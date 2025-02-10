package com.practice.coffeemachinediana.controller;

import com.practice.coffeemachinediana.service.CoffeemachineService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coffeemachines")
public class CoffeemachineController {
    private final CoffeemachineService coffeemachineService;

    public CoffeemachineController(CoffeemachineService coffeemachineService) {
        this.coffeemachineService = coffeemachineService;
    }
}
