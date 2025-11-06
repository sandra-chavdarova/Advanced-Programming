package ExercisesFirstPartialExam;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class IrregularCanvasException extends Exception {
    private String id;
    private double maxArea;

    public IrregularCanvasException(String id, double maxArea) {
        this.id = id;
        this.maxArea = maxArea;
    }

    @Override
    public String getMessage() {
        return String.format("Canvas %s has a shape with area larger than %.2f", id, maxArea);
    }
}

class Window {
    private String type;
    private int size;

    public Window(String type, int size) {
        this.size = size;
        this.type = type;
    }

    public double area() {
        if (type.equals("C"))
            return Math.PI * Math.pow(size, 2);
        return Math.pow(size, 2);
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }
}

class Canvas {
    private String id;
    private List<Window> windows;

    public Canvas(String id, List<Window> windows) {
        this.id = id;
        this.windows = windows;
    }

    public List<Window> getWindows() {
        return windows;
    }

    public double sumAreas() {
        return windows.stream().mapToDouble(Window::area).sum();
    }

    public int totalShapes() {
        return windows.size();
    }

    public int totalCircles() {
        return windows.stream().filter(w -> w.getType().equals("C")).collect(Collectors.toList()).size();
    }

    public int totalSquares() {
        return totalShapes() - totalCircles();
    }

    public double minArea() {
        return windows.stream().mapToDouble(Window::area).min().orElse(0);
    }

    public double maxArea() {
        return windows.stream().mapToDouble(Window::area).max().orElse(0);
    }

    public double averageArea() {
        return sumAreas() / totalShapes();
    }

    @Override
    public String toString() {
        return String.format("%s %d %d %d %.2f %.2f %.2f", this.id, totalShapes(), totalCircles(), totalSquares(), minArea(), maxArea(), averageArea());
    }
}

class ShapesApplication {
    private double maxArea;
    private List<Canvas> canvases;

    public ShapesApplication(double maxArea) {
        this.maxArea = maxArea;
        this.canvases = new ArrayList<>();
    }

    public void readCanvases(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split("\\s+");
            String id = parts[0];
            List<Window> windows = new ArrayList<>();
            for (int i = 1; i < parts.length; i += 2) {
                windows.add(new Window(parts[i].toUpperCase(), Integer.parseInt(parts[i + 1])));
            }
            Canvas canvas = new Canvas(id, windows);
            try {
                if (canvas.getWindows().stream().filter(w -> w.area() > maxArea).count() > 0)
                    throw new IrregularCanvasException(id, maxArea);
                else
                    canvases.add(canvas);
            } catch (IrregularCanvasException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void printCanvases(OutputStream outputStream) {
        PrintWriter writer = new PrintWriter(outputStream);
        canvases.stream().sorted((c1, c2) -> Double.compare(c2.sumAreas(), c1.sumAreas())).forEach(writer::println);
        writer.flush();
    }
}

public class ShapesApplicationTest {
    public static void main(String[] args) {
        ShapesApplication shapesApplication = new ShapesApplication(10000);

        System.out.println("===READING CANVASES AND SHAPES FROM INPUT STREAM===");
        shapesApplication.readCanvases(System.in);
        System.out.println("===PRINTING SORTED CANVASES TO OUTPUT STREAM===");
        shapesApplication.printCanvases(System.out);
    }
}