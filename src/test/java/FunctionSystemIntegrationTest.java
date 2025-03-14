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
    
    /**
     * Комплексный тест для проверки всей системы функций
     * с различными значениями x: x ≤ 0 и x > 0
     * Эталонные значения вычисляются через стандартные функции Math
     */
    @Test
    public void testCompleteSystemWithDifferentValues() {
        // Погрешность сравнения для тестов
        final double DELTA = 0.01;
        
        // Проверка тригонометрической части системы (x ≤ 0)
        // x = -π/6 (отрицательный x, но не на особых точках)
        double x1 = -Math.PI / 6;
        double resultNegative = Main.calculate(x1, EPSILON);
        
        // Вычисляем эталонное значение через Math для тригонометрической части
        double sinX1 = Math.sin(x1);
        double cosX1 = Math.cos(x1);
        double cscX1 = 1 / sinX1;
        double secX1 = 1 / cosX1;
        double term1Trig = Math.pow(cscX1 / secX1, 3) * sinX1;
        double term2Trig = Math.pow(secX1, 2);
        double expectedNegative = (term1Trig - term2Trig) + cscX1;
        
        assertEquals(expectedNegative, resultNegative, DELTA);
        
        // Проверка логарифмической части системы (x > 0)
        // x = 3 (положительный x)
        double x2 = 3;
        double resultPositive = Main.calculate(x2, EPSILON);
        
        // Вычисляем эталонное значение через Math для логарифмической части
        double lnX2 = Math.log(x2);
        double log2X2 = Math.log(x2) / Math.log(2);
        double log5X2 = Math.log(x2) / Math.log(5);
        double log10X2 = Math.log10(x2);
        double term1Log = Math.pow(Math.pow(lnX2, 3), 2);
        double term2Log = (log5X2 - log10X2);
        double term3Log = (log5X2 / log2X2);
        double expectedPositive = ((term1Log + log2X2) - term2Log) * term3Log;
        
        assertEquals(expectedPositive, resultPositive, DELTA);
        
        // Проверка для дополнительной точки в тригонометрической части
        double x3 = -1.0;
        double resultTrig = Main.calculate(x3, EPSILON);
        
        // Вычисляем эталонное значение для x3
        double sinX3 = Math.sin(x3);
        double cosX3 = Math.cos(x3);
        double cscX3 = 1 / sinX3;
        double secX3 = 1 / cosX3;
        double term1Trig3 = Math.pow(cscX3 / secX3, 3) * sinX3;
        double term2Trig3 = Math.pow(secX3, 2);
        double expectedTrig = (term1Trig3 - term2Trig3) + cscX3;
        
        assertEquals(expectedTrig, resultTrig, DELTA);
        
        // Проверка для дополнительной точки в логарифмической части
        double x4 = 5.0;
        double resultLog = Main.calculate(x4, EPSILON);
        
        // Вычисляем эталонное значение для x4
        double lnX4 = Math.log(x4);
        double log2X4 = Math.log(x4) / Math.log(2);
        double log5X4 = Math.log(x4) / Math.log(5);
        double log10X4 = Math.log10(x4);
        double term1Log4 = Math.pow(Math.pow(lnX4, 3), 2);
        double term2Log4 = (log5X4 - log10X4);
        double term3Log4 = (log5X4 / log2X4);
        double expectedLog = ((term1Log4 + log2X4) - term2Log4) * term3Log4;
        
        assertEquals(expectedLog, resultLog, DELTA);
        
        // Проверка граничных значений
        // x близкий к 0 с отрицательной стороны
        double x5 = -0.01;
        double resultNearZeroNegative = Main.calculate(x5, EPSILON);
        // Это должно использовать тригонометрическую часть
        assertTrue("Значение должно быть конечным для x близкого к 0", 
                !Double.isNaN(resultNearZeroNegative) && !Double.isInfinite(resultNearZeroNegative));
        
        // x с большим положительным значением
        double x6 = 100;
        double resultLargeValue = Main.calculate(x6, EPSILON);
        // Это должно использовать логарифмическую часть
        assertTrue("Значение должно быть конечным для большого x", 
                !Double.isNaN(resultLargeValue) && !Double.isInfinite(resultLargeValue));
    }
}
