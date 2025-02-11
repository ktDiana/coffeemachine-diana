package com.practice.coffeemachinediana.repository;

import com.practice.coffeemachinediana.model.Coffeemachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeemachineRepository extends JpaRepository<Coffeemachine, Integer> {
}
