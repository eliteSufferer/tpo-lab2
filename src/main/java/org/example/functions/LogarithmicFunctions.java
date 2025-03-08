package org.example.functions;

public class LogarithmicFunctions {
    public static double ln(double x, double epsilon) {
        if (x <= 0) throw new IllegalArgumentException("x must be positive");
        if (x == 1) return 0.0;

        // Используем замену переменных для улучшения сходимости
        double t = (x - 1) / (x + 1);
        double result = 0;
        double term = t;
        double tSquared = t * t;
        int n = 1;

        while (Math.abs(term) > epsilon / 10) { // Увеличиваем точность
            result += term;
            term *= tSquared * (2 * n - 1) / (2 * n + 1);
            n++;
        }

        return 2 * result;
    }
}
