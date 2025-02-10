package com.practice.coffeemachinediana.repository;

import com.practice.coffeemachinediana.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    Optional<Recipe> findByName(String name);
    List<Recipe> findAllByOrderByCountDesc();
}

