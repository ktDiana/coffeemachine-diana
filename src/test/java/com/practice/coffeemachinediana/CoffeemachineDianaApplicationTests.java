package com.practice.coffeemachinediana;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Disabled // Это отключит тест
public class CoffeemachineDianaApplicationTests {
    @Test
    void contextLoads() {}
}
