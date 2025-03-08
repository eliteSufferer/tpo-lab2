package org.example.stubs;

public class LogarithmicFunctionsStub {
    public static double ln(double x, double epsilon) {
        if (Math.abs(x - 1) < epsilon) return 0.0;       // ln(1) = 0
        if (Math.abs(x - 2) < epsilon) return 0.6931;    // ln(2) ≈ 0.6931
        if (Math.abs(x - 5) < epsilon) return 1.6094;    // ln(5) ≈ 1.6094
        if (Math.abs(x - 10) < epsilon) return 2.3026;   // ln(10) ≈ 2.3026
        throw new IllegalArgumentException("Значение не определено в заглушке");
    }
}
