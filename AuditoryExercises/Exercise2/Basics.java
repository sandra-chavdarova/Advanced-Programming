package AuditoryExercises.Exercise2;

interface Operation { // funtional interface - if it has only one method
    float execute(int a, int b);
}

interface MessageProvider {
    String getMessage();
}

class Addition implements Operation {
    @Override
    public float execute(int a, int b) {
        return a + b;
    }
}

class TraditionalMessageProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "Traditional Hello";
    }
}

public class Basics {
    public static void main(String[] args) {
        int x = 5, y = 6;
        Operation addition = new Addition();
        System.out.println(addition.execute(x, y));

        // anonymous class
        Operation subtraction = new Operation() {
            @Override
            public float execute(int a, int b) {
                return a - b;
            }
        };
        System.out.println(subtraction.execute(x, y));

        // lambda expression (only for functional interfaces)
        Operation multiplication = (a, b) -> a * b;
        System.out.println(multiplication.execute(x, y));

        System.out.println("----------");

        MessageProvider temp = new TraditionalMessageProvider();

        // anonymous class
        MessageProvider anonymous = new MessageProvider() {
            @Override
            public String getMessage() {
                return "Anonymous hello";
            }
        };

        // lambda expression
        MessageProvider lambda = () -> {
            return "Lambda hello";
        };

        System.out.println(temp.getMessage());
        System.out.println(anonymous.getMessage());
        System.out.println(lambda.getMessage());
    }
}
