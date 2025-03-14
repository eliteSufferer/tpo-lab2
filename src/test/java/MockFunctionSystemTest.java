import org.example.Main;
import org.example.functions.LogarithmicFunctions;
import org.example.functions.TrigonometricFunctions;
import org.example.modules.LogarithmicModule;
import org.example.modules.TrigonometricModule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Тест системы функций с использованием Mockito для создания мок-объектов
 * вместо прямого использования статических заглушек
 */
@RunWith(MockitoJUnitRunner.class)
public class MockFunctionSystemTest {
    private static final double EPSILON = 1e-6;

    @Mock
    private LogarithmicModule.LogarithmicFunction mockLnFunction;
    
    @Mock
    private TrigonometricModule.TrigonometricFunction mockSinFunction;
    
    // Сохраняем оригинальные функции
    private LogarithmicModule.LogarithmicFunction originalLnFunction;
    private TrigonometricModule.TrigonometricFunction originalSinFunction;

    @Before
    public void setup() {
        // Сохраняем оригинальные функции
        originalLnFunction = (x, eps) -> Math.log(x);
        originalSinFunction = (x, eps) -> Math.sin(x);
        
        // По умолчанию сбрасываем зависимости модулей
        LogarithmicModule.setDependency(originalLnFunction);
        TrigonometricModule.setDependency(originalSinFunction);
    }
    
    @After
    public void tearDown() {
        // Сбрасываем зависимости к оригинальным после каждого теста
        LogarithmicModule.setDependency(originalLnFunction);
        TrigonometricModule.setDependency(originalSinFunction);
    }

    /**
     * Тест логарифмического модуля с использованием мок-объекта
     * для функции ln с предопределенным значением
     */
    @Test
    public void testLogarithmicModuleWithMock() {
        // Получаем стандартный результат с оригинальной функцией
        double standardResult = LogarithmicModule.calculate(5.0, EPSILON);
        
        // Настраиваем мок для функции ln чтобы она возвращала явно другое значение
        when(mockLnFunction.apply(anyDouble(), anyDouble())).thenReturn(10.0);
        
        // Устанавливаем наш мок как зависимость для модуля
        LogarithmicModule.setDependency(mockLnFunction);
        
        // Вызываем функцию модуля с тем же входным параметром
        double result = LogarithmicModule.calculate(5.0, EPSILON);
        
        // Проверяем, что мок был вызван хотя бы один раз
        verify(mockLnFunction, atLeastOnce()).apply(anyDouble(), anyDouble());
        
        // Значения должны отличаться
        assertNotEquals("Результаты должны отличаться при использовании мока", 
                standardResult, result, EPSILON);
        
        // Для отладки выводим значения
        System.out.println("Результат с моком: " + result);
        System.out.println("Стандартный результат: " + standardResult);
    }
    
    /**
     * Тест тригонометрического модуля с использованием мок-объекта
     * для функции sin с предопределенным значением
     */
    @Test
    public void testTrigonometricModuleWithMock() {
        // Получаем стандартный результат с оригинальной функцией
        double standardResult = TrigonometricModule.calculate(Math.PI / 4, EPSILON);
        
        // Настраиваем мок для функции sin чтобы она возвращала явно другое значение
        when(mockSinFunction.apply(anyDouble(), anyDouble())).thenReturn(0.1);
        
        // Устанавливаем наш мок как зависимость для модуля
        TrigonometricModule.setDependency(mockSinFunction);
        
        // Вызываем функцию модуля с тем же входным параметром
        double result = TrigonometricModule.calculate(Math.PI / 4, EPSILON);
        
        // Проверяем, что мок был вызван хотя бы один раз
        verify(mockSinFunction, atLeastOnce()).apply(anyDouble(), anyDouble());
        
        // Значения должны отличаться
        assertNotEquals("Результаты должны отличаться при использовании мока", 
                standardResult, result, EPSILON);
        
        // Для отладки выводим значения
        System.out.println("Результат с моком: " + result);
        System.out.println("Стандартный результат: " + standardResult);
    }
    
