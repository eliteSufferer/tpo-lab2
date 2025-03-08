package org.example.stubs;

public class TrigonometricFunctionsStub {
    public static double sin(double x, double epsilon) {
        // Добавлена поддержка x = π/4
        if (Math.abs(x - 0) < epsilon) return 0.0;
        if (Math.abs(x - Math.PI / 2) < epsilon) return 1.0;
        if (Math.abs(x - Math.PI) < epsilon) return 0.0;
        if (Math.abs(x - Math.PI / 4) < epsilon) return Math.sqrt(2) / 2; // sin(π/4) = √2/2
        if (Math.abs(x + Math.PI/2) < epsilon) return -1.0;
        throw new IllegalArgumentException("Значение не определено в заглушке");
    }

    public static double cos(double x, double epsilon) {
        // Добавлена поддержка x = π/4
        if (Math.abs(x - 0) < epsilon) return 1.0;
        if (Math.abs(x - Math.PI / 2) < epsilon) return 0.0;
        if (Math.abs(x - Math.PI) < epsilon) return -1.0;
        if (Math.abs(x - Math.PI / 4) < epsilon) return Math.sqrt(2) / 2; // cos(π/4) = √2/2
        if (Math.abs(x + Math.PI/2) < epsilon) return 0.0;
        throw new IllegalArgumentException("Значение не определено в заглушке");
    }
}