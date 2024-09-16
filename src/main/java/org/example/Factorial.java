package org.example;

public class Factorial {
    // Метод для вычисления факториала числа
    public static long calculate(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Факториал не определен для отрицательных чисел.");
        }
        long result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i; // Умножаем результат на текущее число на каждой итерации
        }
        return result;
    }
}
