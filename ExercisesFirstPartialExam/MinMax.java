package ExercisesFirstPartialExam;

import java.util.Scanner;

public class MinMax<T extends Comparable<T>> {
    private T min;
    private T max;

    private int updates;
    private int counterMin;
    private int counterMax;

    public MinMax() {
        this.min = null;
        this.max = null;

        this.updates = 0;
        this.counterMax = 0;
        this.counterMin = 0;
    }

    public void update(T element) {
        if (min == null || min.compareTo(element) > 0) {
            counterMin = 1;
            min = element;
        } else if (min.compareTo(element) == 0) {
            counterMin++;
        }
        if (max == null || max.compareTo(element) < 0) {
            counterMax = 1;
            max = element;
        } else if (max.compareTo(element) == 0) {
            counterMax++;
        }
        updates++;
    }

    public T max() {
        return max;
    }

    public T min() {
        return min;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d\n", min(), max(), updates - counterMax - counterMin);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        MinMax<String> strings = new MinMax<String>();
        for (int i = 0; i < n; ++i) {
            String s = scanner.next();
            strings.update(s);
        }
        System.out.println(strings);
        MinMax<Integer> ints = new MinMax<Integer>();
        for (int i = 0; i < n; ++i) {
            int x = scanner.nextInt();
            ints.update(x);
        }
        System.out.println(ints);
    }
}
