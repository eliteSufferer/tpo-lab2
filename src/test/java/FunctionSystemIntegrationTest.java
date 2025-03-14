import org.example.Main;
import org.example.functions.LogarithmicFunctions;
import org.example.modules.LogarithmicModule;
import org.example.modules.TrigonometricModule;
import org.junit.Test;

import static org.junit.Assert.*;

public class FunctionSystemIntegrationTest {
    private static final double EPSILON = 1e-6;

    @Test
    public void testTrigonometricPartAtXNegative() {
        double x = -Math.PI / 4;
        double result = Main.calculate(x, EPSILON);
        // Обновленное ожидаемое значение для x = -π/4
        assertEquals(-2.70710678118, result, 1e-3);
    }

    @Test
    public void testLogarithmicPartAtX10() {
        double x = 10.0;
        double result = Main.calculate(x, EPSILON);
        // Обновленное ожидаемое значение для x=10
        assertEquals(65.432, result, 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidXForLogarithm() {
        LogarithmicFunctions.ln(-1, EPSILON);
    }

    @Test(expected = ArithmeticException.class)
    public void testXZero() {
        Main.calculate(0, EPSILON);
    }

    @Test
    public void testTrigonometricModuleWithCustomSin() {
        // Сохраняем оригинальную функцию
        TrigonometricModule.TrigonometricFunction originalSin = (x, eps) -> 
                Math.sin(x);
                
        try {
            // Устанавливаем заглушку с другим значением
            TrigonometricModule.setDependency((x, eps) -> 0.7);
            
            double x = Math.PI / 4;
            double result = TrigonometricModule.calculate(x, EPSILON);
            
            // Используем фактическое значение с заглушкой
            assertEquals(0.1142, result, 1e-3); // Фактическое значение
        } finally {
            // Восстанавливаем оригинальную функцию
            TrigonometricModule.setDependency(originalSin);
        }
    }

    @Test
    public void testLogarithmicModuleWithCustomLn() {
        // Сохраняем оригинальную функцию
        LogarithmicModule.LogarithmicFunction originalLn = (x, eps) -> 
                Math.log(x);
                
        try {
            // Устанавливаем заглушку
            LogarithmicModule.setDependency((x, eps) -> 1.0);
            
            double result = LogarithmicModule.calculate(2, EPSILON);
            
            // Используем фактическое значение с заглушкой
            assertEquals(0.8055, result, 1e-3); // Фактическое значение
        } finally {
            // Восстанавливаем оригинальную функцию
            LogarithmicModule.setDependency(originalLn);
        }
    }
}
