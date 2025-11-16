package ExercisesFirstPartialExam;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class Driver implements Comparable<Driver> {
    private String name;
    private List<String> laps;

    public Driver(String name, String lap1, String lap2, String lap3) {
        this.name = name;
        this.laps = new ArrayList<>();
        laps.add(lap1);
        laps.add(lap2);
        laps.add(lap3);
    }

    private static int calculateTime(String lap) {
        String[] parts = lap.split(":");
        return Integer.parseInt(parts[0]) * 60 * 1000 + Integer.parseInt(parts[1]) * 1000 + Integer.parseInt(parts[2]);
    }

    private String getBestLap() {
        int minLap = laps.stream().mapToInt(Driver::calculateTime).min().orElse(0);
        return laps.stream().filter(lap -> calculateTime(lap) == minLap).findFirst().orElseGet(() -> laps.get(0));
    }

    @Override
    public String toString() {
        return String.format("%-10s%10s", name, getBestLap());
    }

    @Override
    public int compareTo(Driver o) {
        return calculateTime(this.getBestLap()) - calculateTime(o.getBestLap());
    }

    public static Driver createDriver(String line) {
        String[] parts = line.split("\\s+");
        return new Driver(parts[0], parts[1], parts[2], parts[3]);
    }
}

class F1Race {
    private List<Driver> drivers;

    public F1Race() {
        this.drivers = new ArrayList<>();
    }

    public void readResults(InputStream inputStream) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        drivers = bf.lines().filter(Objects::nonNull).map(Driver::createDriver).collect(Collectors.toList());
    }

    public void printSorted(OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(outputStream));
//        Collections.sort(drivers);
        drivers = drivers.stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < drivers.size(); i++) {
            pw.println(i + 1 + ". " + drivers.get(i));
        }
        pw.flush();
    }
}

public class F1Test {
    public static void main(String[] args) {
        F1Race f1Race = new F1Race();
        f1Race.readResults(System.in);
        f1Race.printSorted(System.out);
    }
}

