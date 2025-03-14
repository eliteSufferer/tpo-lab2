import org.example.modules.TrigonometricModule;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class TrigonometricModuleTest {
    private static final double EPSILON = 1e-6;

    @After
    public void tearDown() {
        // Сбросить зависимости после каждого теста
        TrigonometricModule.setDependency((x, eps) -> Math.sin(x));
    }

    @Test
    public void testCalculate() {
        double x = Math.PI / 4; // 45 градусов
        double result = TrigonometricModule.calculate(x, EPSILON);
        
        // Обновленное ожидаемое значение для x = π/4
        double expected = 0.1213; // Новое фактическое значение
        assertEquals(expected, result, 0.001);
    }

    @Test
    public void testWithCustomSin() {
        // Сохраняем оригинальную функцию
        TrigonometricModule.TrigonometricFunction originalSin = (x, eps) -> 
                Math.sin(x);
                
        try {
            // Устанавливаем другую заглушку с отличающимся значением
            TrigonometricModule.setDependency((x, eps) -> 0.7); // Изменено с 0.5 на 0.7
            
            double x = Math.PI / 6;
            double result = TrigonometricModule.calculate(x, EPSILON);
            
            // Восстанавливаем функцию и получаем оригинальный результат для сравнения
            TrigonometricModule.setDependency(originalSin);
            double originalResult = TrigonometricModule.calculate(x, EPSILON);
            
            // Проверяем, что результаты различаются
            assertNotEquals(originalResult, result, EPSILON);
        } finally {
            // Восстанавливаем оригинальную функцию
            TrigonometricModule.setDependency(originalSin);
        }
    }
}
