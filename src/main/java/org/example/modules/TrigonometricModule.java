package org.example.modules;

import org.example.functions.TrigonometricFunctions;

public class TrigonometricModule {
    private static TrigonometricFunction sinImpl = TrigonometricFunctions::sin;

    public interface TrigonometricFunction {
        double apply(double x, double epsilon);
    }

    public static void setDependency(TrigonometricFunction sin) {
        sinImpl = sin;
    }
    public static double calculate(double x, double epsilon) {

        double sinX = sinImpl.apply(x, epsilon);
        double cosX = TrigonometricFunctions.cos(x, epsilon);

        if (Math.abs(sinX) < epsilon) {
            throw new ArithmeticException("Деление на ноль: sin(x) = 0");
        }

        if (Math.abs(cosX) < epsilon) {
            return Double.NaN;
        }

        double cscX = TrigonometricFunctions.csc(x, epsilon);
        double secX = TrigonometricFunctions.sec(x, epsilon);

        double term1 = Math.pow(cscX / secX, 3) * sinX;
        double term2 = Math.pow(secX, 2);
        return (term1 - term2) + cscX;
    }
}