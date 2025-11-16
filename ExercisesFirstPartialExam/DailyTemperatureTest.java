package ExercisesFirstPartialExam;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class DailyMeasurement implements Comparable<DailyMeasurement> {
    private int day;
    private List<Double> temperatures;

    public DailyMeasurement(int day, List<Double> temperatures, String metric) {
        this.day = day;
        this.temperatures = temperatures;
    }

    public static DailyMeasurement createMeasurement(String line) {
        String[] parts = line.split(" ");
        int day = Integer.parseInt(parts[0]);
        List<Double> temperatures = new ArrayList<>();
        String metric = "";
        if (line.contains("C")) {
            metric = "C";
            for (int i = 1; i < parts.length; i++) {
                temperatures.add(Double.parseDouble(parts[i].split("C")[0]));
            }
        } else if (line.contains("F")) {
            metric = "F";
            for (int i = 1; i < parts.length; i++) {
                temperatures.add(toCelsius(Double.parseDouble(parts[i].split("F")[0])));
            }
        }
        return new DailyMeasurement(day, temperatures, metric);
    }

    public static double toFahrenheit(double t) {
        return (t * 9.0) / 5 + 32;
    }

    public static double toCelsius(double t) {
        return (t - 32) * 5 / 9.0;
    }

    public static String dailyMeasurement(DailyMeasurement dailyMeasurement, char scale) {
        List<Double> temps = dailyMeasurement.temperatures;
        if (scale == 'F') {
            temps = temps.stream().map(DailyMeasurement::toFahrenheit).collect(Collectors.toList());
        }

        int count = temps.size();
        double min = temps.stream().min(Double::compare).orElse(0.0);
        double max = temps.stream().max(Double::compare).orElse(0.0);
        double sum = temps.stream().mapToDouble(Double::doubleValue).sum();
        double average = sum / count;

        return String.format("%3d: Count: %3d Min: %6.2f%c Max: %6.2f%c Avg: %6.2f%c", dailyMeasurement.day, count, min, scale, max, scale, average, scale);
    }

    @Override
    public int compareTo(DailyMeasurement o) {
        return Integer.compare(this.day, o.day);
    }
}

class DailyTemperatures {
    private List<DailyMeasurement> measurements;

    public DailyTemperatures() {
        this.measurements = new ArrayList<>();
    }

    public void readTemperatures(InputStream inputStream) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        measurements = bf.lines().filter(Objects::nonNull)
                .map(DailyMeasurement::createMeasurement)
                .collect(Collectors.toList());
    }

    public void writeDailyStats(OutputStream outputStream, char scale) {
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(outputStream));
        measurements.stream().sorted().forEach(m -> pw.println(DailyMeasurement.dailyMeasurement(m, scale)));
        pw.flush();
    }
}

public class DailyTemperatureTest {
    public static void main(String[] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        dailyTemperatures.readTemperatures(System.in);
        System.out.println("=== Daily temperatures in Celsius (C) ===");
        dailyTemperatures.writeDailyStats(System.out, 'C');
        System.out.println("=== Daily temperatures in Fahrenheit (F) ===");
        dailyTemperatures.writeDailyStats(System.out, 'F');
    }
}
