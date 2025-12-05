package LaboratoryExercises.Lab04;

import java.util.*;
import java.util.stream.Collectors;

class Student implements Comparable<Student> {
    private String id;
    private List<Integer> grades;

    public Student(String id, List<Integer> grades) {
        this.id = id;
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Student{id='" + id + "', grades=" + grades + "}";
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public String getId() {
        return id;
    }

    @Override
    public int compareTo(Student o) {
        int comparison1 = Double.compare(o.average(), this.average());
        if (comparison1 == 0) {
            int comparison2 = Integer.compare(o.passedSubjects(), this.passedSubjects());
            if (comparison2 == 0)
                return this.id.compareTo(o.id);
            return comparison2;
        }
        return comparison1;
    }

    public double average() {
        return (double) grades.stream().mapToInt(Integer::intValue).sum() / grades.size();
    }

    public int passedSubjects() {
        return (int) grades.stream().filter(g -> g > 5).count();
    }
}

class Faculty {
    private Map<String, Student> students;

    public Faculty() {
        this.students = new TreeMap<>();
    }

    public void addStudent(String id, List<Integer> grades) {
        if (students.containsKey(id))
            throw new IllegalArgumentException("Student with ID " + id + " already exists");
        students.put(id, new Student(id, grades));
    }

    public void addGrade(String id, int grade) {
        Student student = students.get(id);
        student.getGrades().add(grade);
        students.put(id, student);
    }

    public Set<Student> getStudentsSortedByAverageGrade() {
        return new TreeSet<>(students.values());
    }

    public Set<Student> getStudentsSortedByCoursesPassed() {
        return students.values().stream().sorted(Comparator.comparingInt(Student::passedSubjects).reversed()
                        .thenComparing(Comparator.comparingDouble(Student::average).reversed())
                        .thenComparing(Student::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}

public class SetsTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Faculty faculty = new Faculty();

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            String[] tokens = input.split("\\s+");
            String command = tokens[0];

            switch (command) {
                case "addStudent":
                    String id = tokens[1];
                    List<Integer> grades = new ArrayList<>();
                    for (int i = 2; i < tokens.length; i++) {
                        grades.add(Integer.parseInt(tokens[i]));
                    }
                    try {
                        faculty.addStudent(id, grades);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "addGrade":
                    String studentId = tokens[1];
                    int grade = Integer.parseInt(tokens[2]);
                    faculty.addGrade(studentId, grade);
                    break;

                case "getStudentsSortedByAverageGrade":
                    System.out.println("Sorting students by average grade");
                    Set<Student> sortedByAverage = faculty.getStudentsSortedByAverageGrade();
                    for (Student student : sortedByAverage) {
                        System.out.println(student);
                    }
                    break;

                case "getStudentsSortedByCoursesPassed":
                    System.out.println("Sorting students by courses passed");
                    Set<Student> sortedByCourses = faculty.getStudentsSortedByCoursesPassed();
                    for (Student student : sortedByCourses) {
                        System.out.println(student);
                    }
                    break;

                default:
                    break;
            }
        }
        scanner.close();
    }
}
