package com.practice.coffeemachinediana.service;

import com.practice.coffeemachinediana.model.Coffeemachine;
import com.practice.coffeemachinediana.model.Order;
import com.practice.coffeemachinediana.model.Recipe;
import com.practice.coffeemachinediana.repository.CoffeemachineRepository;
import com.practice.coffeemachinediana.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CoffeemachineService {

    private final CoffeemachineRepository coffeemachineRepository;
    private final OrderRepository orderRepository;
    private final TimeService timeService;

    public CoffeemachineService(CoffeemachineRepository coffeemachineRepository, OrderRepository orderRepository, TimeService timeService) {
        this.coffeemachineRepository = coffeemachineRepository;
        this.orderRepository = orderRepository;
        this.timeService = timeService;
    }

    public List<Coffeemachine> getAllCoffeemachines() {
        return coffeemachineRepository.findAll();
    }

    public Coffeemachine getCoffeemachineById(int id) {
        return coffeemachineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Кофемашина не найдена"));
    }

    public void registerOrder(Order order) {
        Recipe recipe = order.getRecipe();
        recipe.setOrderCount(recipe.getOrderCount() + 1);
    }
}


