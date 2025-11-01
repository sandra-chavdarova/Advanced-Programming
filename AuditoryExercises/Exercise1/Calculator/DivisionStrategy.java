package AuditoryExercises.Exercise1.Calculator;

public class DivisionStrategy implements CalculateStrategy {
    @Override
    public double calculate(double a, double b) {
        if (b != 0)
            return a / b;
        return a;
    }
}
