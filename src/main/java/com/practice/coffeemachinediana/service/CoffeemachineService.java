package com.practice.coffeemachinediana.service;

import com.practice.coffeemachinediana.dto.OrderDto;
import com.practice.coffeemachinediana.model.Coffeemachine;
import com.practice.coffeemachinediana.model.Order;
import com.practice.coffeemachinediana.model.Recipe;
import com.practice.coffeemachinediana.repository.CoffeemachineRepository;
import com.practice.coffeemachinediana.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public class CoffeemachineService {

    private final CoffeemachineRepository coffeemachineRepository;
    private final OrderRepository orderRepository;
    private final TimeService timeService;

    public CoffeemachineService(CoffeemachineRepository coffeemachineRepository, OrderRepository orderRepository, TimeService timeService) {
        this.coffeemachineRepository = coffeemachineRepository;
        this.orderRepository = orderRepository;
        this.timeService = timeService;
    }

    public List<Coffeemachine> findAll() {
        return coffeemachineRepository.findAll();
    }

    public Coffeemachine addCoffeemachine(Coffeemachine coffeemachine) {
        return coffeemachineRepository.save(coffeemachine);
    }

    public Coffeemachine getCoffeemachineById(int id) {
        return coffeemachineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Кофемашина не найдена"));
    }

    public void registerOrder(OrderDto orderDto, int coffeemachineId) {
        Coffeemachine coffeemachine = getCoffeemachineById(coffeemachineId);
        Recipe recipe = orderDto.getRecipe();

        Order order = new Order();
        order.setRecipe(recipe);
        order.setCoffeemachine(coffeemachine); // Указываем кофемашину

        orderRepository.save(order);

        recipe.setCount(recipe.getCount() + 1);
    }

}


