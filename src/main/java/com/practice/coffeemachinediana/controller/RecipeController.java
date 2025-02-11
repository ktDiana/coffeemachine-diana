package com.practice.coffeemachinediana.controller;

import com.practice.coffeemachinediana.dto.RecipeDto;
import com.practice.coffeemachinediana.model.Recipe;
import com.practice.coffeemachinediana.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    // GET - СПИСОК ВСЕХ РЕЦЕПТОВ
    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    // GET - ПОПУЛЯРНЫЙ РЕЦЕПТ
    @GetMapping("/popular")
    public Recipe getMostPopularRecipe() {
        return recipeService.getMostPopularRecipe();
    }

    // POST - НОВЫЙ РЕЦЕПТ
    @PostMapping("/add")
    public Recipe addRecipe(@RequestBody RecipeDto recipeDto) {
        return recipeService.addRecipe(recipeDto);
    }

}