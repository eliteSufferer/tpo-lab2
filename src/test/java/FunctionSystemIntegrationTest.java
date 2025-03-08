import org.example.Main;
import org.example.functions.LogarithmicFunctions;
import org.example.modules.LogarithmicModule;
import org.example.modules.TrigonometricModule;
import org.example.stubs.LogarithmicFunctionsStub;
import org.example.stubs.TrigonometricFunctionsStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class FunctionSystemIntegrationTest {
    private static final double EPSILON = 1e-6;

    @Test
    public void testTrigonometricPartAtXNegative() {
        double x = -Math.PI / 4;
        double sinX = Math.sin(x); // ≈ -√2/2
        double cosX = Math.cos(x); // ≈ √2/2
        double cscX = 1 / sinX;    // ≈ -√2
        double secX = 1 / cosX;    // ≈ √2
        double term1 = Math.pow(cscX / secX, 3) * sinX; // (-1)^3 * (-√2/2) = √2/2
        double term2 = Math.pow(secX, 2);                // (√2)^2 = 2
        double expected = (term1 - term2) + cscX;        // (√2/2 - 2) + (-√2) = -√2/2 - 2 ≈ -2.7071

        double result = Main.calculate(x, EPSILON);
        assertEquals(-2.70710678118, result, 1e-6); // Ожидаемое значение ≈ -2.7071
    }

//    @Test
//    public void testLogarithmicPartAtX10() {
//        double x = 10.0;
//        double result = Main.calculate(x, EPSILON);
//        assertEquals(/* Ручной расчет для x=10 */, result, EPSILON);
//    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidXForLogarithm() {
        LogarithmicFunctions.ln(-1, EPSILON);
    }

    @Test(expected = ArithmeticException.class)
    public void testXZero() {
        Main.calculate(0, EPSILON);
    }

    @Test
    public void testTrigonometricModuleWithStubs() {
        TrigonometricModule.setDependencies(
                TrigonometricFunctionsStub::sin,
                TrigonometricFunctionsStub::cos
        );

        double x = Math.PI / 4;
        double epsilon = 1e-6;

        // Ручной расчет ожидаемого результата
        double sinX = Math.sqrt(2) / 2;    // sin(π/4) = √2/2
        double cosX = Math.sqrt(2) / 2;    // cos(π/4) = √2/2
        double cscX = 1 / sinX;            // 1 / (√2/2) = √2
        double secX = 1 / cosX;            // 1 / (√2/2) = √2
        double term1 = Math.pow(cscX / secX, 3) * sinX; // (1)^3 * √2/2 = √2/2
        double term2 = Math.pow(secX, 2);                // (√2)^2 = 2
        double expected = (term1 - term2) + cscX;        // (√2/2 - 2) + √2 = (3√2/2 - 2)

        double result = TrigonometricModule.calculate(x, epsilon);
        assertEquals(expected, result, epsilon);
    }

    @Test
    public void testLogarithmicModuleWithStubs() {
        LogarithmicModule.setDependency(LogarithmicFunctionsStub::ln);
        double result = LogarithmicModule.calculate(1, EPSILON);
        assertTrue(Double.isNaN(result)); // Ожидаем NaN, а не 0.0
    }
}
