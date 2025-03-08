
import org.example.modules.LogarithmicModule;
import org.example.stubs.LogarithmicFunctionsStub;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LogarithmicModuleTest {
    private static final double EPSILON = 1e-6;

    @Before
    public void setup() {
        LogarithmicModule.setDependency(LogarithmicFunctionsStub::ln);
    }

    @Test
    public void testCalculateAtX2() {
        LogarithmicModule.setDependency((x, epsilon) -> Math.log(x));

        double x = 2.0;
        double epsilon = 1e-6;

        // Точные значения через Math
        double lnX = Math.log(x);
        double log2X = lnX / Math.log(2);
        double log5X = lnX / Math.log(5);
        double log10X = lnX / Math.log(10);

        // Ручной расчет с повышенной точностью
        double term1 = Math.pow(Math.pow(lnX, 3), 2);
        double term2 = (log5X - log10X) * (log5X / log2X);
        double expected = term1 + log2X - term2;

        double result = LogarithmicModule.calculate(x, epsilon);
        assertEquals(expected, result, 1e-5); // Увеличенная погрешность
    }

    @Test
    public void testNaNResultAtX1() {
        double result = LogarithmicModule.calculate(1, EPSILON);
        assertTrue(Double.isNaN(result));
    }
}