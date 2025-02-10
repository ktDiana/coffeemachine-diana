package com.practice.coffeemachinediana.dto;

import java.util.List;

public class RecipeDto {

    private String name;

    private List<IngredientDto> ingredients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IngredientDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }
}
