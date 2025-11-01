package AuditoryExercises.Exercise1.Calculator;

public class MultiplicationStrategy implements CalculateStrategy {
    @Override
    public double calculate(double a, double b) {
        return a * b;
    }
}
