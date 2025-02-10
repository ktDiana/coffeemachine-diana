package com.practice.coffeemachinediana.controller;

import com.practice.coffeemachinediana.dto.OrderDto;
import com.practice.coffeemachinediana.model.Order;
import com.practice.coffeemachinediana.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // GET - СПИСОК ВСЕХ ЗАКАЗОВ
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // POST - НОВЫЙ ЗАКАЗ (по названию рецепта)
    @PostMapping("/add")
    public Order addOrder(@RequestBody OrderDto orderDto) {
        return orderService.addOrder(orderDto);
    }
}
