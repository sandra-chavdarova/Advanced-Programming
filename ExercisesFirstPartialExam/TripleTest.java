package ExercisesFirstPartialExam;

import java.util.Scanner;

class Triple<T extends Number> {
    private T number1;
    private T number2;
    private T number3;

    public Triple(T number1, T number2, T number3) {
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
    }

    public double max() {
        double result = number1.doubleValue();
        if (number2.doubleValue() > result)
            result = number2.doubleValue();
        if (number3.doubleValue() > result)
            result = number3.doubleValue();
        return result;
    }

    public double average() {
        return (number1.doubleValue() + number2.doubleValue() + number3.doubleValue()) / 3;
    }

    public void sort() {
        if (number2.doubleValue() < number1.doubleValue()) {
            T local = number1;
            number1 = number2;
            number2 = local;
        }
        if (number3.doubleValue() < number1.doubleValue()) {
            T local = number1;
            number1 = number3;
            number3 = local;
        }
        if (number3.doubleValue() < number2.doubleValue()) {
            T local = number3;
            number3 = number2;
            number2 = local;
        }
    }

    @Override
    public String toString() {
        return String.format("%.2f %.2f %.2f", number1.doubleValue(), number2.doubleValue(), number3.doubleValue());
    }
}

public class TripleTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        Triple<Integer> tInt = new Triple<Integer>(a, b, c);
        System.out.printf("%.2f\n", tInt.max());
        System.out.printf("%.2f\n", tInt.average());
        tInt.sort();
        System.out.println(tInt);
        float fa = scanner.nextFloat();
        float fb = scanner.nextFloat();
        float fc = scanner.nextFloat();
        Triple<Float> tFloat = new Triple<Float>(fa, fb, fc);
        System.out.printf("%.2f\n", tFloat.max());
        System.out.printf("%.2f\n", tFloat.average());
        tFloat.sort();
        System.out.println(tFloat);
        double da = scanner.nextDouble();
        double db = scanner.nextDouble();
        double dc = scanner.nextDouble();
        Triple<Double> tDouble = new Triple<Double>(da, db, dc);
        System.out.printf("%.2f\n", tDouble.max());
        System.out.printf("%.2f\n", tDouble.average());
        tDouble.sort();
        System.out.println(tDouble);
    }
}
