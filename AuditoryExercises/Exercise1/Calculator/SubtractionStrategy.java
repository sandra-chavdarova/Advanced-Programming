package AuditoryExercises.Exercise1.Calculator;

public class SubtractionStrategy implements CalculateStrategy {
    @Override
    public double calculate(double a, double b) {
        return a - b;
    }
}
