package com.practice.coffeemachinediana.controller;

import com.practice.coffeemachinediana.dto.CoffeemachineDto;
import com.practice.coffeemachinediana.dto.OrderDto;
import com.practice.coffeemachinediana.model.Coffeemachine;
import com.practice.coffeemachinediana.service.CoffeemachineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffeemachines")
public class CoffeemachineController {
    private final CoffeemachineService coffeemachineService;

    public CoffeemachineController(CoffeemachineService coffeemachineService) {
        this.coffeemachineService = coffeemachineService;
    }

    @GetMapping
    public List<Coffeemachine> getAllOrders() {
        return coffeemachineService.findAll();
    }

    @PostMapping("/add")
    public Coffeemachine addCoffeemachine(@RequestBody CoffeemachineDto coffeemachineDto) {
        Coffeemachine coffeemachine = new Coffeemachine();
        coffeemachine.setName(coffeemachineDto.getName());
        return coffeemachineService.addCoffeemachine(coffeemachine);
    }

    @PostMapping("/{id}/order")
    public void registerOrder(@PathVariable int id, @RequestBody OrderDto orderDto) {
        coffeemachineService.registerOrder(orderDto, id);
    }
}