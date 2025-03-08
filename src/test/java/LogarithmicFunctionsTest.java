import org.example.functions.LogarithmicFunctions;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LogarithmicFunctionsTest {
    private static final double EPSILON = 1e-6;

    @Test
    public void testLnOne() {
        double x = 1;
        double result = LogarithmicFunctions.ln(x, EPSILON);
        assertEquals(0.0, result, EPSILON);
    }

    @Test
    public void testLnE() {
        double x = Math.E;
        double result = LogarithmicFunctions.ln(x, EPSILON);
        assertEquals(1.0, result, EPSILON);
    }

    @Test
    public void testLnTwo() {
        double x = 2;
        double result = LogarithmicFunctions.ln(x, EPSILON);
        assertEquals(0.693147, result, EPSILON);
    }
}