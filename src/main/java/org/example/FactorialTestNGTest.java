package org.example;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FactorialTestNGTest {
    @Test
    public void testCalculateFactorial() {
        assertEquals(Factorial.calculate(0), 1); // Факториал 0 равен 1
        assertEquals(Factorial.calculate(1), 1); // Факториал 1 равен 1
        assertEquals(Factorial.calculate(2), 2); // Факториал 2 равен 2
        assertEquals(Factorial.calculate(3), 6); // Факториал 3 равен 6
        assertEquals(Factorial.calculate(4), 24); // Факториал 4 равен 24
        assertEquals(Factorial.calculate(5), 120); // Факториал 5 равен 120
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Факториал не определен для отрицательных чисел.")
    public void testCalculateFactorialNegativeNumber() {
        Factorial.calculate(-1);
    }
}
