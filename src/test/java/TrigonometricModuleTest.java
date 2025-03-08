import org.example.modules.TrigonometricModule;
import org.example.stubs.TrigonometricFunctionsStub;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TrigonometricModuleTest {
    private static final double EPSILON = 1e-6;

    @Before
    public void setup() {
        TrigonometricModule.setDependencies(
                TrigonometricFunctionsStub::sin,
                TrigonometricFunctionsStub::cos
        );
    }

    @Test
    public void testCalculateAtPiOver4() {
        double x = Math.PI / 4;
        double expected = (Math.sqrt(2)/2 - 2) + Math.sqrt(2); // Ручной расчет
        double result = TrigonometricModule.calculate(x, EPSILON);
        assertEquals(expected, result, EPSILON);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivisionByZeroAtX0() {
        TrigonometricModule.calculate(0, EPSILON);
    }

    @Test
    public void testXPiOver2() {
        double x = -Math.PI / 2;
        double result = TrigonometricModule.calculate(x, EPSILON);
        assertTrue("Ожидается NaN из-за sec(-π/2)", Double.isNaN(result));
    }
}
