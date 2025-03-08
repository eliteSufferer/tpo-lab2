package org.example.modules;

import org.example.functions.LogarithmicFunctions;

public class LogarithmicModule {
    private static LogarithmicFunction lnImpl = LogarithmicFunctions::ln;

    public interface LogarithmicFunction {
        double apply(double x, double epsilon);
    }

    public static void setDependency(LogarithmicFunction ln) {
        lnImpl = ln;
    }
    public static double calculate(double x, double epsilon) {
        double lnX = lnImpl.apply(x, epsilon);

        double log2X = lnX / LogarithmicFunctions.ln(2, epsilon);
        double log5X = lnX / LogarithmicFunctions.ln(5, epsilon);
        double log10X = lnX / LogarithmicFunctions.ln(10, epsilon);

        double term1 = Math.pow(Math.pow(lnX, 3), 2);
        double term2 = (log5X - log10X) * (log5X / log2X);
        return term1 + log2X - term2;
    }
}
