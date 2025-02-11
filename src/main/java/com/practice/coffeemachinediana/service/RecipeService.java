package com.practice.coffeemachinediana.service;

import com.practice.coffeemachinediana.dto.IngredientDto;
import com.practice.coffeemachinediana.dto.RecipeDto;
import com.practice.coffeemachinediana.model.Ingredient;
import com.practice.coffeemachinediana.model.Recipe;
import com.practice.coffeemachinediana.repository.IngredientRepository;
import com.practice.coffeemachinediana.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    // список всех рецептов
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    // новый рецепт
    public Recipe addRecipe(RecipeDto recipeDto) {
        Recipe recipe = new Recipe();
        recipe.setName(recipeDto.getName());
        recipe.setCount(0);

        // предположим, что мы обязательно указываем ингредиенты
        List<Ingredient> ingredients = new ArrayList<>();

        for (IngredientDto ingredientDto : recipeDto.getIngredients()) {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(ingredientDto.getName());
            ingredient.setQuantity(ingredientDto.getQuantity());
            ingredient.setRecipe(recipe);

            ingredients.add(ingredient);
        }

        recipe.setIngredients(ingredients);
        recipeRepository.save(recipe);
        ingredientRepository.saveAll(ingredients);

        return recipe;
    }

//     получить популярный рецепт
    public Recipe getMostPopularRecipe() {
        return recipeRepository.findAllByOrderByCountDesc()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Заказов ещё не было"));
    }

}


