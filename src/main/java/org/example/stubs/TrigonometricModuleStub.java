package org.example.stubs;

public class TrigonometricModuleStub {
    public static double calculate(double x, double epsilon) {
        // Примеры предопределенных значений
        if (x == 0) return Double.NaN; // csc(0) → деление на ноль
        if (x == Math.PI / 4) return 1.0; // Упрощенный результат
        return 42.0; // Значение по умолчанию
    }
}
