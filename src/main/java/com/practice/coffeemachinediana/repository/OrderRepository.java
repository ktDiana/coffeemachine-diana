package com.practice.coffeemachinediana.repository;

import com.practice.coffeemachinediana.model.Order;
import com.practice.coffeemachinediana.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    int countByRecipe(Recipe recipe);
}
