package com.practice.coffeemachinediana.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    // несколько заказов - на один рецепт
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    Recipe recipe;

    // несколько заказов - у одной кофемашины
    @ManyToOne
    @JoinColumn(name = "coffee_machine_id", nullable = false)
    private Coffeemachine coffeemachine;

    @Column(nullable = false)
    LocalDateTime createdAt = LocalDateTime.now();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
