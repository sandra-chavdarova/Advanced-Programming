package AuditoryExercises.Exercise1.Calculator;

import java.util.Scanner;

public class Calculator {
    private double state;

    public Calculator() {
        this.state = 0;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String line = scanner.nextLine();
            if (line.toLowerCase().startsWith("n")) {
                System.out.println("Final result: " + calculator.state);
                break;
            } else if (line.toLowerCase().startsWith("y")) {
                calculator.state = 0;
                System.out.println("Result: " + calculator.state);
                continue;
            } else if (line.toLowerCase().startsWith("r")) {
                System.out.println("Final result: " + calculator.state);
                continue;
            } else if (!line.toLowerCase().matches("[+\\-*/]\\s*\\d+")) {
                System.out.println("Invalid input");
                continue;
            }

            String[] parts = line.split("\\s+");
            char operator = parts[0].charAt(0);
            double amount = Double.parseDouble(parts[1]);

            CalculateStrategy strategy = null;
            if (operator == '+') {
                strategy = new AdditionStrategy();
            } else if (operator == '-') {
                strategy = new SubtractionStrategy();
            } else if (operator == '*') {
                strategy = new MultiplicationStrategy();
            } else if (operator == '/') {
                strategy = new DivisionStrategy();
            } else {
                System.out.println("Invalid operation");
                continue;
            }
            calculator.state = strategy.calculate(calculator.state, amount);
            System.out.println("Result: " + calculator.state);
        }
    }
}
