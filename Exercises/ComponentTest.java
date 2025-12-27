package Exercises;

import java.util.*;

class InvalidPositionException extends Exception {
    public InvalidPositionException(int position) {
        super(String.format("Invalid position %d, alredy taken!", position));
    }
}

class Indent {
    public static String getIndent(int level) {
        return "---".repeat(level);
    }
}

class Component {
    private String color;
    private int weight;
    private List<Component> components;

    public Component(String color, int weight) {
        this.color = color;
        this.weight = weight;
        this.components = new ArrayList<>();
    }

    public void addComponent(Component component) {
        this.components.add(component);
        this.components.sort(Comparator.comparingDouble(Component::getWeight).thenComparing(Component::getColor));
    }

    public void changeColor(int weight, String color) {
        if (this.weight < weight) {
            this.color = color;
        }
        for (Component component : components) {
            component.changeColor(weight, color);
        }
    }

    public String getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }

    public String toString(int level) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s%d:%s", Indent.getIndent(level), getWeight(), getColor()));
        for (Component component : components)
            sb.append("\n").append(component.toString(level + 1));
        return sb.toString();
    }
}

class Window {
    private String name;
    private Map<Integer, Component> components;

    public Window(String name) {
        this.name = name;
        this.components = new TreeMap<>();
    }

    void addComponent(int position, Component component) throws InvalidPositionException {
        if (components.get(position) != null) {
            throw new InvalidPositionException(position);
        }
        components.put(position, component);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("WINDOW %s\n", name));
        for (Integer position : components.keySet()) {
            sb.append(String.format("%d:%s\n", position, components.get(position).toString(0)));
        }
        return sb.toString();
    }

    public void changeColor(int weight, String color) {
        for (Component component : components.values()) {
            component.changeColor(weight, color);
        }
    }

    public void switchComponents(int pos1, int pos2) {
        Component temp = components.get(pos1);
        components.put(pos1, components.get(pos2));
        components.put(pos2, temp);
    }
}

public class ComponentTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Window window = new Window(name);
        Component prev = null;
        while (true) {
            try {
                int what = scanner.nextInt();
                scanner.nextLine();
                if (what == 0) {
                    int position = scanner.nextInt();
                    window.addComponent(position, prev);
                } else if (what == 1) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev = component;
                } else if (what == 2) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev.addComponent(component);
                    prev = component;
                } else if (what == 3) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev.addComponent(component);
                } else if (what == 4) {
                    break;
                }

            } catch (InvalidPositionException e) {
                System.out.println(e.getMessage());
            }
            scanner.nextLine();
        }

        System.out.println("=== ORIGINAL WINDOW ===");
        System.out.println(window);
        int weight = scanner.nextInt();
        scanner.nextLine();
        String color = scanner.nextLine();
        window.changeColor(weight, color);
        System.out.println(String.format("=== CHANGED COLOR (%d, %s) ===", weight, color));
        System.out.println(window);
        int pos1 = scanner.nextInt();
        int pos2 = scanner.nextInt();
        System.out.println(String.format("=== SWITCHED COMPONENTS %d <-> %d ===", pos1, pos2));
        window.switchComponents(pos1, pos2);
        System.out.println(window);
    }
}