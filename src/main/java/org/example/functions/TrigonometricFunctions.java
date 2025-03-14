package org.example.functions;

public class TrigonometricFunctions {
    /**
     * Вычисление sin(x) через ряд Тейлора
     */
    public static double sin(double x, double epsilon) {
        // Нормализация x в диапазон [-2π, 2π] для улучшения точности
        x = x % (2 * Math.PI);
        
        double result = 0;
        double term = x;
        int n = 1;
        while (Math.abs(term) >= epsilon) {
            result += term;
            term *= -x * x / ((2 * n) * (2 * n + 1));
            n++;
        }
        return result;
    }

    /**
     * Вычисление cos(x) через sin(x)
     */
    public static double cos(double x, double epsilon) {
        // cos(x) = sin(x + π/2)
        return sin(x + Math.PI / 2, epsilon);
    }

    /**
     * Вычисление sec(x) через cos(x)
     */
    public static double sec(double x, double epsilon) {
        double cosX = cos(x, epsilon);
        if (Math.abs(cosX) < epsilon) {
            throw new ArithmeticException("Секанс не определен при x = π/2 + πn");
        }
        return 1 / cosX;
    }

    /**
     * Вычисление csc(x) через sin(x)
     */
    public static double csc(double x, double epsilon) {
        double sinX = sin(x, epsilon);
        if (Math.abs(sinX) < epsilon) {
            throw new ArithmeticException("Косеканс не определен при x = πn");
        }
        return 1 / sinX;
    }
}