    /**
     * Тест с использованием статического мока для базовых функций
     */
    @Test
    public void testWithStaticMock() {
        try (MockedStatic<LogarithmicFunctions> mockedLn = Mockito.mockStatic(LogarithmicFunctions.class);
             MockedStatic<TrigonometricFunctions> mockedSin = Mockito.mockStatic(TrigonometricFunctions.class)) {
            
            // Настраиваем статические моки
            mockedLn.when(() -> LogarithmicFunctions.ln(eq(2.0), anyDouble())).thenReturn(1.0);
            mockedSin.when(() -> TrigonometricFunctions.sin(eq(Math.PI / 4), anyDouble())).thenReturn(0.8);
            
            // Проверяем, что статические моки работают
            assertEquals(1.0, LogarithmicFunctions.ln(2.0, EPSILON), EPSILON);
            assertEquals(0.8, TrigonometricFunctions.sin(Math.PI / 4, EPSILON), EPSILON);
            
            // Проверяем, что для других значений они делегируют вызов (можно настроить)
            // Здесь просто настроим моки для других значений
            mockedLn.when(() -> LogarithmicFunctions.ln(eq(10.0), anyDouble())).thenReturn(2.5);
            assertEquals(2.5, LogarithmicFunctions.ln(10.0, EPSILON), EPSILON);
        }
        
        // После закрытия контекста статические моки перестают работать
        // и функции возвращаются к своему обычному поведению
        assertEquals(Math.log(2.0), LogarithmicFunctions.ln(2.0, EPSILON), EPSILON);
    }
    
    /**
     * Тест интеграции Main с моками
     */
    @Test
    public void testMainWithMocks() {
        try (MockedStatic<TrigonometricModule> mockedTrigModule = Mockito.mockStatic(TrigonometricModule.class);
             MockedStatic<LogarithmicModule> mockedLogModule = Mockito.mockStatic(LogarithmicModule.class)) {
            
            // Настраиваем модули, чтобы они возвращали известные значения
            mockedTrigModule.when(() -> TrigonometricModule.calculate(anyDouble(), anyDouble()))
                    .thenReturn(5.0);
            mockedLogModule.when(() -> LogarithmicModule.calculate(anyDouble(), anyDouble()))
                    .thenReturn(10.0);
            
            // Проверяем, что Main использует правильный модуль в зависимости от значения x
            double negativeResult = Main.calculate(-1.0, EPSILON); // должен использовать тригонометрический модуль
            assertEquals(5.0, negativeResult, EPSILON);
            
            double positiveResult = Main.calculate(1.0, EPSILON); // должен использовать логарифмический модуль
            assertEquals(10.0, positiveResult, EPSILON);
            
            // Проверяем, сколько раз были вызваны модули
            mockedTrigModule.verify(() -> TrigonometricModule.calculate(-1.0, EPSILON), times(1));
            mockedLogModule.verify(() -> LogarithmicModule.calculate(1.0, EPSILON), times(1));
        }
    }
    
    /**
     * Демонстрация того, как можно использовать моки для проверки граничных случаев
     */
    @Test
    public void testEdgeCasesWithMocks() {
        // Проверка деления на ноль в тригонометрическом модуле
        when(mockSinFunction.apply(anyDouble(), anyDouble())).thenReturn(0.0);
        TrigonometricModule.setDependency(mockSinFunction);
        
        try {
            TrigonometricModule.calculate(Math.PI / 4, EPSILON);
            fail("Должно быть выброшено исключение ArithmeticException");
        } catch (ArithmeticException e) {
            // Ожидаемое исключение
            assertTrue(e.getMessage().contains("ноль") || e.getMessage().contains("zero"));
        }
    }
    
    /**
     * Проверка неопределенности при x = π/2 в тригонометрическом модуле
     */
    @Test
    public void testHalfPiWithMock() {
        // Настраиваем мок для sin(π/2) = 1 и cos(π/2) = 0
        TrigonometricModule.setDependency((x, eps) -> {
            if (Math.abs(x - Math.PI/2) < EPSILON) return 1.0;
            if (Math.abs(x - Math.PI) < EPSILON) return 0.0;
            return Math.sin(x);
        });
        
        // При x = π/2 косеканс определен, но секанс неопределен
        double result = TrigonometricModule.calculate(Math.PI / 2, EPSILON);
        assertTrue("Результат должен быть NaN при x = π/2", Double.isNaN(result));
    }
} 