package org.example.functions;

public class LogarithmicFunctions {
    /**
     * Вычисление натурального логарифма ln(x) через ряд
     */
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

    /**
     * Вычисление логарифма по основанию b через ln
     */
    public static double log(double base, double x, double epsilon) {
        if (base <= 0 || base == 1) 
            throw new IllegalArgumentException("Base must be positive and not equal to 1");
        return ln(x, epsilon) / ln(base, epsilon);
    }

    /**
     * Вычисление логарифма по основанию 2
     */
    public static double log2(double x, double epsilon) {
        return log(2, x, epsilon);
    }

    /**
     * Вычисление логарифма по основанию 5
     */
    public static double log5(double x, double epsilon) {
        return log(5, x, epsilon);
    }

    /**
     * Вычисление логарифма по основанию 10
     */
    public static double log10(double x, double epsilon) {
        return log(10, x, epsilon);
    }
}
