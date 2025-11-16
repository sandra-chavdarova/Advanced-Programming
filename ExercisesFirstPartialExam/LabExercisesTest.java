package ExercisesFirstPartialExam;

import java.util.*;
import java.util.stream.Collectors;

class Student implements Comparable<Student> {
    private String index;
    private List<Integer> points;
    private int year;

    public Student(String index, List<Integer> points) {
        this.index = index;
        this.points = points;
        this.year = 20 - Integer.parseInt(index) / 10000;
    }

    public double averagePoints() {
        return points.stream().mapToDouble(Integer::intValue).sum() / 10.0;
    }

    public int sumPoints() {
        return points.stream().mapToInt(Integer::intValue).sum();
    }

    public boolean getsSignature() {
        return points.size() >= 8;
    }

    public String getIndex() {
        return index;
    }

    public int getYear() {
        return year;
    }

    @Override
    public int compareTo(Student o) {
        int comparison = this.index.compareToIgnoreCase(o.index);
        if (comparison == 0)
            return Double.compare(this.averagePoints(), o.averagePoints());
        return comparison;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", index, getsSignature() ? "YES" : "NO", averagePoints());
    }
}

class LabExercises {
    private List<Student> students;

    public LabExercises() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void printByAveragePoints(boolean ascending, int n) {
        List<Student> sorted = new ArrayList<>();
        if (ascending)
            sorted = students.stream().sorted(Comparator.comparing(Student::averagePoints).thenComparing(Student::getIndex)).limit(n).collect(Collectors.toList());
        else
            sorted = students.stream().sorted(Comparator.comparing(Student::averagePoints).reversed().thenComparing(Comparator.comparing(Student::getIndex).reversed())).limit(n).collect(Collectors.toList());
        for (Student student : sorted) {
            System.out.println(student);
        }
    }

    public List<Student> failedStudents() {
        List<Student> filtered = students.stream().filter(s -> !s.getsSignature()).collect(Collectors.toList());
        filtered.sort(null);
        return filtered;
    }

    public Map<Integer, Double> getStatisticsByYear() {
        Map<Integer, Double> map = new TreeMap<>();
        List<Student> passed = students.stream().filter(Student::getsSignature).collect(Collectors.toList());
        map = passed.stream().collect(Collectors.groupingBy(
                Student::getYear,
                TreeMap::new,
                Collectors.averagingDouble(Student::averagePoints)
        ));
        return map;
    }
}
public class LabExercisesTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LabExercises labExercises = new LabExercises();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] parts = input.split("\\s+");
            String index = parts[0];
            List<Integer> points = Arrays.stream(parts).skip(1)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());

            labExercises.addStudent(new Student(index, points));
        }

        System.out.println("===printByAveragePoints (ascending)===");
        labExercises.printByAveragePoints(true, 100);
        System.out.println("===printByAveragePoints (descending)===");
        labExercises.printByAveragePoints(false, 100);
        System.out.println("===failed students===");
        labExercises.failedStudents().forEach(System.out::println);
        System.out.println("===statistics by year");
        labExercises.getStatisticsByYear().entrySet().stream()
                .map(entry -> String.format("%d : %.2f", entry.getKey(), entry.getValue()))
                .forEach(System.out::println);

    }
}