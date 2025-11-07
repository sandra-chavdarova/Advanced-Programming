package ExercisesFirstPartialExam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum Color {
    RED, GREEN, BLUE
}

interface Scalable {
    void scale(float scaleFactor);
}

interface Stackable extends Comparable<Stackable> {
    float weight();

    @Override
    default int compareTo(Stackable o) {
        return Float.compare(o.weight(), weight());
    }
}

class Shape implements Scalable, Stackable {
    private String id;
    private Color color;

    public Shape(String id, Color color) {
        this.id = id;
        this.color = color;
    }

    @Override
    public void scale(float scaleFactor) {

    }

    @Override
    public float weight() {
        return 0;
    }

    public String getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }
}

class Circle extends Shape {
    private float radius;

    public Circle(String id, Color color, float radius) {
        super(id, color);
        this.radius = radius;
    }

    @Override
    public void scale(float scaleFactor) {
        radius *= scaleFactor;
    }

    @Override
    public float weight() {
        return (float) ((float) Math.pow(radius, 2) * Math.PI);
    }

    @Override
    public String toString() {
        return String.format("C: %-5s%-10s%10.2f", this.getId(), this.getColor().toString(), this.weight());
    }
}

class Rectangle extends Shape {
    private float width;
    private float height;

    public Rectangle(String id, Color color, float width, float height) {
        super(id, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void scale(float scaleFactor) {
        width *= scaleFactor;
        height *= scaleFactor;
    }

    @Override
    public float weight() {
        return width * height;
    }

    @Override
    public String toString() {
        return String.format("R: %-5s%-10s%10.2f", this.getId(), this.getColor().toString(), this.weight());
    }
}

class Canvas {
    private List<Shape> shapes;

    public Canvas() {
        this.shapes = new ArrayList<>();
    }

    public void add(String id, Color color, float radius) {
        Circle circle = new Circle(id, color, radius);
        shapes.add(circle);
        sortShapes();
    }

    public void add(String id, Color color, float width, float height) {
        Rectangle rectangle = new Rectangle(id, color, width, height);
        shapes.add(rectangle);
        sortShapes();
    }

    public void scale(String id, float scaleFactor) {
        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {
                shape.scale(scaleFactor);
            }
        }
        sortShapes();
    }

    private void sortShapes() {
        for (int i = 0; i < shapes.size() - 1; i++) {
            for (int j = 0; j < shapes.size() - 1; j++) {
                Shape s1 = shapes.get(j);
                Shape s2 = shapes.get(j + 1);
                if (s1.weight() > s2.weight()) {
                    shapes.set(j, s2);
                    shapes.set(j + 1, s1);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Shape shape : shapes) {
            sb.append(shape).append("\n");
        }
        return sb.toString();
    }
}

public class Shapes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Canvas canvas = new Canvas();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            int type = Integer.parseInt(parts[0]);
            String id = parts[1];
            if (type == 1) {
                Color color = Color.valueOf(parts[2]);
                float radius = Float.parseFloat(parts[3]);
                canvas.add(id, color, radius);
            } else if (type == 2) {
                Color color = Color.valueOf(parts[2]);
                float width = Float.parseFloat(parts[3]);
                float height = Float.parseFloat(parts[4]);
                canvas.add(id, color, width, height);
            } else if (type == 3) {
                float scaleFactor = Float.parseFloat(parts[2]);
                System.out.println("ORIGNAL:");
                System.out.print(canvas);
                canvas.scale(id, scaleFactor);
                System.out.printf("AFTER SCALING: %s %.2f\n", id, scaleFactor);
                System.out.print(canvas);
            }
        }
    }
}
