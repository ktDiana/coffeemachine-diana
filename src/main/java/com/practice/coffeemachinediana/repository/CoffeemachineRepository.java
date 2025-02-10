package com.practice.coffeemachinediana.repository;

import com.practice.coffeemachinediana.model.Coffeemachine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeemachineRepository extends JpaRepository<Coffeemachine, Integer> {
}
