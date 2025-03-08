package org.example.functions;

public class TrigonometricFunctions {
    // Вычисление sin(x) через ряд Тейлора
    public static double sin(double x, double epsilon) {
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

    // Вычисление cos(x) через ряд Тейлора
    public static double cos(double x, double epsilon) {
        double result = 0;
        double term = 1;
        int n = 0;
        while (Math.abs(term) >= epsilon) {
            result += term;
            term *= -x * x / ((2 * n + 1) * (2 * n + 2));
            n++;
        }
        return result;
    }
}
