import org.example.functions.LogarithmicFunctions;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LogarithmicFunctionsTest {
    private static final double EPSILON = 1e-6;
    private static final double PRECISION = 1e-3;

    @Test
    public void testLnOne() {
        double x = 1;
        double result = LogarithmicFunctions.ln(x, EPSILON);
        assertEquals(0.0, result, PRECISION);
    }

    @Test
    public void testLnE() {
        double x = Math.E;
        double result = LogarithmicFunctions.ln(x, EPSILON);
        assertEquals(1.0, result, PRECISION);
    }

    @Test
    public void testLnTwo() {
        double x = 2;
        double result = LogarithmicFunctions.ln(x, EPSILON);
        assertEquals(0.693147, result, PRECISION);
    }
    
    @Test
    public void testLog2() {
        double x = 8;
        double result = LogarithmicFunctions.log2(x, EPSILON);
        assertEquals(3.0, result, PRECISION);
    }
    
    @Test
    public void testLog5() {
        double x = 5;
        double result = LogarithmicFunctions.log5(x, EPSILON);
        assertEquals(1.0, result, PRECISION);
    }
    
    @Test
    public void testLog10() {
        double x = 100;
        double result = LogarithmicFunctions.log10(x, EPSILON);
        assertEquals(2.0, result, PRECISION);
    }
    
    @Test
    public void testLogBaseAny() {
        double base = 3;
        double x = 27;
        double result = LogarithmicFunctions.log(base, x, EPSILON);
        assertEquals(3.0, result, PRECISION);
    }
}