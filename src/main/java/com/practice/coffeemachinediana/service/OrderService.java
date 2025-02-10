package com.practice.coffeemachinediana.service;

import com.practice.coffeemachinediana.dto.OrderDto;
import com.practice.coffeemachinediana.model.Coffeemachine;
import com.practice.coffeemachinediana.model.Ingredient;
import com.practice.coffeemachinediana.model.Order;
import com.practice.coffeemachinediana.model.Recipe;
import com.practice.coffeemachinediana.repository.CoffeemachineRepository;
import com.practice.coffeemachinediana.repository.IngredientRepository;
import com.practice.coffeemachinediana.repository.OrderRepository;
import com.practice.coffeemachinediana.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional  // если какая-то операция прервётся, то всё откатим назад

public class OrderService {
    private final OrderRepository orderRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final TimeService timeService;
    private final CoffeemachineRepository coffeemachineRepository;

    public OrderService(OrderRepository orderRepository, RecipeRepository recipeRepository, IngredientRepository ingredientRepository, TimeService timeService, CoffeemachineRepository coffeemachineRepository) {
        this.orderRepository = orderRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.timeService = timeService;
        this.coffeemachineRepository = coffeemachineRepository;
    }

    // список всех заказов
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // новый заказ
    public Order addOrder(OrderDto orderDto) {

        Coffeemachine coffeemachine = coffeemachineRepository.findById(orderDto.getCoffeemachine().getId())
                .orElseThrow(() -> new RuntimeException("Кофемашина не найдена"));

        if (!timeService.isMachineAvailable()) {
            throw new RuntimeException("Кофемашина не работает на данный момент");
        }

        // формируем заказ - сначала ищем рецепт по названию
        Recipe recipe = recipeRepository.findByName(orderDto.getRecipe().getName())
                .orElseThrow(() -> new RuntimeException("Напиток не найден"));

        // если нашли напиток, то проверяем достаточно ли ингредиентов
        for (Ingredient ingredient : recipe.getIngredients()) {

            // находим нужный ингредиент
            Ingredient currentIngredient = ingredientRepository.findByName(ingredient.getName())
                    .orElseThrow(() -> new RuntimeException("Ингредиент " + ingredient.getName() + " отсутствует"));

            // проверяем, достаточно ли его
            if (currentIngredient.getQuantity() < ingredient.getQuantity()) {
                throw new RuntimeException("Недостаточно " + ingredient.getName());
            }
        }

        // уменьшаем количество ингредиентов (ведь мы их тратим на заказ)
        for (Ingredient ingredient : recipe.getIngredients()) {
            Ingredient currentIngredient = ingredientRepository.findByName(ingredient.getName())
                    // я знаю, что вторая проверка лишняя
                    .orElseThrow();
            currentIngredient.setQuantity(currentIngredient.getQuantity() - ingredient.getQuantity());
            ingredientRepository.save(currentIngredient);
        }

        Order order = new Order();
        order.setRecipe(recipe);
        order.setCoffeemachine(coffeemachine);
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }
}
