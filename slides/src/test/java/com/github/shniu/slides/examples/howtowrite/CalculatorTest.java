package com.github.shniu.slides.examples.howtowrite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @Test
    void should_get_sum_of_two_numbers() {
        // Arrange (Given)
        double a = 10;
        double b = 100;
        Calculator calculator = new Calculator();

        // Act (When)
        double sum = calculator.sum(a, b);

        // Assert (Then)
        assertEquals(110, sum);
    }

    @Test
    void should_get_double_max_value_when_the_sum_number_is_overflow() {
        // Arrange (Given)
        double a = Double.MAX_VALUE;
        double b = 10;
        Calculator calculator = new Calculator();

        // Act (When)
        double sum = calculator.sum(a, b);

        // Assert (Then)
        assertEquals(Double.MAX_VALUE, sum);
    }
}