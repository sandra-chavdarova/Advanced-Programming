package ExercisesFirstPartialExam;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Window {
    private String id;
    private List<Integer> sides;

    public Window(String id, List<Integer> sides) {
        this.id = id;
        this.sides = sides;
    }

    public String getId() {
        return id;
    }

    public List<Integer> getSides() {
        return sides;
    }

    public int sumOfPerimeters() {
        int sum = 0;
        for (Integer side : sides) {
            sum += side * 4;
        }
        return sum;
    }

    @Override
    public String toString() {
        return String.format("%s %d %d", id, sides.size(), sumOfPerimeters());
    }
}

class ShapesApplication {
    private List<Window> windows;

    public ShapesApplication() {
        windows = new ArrayList<>();
    }

    public int readCanvases(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split("\\s+");
            String id = parts[0];
            List<Integer> sides = new ArrayList<>();
            for (int i = 1; i < parts.length; i++) {
                sides.add(Integer.parseInt(parts[i]));
            }
            windows.add(new Window(id, sides));
        }
        return windows.stream().mapToInt(w -> w.getSides().size()).sum();
    }

    public void printLargestCanvasTo(OutputStream outputStream) {
        PrintWriter writer = new PrintWriter(outputStream);
        Window result = windows.stream().max(Comparator.comparingInt(Window::sumOfPerimeters)).orElse(null);
        writer.println(result.toString());
        writer.flush();
    }
}

public class ShapesTest {
    public static void main(String[] args) throws IOException {
        ShapesApplication shapesApplication = new ShapesApplication();

        System.out.println("===READING SQUARES FROM INPUT STREAM===");
        System.out.println(shapesApplication.readCanvases(System.in));
        System.out.println("===PRINTING LARGEST CANVAS TO OUTPUT STREAM===");
        shapesApplication.printLargestCanvasTo(System.out);
    }
}