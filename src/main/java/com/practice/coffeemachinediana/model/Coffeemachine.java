package com.practice.coffeemachinediana.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import java.util.List;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "coffee_machines")

public class Coffeemachine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false, unique = true)
    String name;

    // я не поняла прикол с кофемашинами, поэтому предположим, что их может быть несколько
    // поэтому отдельная привязка заказов к конкретной кофемашине
    @OneToMany(mappedBy = "coffeemachine")
    private List<Order> orders;

    @ManyToMany
    @JoinTable(name = "coffee_machine_recipes",
            joinColumns = @JoinColumn(name = "coffee_machine_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    Set<Recipe> recipes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
}
