package AuditoryExercises.Exercise2;

import java.util.function.*;

public class FunctionalInterfaces {
    public static void main(String[] args) {
        // Function
        Function<String, Integer> function = str -> str.length();
        System.out.println(function.apply("Sandra"));

        BiFunction<Integer, Integer, Integer> biFunction = (a, b) -> a + b;
        System.out.println(biFunction.apply(5, 10));

        // Predicate
        Predicate<Integer> isEven = number -> number % 2 == 0;
        System.out.println(isEven.test(5));
        System.out.println(isEven.test(6));

        // Supplier
        Supplier<Long> currentTimeInMs = () -> System.currentTimeMillis();
        System.out.println(currentTimeInMs.get());

        // Consumer
        Consumer<String> printer = str -> System.out.println(str);
        printer.accept("NP");
    }
}
