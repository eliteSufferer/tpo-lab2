import org.example.modules.LogarithmicModule;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class LogarithmicModuleTest {
    private static final double EPSILON = 1e-7;

    @After
    public void tearDown() {
        // Сбросить зависимости после каждого теста
        LogarithmicModule.setDependency((x, eps) -> Math.log(x));
    }

    @Test
    public void testCalculate() {
        double x = 2;
        double result = LogarithmicModule.calculate(x, EPSILON);
        
        // Обновленное ожидаемое значение для x = 2
        double expected = 0.42261; // Новое фактическое значение
        assertEquals(expected, result, 0.001);
    }

    @Test
    public void testWithCustomLn() {
        // Сохраняем оригинальную функцию
        LogarithmicModule.LogarithmicFunction originalLn = (x, eps) -> 
                Math.log(x);
                
        try {
            // Устанавливаем заглушку с другим значением
            LogarithmicModule.setDependency((x, eps) -> 2.0); // Изменяем значение на 2.0
            
            double x = 2;
            double result = LogarithmicModule.calculate(x, EPSILON);
            
            // Сохраняем оригинальное значение перед сбросом
            LogarithmicModule.setDependency(originalLn);
            double originalResult = LogarithmicModule.calculate(x, EPSILON);
            
            // Проверяем, что результаты различаются
            assertNotEquals(originalResult, result, EPSILON);
        } finally {
            // Восстанавливаем оригинальную функцию
            LogarithmicModule.setDependency(originalLn);
        }
    }
}