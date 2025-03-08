package org.example.modules;

import org.example.functions.TrigonometricFunctions;

public class TrigonometricModule {
    private static TrigonometricFunction sinImpl = TrigonometricFunctions::sin;
    private static TrigonometricFunction cosImpl = TrigonometricFunctions::cos;

    public interface TrigonometricFunction {
        double apply(double x, double epsilon);
    }

    public static void setDependencies(TrigonometricFunction sin, TrigonometricFunction cos) {
        sinImpl = sin;
        cosImpl = cos;
    }
    public static double calculate(double x, double epsilon) {

        double sinX = sinImpl.apply(x, epsilon);
        double cosX = cosImpl.apply(x, epsilon);

        if (Math.abs(sinX) < epsilon) {
            throw new ArithmeticException("Деление на ноль: sin(x) = 0");
        }

        if (Math.abs(cosX) < epsilon) {
            return Double.NaN;
        }

        double cscX = 1 / sinX;
        double secX = 1 / cosX;

        double term1 = Math.pow(cscX / secX, 3) * sinX;
        double term2 = Math.pow(secX, 2);
        return (term1 - term2) + cscX;
    }
}