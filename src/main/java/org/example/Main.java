package org.example;

import org.example.modules.LogarithmicModule;
import org.example.modules.TrigonometricModule;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static double calculate(double x, double epsilon) {
        if (x <= 0) {
            return TrigonometricModule.calculate(x, epsilon);
        } else {
            return LogarithmicModule.calculate(x, epsilon);
        }
    }

    public static void exportToCsv(String filename, double start, double end, double step, double epsilon) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("X,Result\n");
            for (double x = start; x <= end; x += step) {
                try {
                    double result = calculate(x, epsilon);
                    writer.write(x + "," + result + "\n");
                } catch (ArithmeticException | IllegalArgumentException e) {
                    // Пропустить точки, где функция не определена
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        exportToCsv("results.csv", -1, 1, 0.1, 1e-6);
    }
}