import org.example.functions.TrigonometricFunctions;
import org.junit.Test;
import static org.junit.Assert.*;

public class TrigonometricFunctionsTest {
    private static final double EPSILON = 1e-6;

    @Test
    public void testSinZero() {
        double x = 0;
        double result = TrigonometricFunctions.sin(x, EPSILON);
        assertEquals(0.0, result, EPSILON);
    }

    @Test
    public void testSinPiOver2() {
        double x = Math.PI / 2;
        double result = TrigonometricFunctions.sin(x, EPSILON);
        assertEquals(1.0, result, EPSILON);
    }

    @Test
    public void testSinPi() {
        double x = Math.PI;
        double result = TrigonometricFunctions.sin(x, EPSILON);
        assertEquals(0.0, result, EPSILON);
    }

    @Test
    public void testCosZero() {
        double x = 0;
        double result = TrigonometricFunctions.cos(x, EPSILON);
        assertEquals(1.0, result, EPSILON);
    }

    @Test
    public void testCosPiOver2() {
        double x = Math.PI / 2;
        double result = TrigonometricFunctions.cos(x, EPSILON);
        assertEquals(0.0, result, EPSILON);
    }

    @Test
    public void testCosPi() {
        double x = Math.PI;
        double result = TrigonometricFunctions.cos(x, EPSILON);
        assertEquals(-1.0, result, EPSILON);
    }
}
