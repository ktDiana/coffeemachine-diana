package com.practice.coffeemachinediana.dto;

import com.practice.coffeemachinediana.model.Coffeemachine;
import com.practice.coffeemachinediana.model.Recipe;

public class OrderDto {

    private String name;
    private Recipe recipe;
    private Coffeemachine coffeemachine;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Coffeemachine getCoffeemachine() {
        return coffeemachine;
    }

    public void setCoffeemachine(Coffeemachine coffeemachine) {
        this.coffeemachine = coffeemachine;
    }
}
