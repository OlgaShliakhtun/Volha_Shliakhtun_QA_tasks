import org.example.Factorial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactorialTest {
    @Test
    void testCalculateFactorial() {
        assertEquals(1, Factorial.calculate(0)); // Факториал 0 равен 1
        assertEquals(1, Factorial.calculate(1)); // Факториал 1 равен 1
        assertEquals(2, Factorial.calculate(2)); // Факториал 2 равен 2
        assertEquals(6, Factorial.calculate(3)); // Факториал 3 равен 6
        assertEquals(24, Factorial.calculate(4)); // Факториал 4 равен 24
        assertEquals(120, Factorial.calculate(5)); // Факториал 5 равен 120
    }

    @Test
    void testCalculateFactorialNegativeNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Factorial.calculate(-1);
        });
        assertEquals("Факториал не определен для отрицательных чисел.", exception.getMessage());
    }
}
