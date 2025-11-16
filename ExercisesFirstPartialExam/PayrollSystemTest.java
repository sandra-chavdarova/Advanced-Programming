package ExercisesFirstPartialExam;

import java.io.*;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

abstract class Employee implements Comparable<Employee> {
    private String id;
    private String level;
    private double rate;

    public Employee(String id, String level, double rate) {
        this.id = id;
        this.level = level;
        this.rate = rate;
    }

    abstract double calculateSalary();

    public String getId() {
        return id;
    }

    public String getLevel() {
        return level;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public int compareTo(Employee o) {
        return Comparator.comparing(Employee::calculateSalary, Comparator.reverseOrder())
                .thenComparing(Employee::getLevel)
                .compare(this, o);
    }

    @Override
    public String toString() {
        return String.format("Employee ID: %s Level: %s Salary: %.2f", id, level, calculateSalary());
    }
}

class HourlyEmployee extends Employee {
    private double hours;
    private double overtime;
    private double regular;

    public HourlyEmployee(String id, String level, double rate, double hours) {
        super(id, level, rate);
        this.hours = hours;
        this.overtime = Math.max(0, this.hours - 40);
        this.regular = hours - overtime;
    }

    @Override
    public double calculateSalary() {
        return regular * getRate() + overtime * getRate() * 1.5;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Regular hours: %.2f Overtime hours: %.2f", regular, overtime);
    }
}

class FreelanceEmployee extends Employee {
    private List<Integer> ticketPoints;

    public FreelanceEmployee(String id, String level, double rate, List<Integer> ticketPoints) {
        super(id, level, rate);
        this.ticketPoints = ticketPoints;
    }

    @Override
    public double calculateSalary() {
        return ticketPoints.stream().mapToInt(Integer::intValue).sum() * getRate();
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Tickets count: %d Tickets points: %d", ticketPoints.size(), ticketPoints.stream().mapToInt(Integer::intValue).sum());
    }
}

class EmployeeFactory {
    public static Employee createEmployee(String line, Map<String, Double> hourlyRate, Map<String, Double> ticketRate) {
        String[] parts = line.split(";");
        String type = parts[0];
        String id = parts[1];
        String level = parts[2];

        if (type.equalsIgnoreCase("H")) {
            double hours = Double.parseDouble(parts[3]);
            Double rate = hourlyRate.get(level);

            if (rate == null)
                throw new IllegalArgumentException("Unknown hourly level: " + level);
            return new HourlyEmployee(id, level, rate, hours);
        } else if (type.equalsIgnoreCase("F")) {
            Double rate = ticketRate.get(level);
            if (rate == null)
                throw new IllegalArgumentException("Unknown freelance level: " + level);
            List<Integer> ticketPoints = new ArrayList<>();
            for (int i = 3; i < parts.length; i++) {
                ticketPoints.add(Integer.parseInt(parts[i]));
            }
            return new FreelanceEmployee(id, level, rate, ticketPoints);
        } else
            throw new IllegalArgumentException("Unknown employee level: " + type);

    }
}

class PayrollSystem {
    private List<Employee> employees;
    private Map<String, Double> hourlyRateByLevel;
    private Map<String, Double> ticketRateByLevel;

    public PayrollSystem(Map<String, Double> hourlyRateByLevel, Map<String, Double> ticketRateByLevel) {
        this.hourlyRateByLevel = hourlyRateByLevel;
        this.ticketRateByLevel = ticketRateByLevel;
    }

    public void readEmployees(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        employees = reader.lines().filter(l -> l != null && !l.trim().isEmpty()).map(line -> EmployeeFactory.createEmployee(line, hourlyRateByLevel, ticketRateByLevel)).collect(Collectors.toList());
    }

    public Map<String, Set<Employee>> printEmployeesByLevels(OutputStream os, Set<String> levels) {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(os));
        Map<String, Set<Employee>> grouped = employees.stream().collect(Collectors.groupingBy(Employee::getLevel, (Supplier<TreeMap<String, Set<Employee>>>) TreeMap::new, Collectors.toCollection(TreeSet::new)));
        Map<String, Set<Employee>> result = new TreeMap<>();
        for (String level : levels) {
            Set<Employee> set = grouped.get(level);
            if (set != null) {
                result.put(level, set);
            }
        }
        return result;
    }
}

public class PayrollSystemTest {
    public static void main(String[] args) {
        Map<String, Double> hourlyRateByLevel = new LinkedHashMap<>();
        Map<String, Double> ticketRateByLevel = new LinkedHashMap<>();
        for (int i = 1; i <= 10; i++) {
            hourlyRateByLevel.put("level" + i, 10 + i * 2.2);
            ticketRateByLevel.put("level" + i, 5 + i * 2.5);
        }

        PayrollSystem payrollSystem = new PayrollSystem(hourlyRateByLevel, ticketRateByLevel);

        System.out.println("READING OF THE EMPLOYEES DATA");
        payrollSystem.readEmployees(System.in);

        System.out.println("PRINTING EMPLOYEES BY LEVEL");
        Set<String> levels = new LinkedHashSet<>();
        for (int i = 5; i <= 10; i++) {
            levels.add("level" + i);
        }
        Map<String, Set<Employee>> result = payrollSystem.printEmployeesByLevels(System.out, levels);
        result.forEach((level, employees) -> {
            System.out.println("LEVEL: " + level);
            System.out.println("Employees: ");
            employees.forEach(System.out::println);
            System.out.println("------------");
        });
    }
}