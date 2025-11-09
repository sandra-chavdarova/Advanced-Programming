// had to change from Date to LocalDateTime

package ExercisesFirstPartialExam;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

class Weather {
    private float temperature;
    private float humidity;
    private float wind;
    private float visibility;
    private LocalDateTime time;

    public Weather(float temperature, float wind, float humidity, float visibility, LocalDateTime time) {
        this.temperature = temperature;
        this.wind = wind;
        this.humidity = humidity;
        this.visibility = visibility;
        this.time = time;
    }

    public float getTemperature() {
        return temperature;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss 'GMT' yyyy");
        String date = time.format(formatter);
        return String.format("%.1f %.1f km/h %.1f%% %.1f km %s", temperature, wind, humidity, visibility, date);
    }
}

class WeatherStation {
    private List<Weather> days;
    private int numberOfDays;

    public WeatherStation(int numberOfDays) {
        this.numberOfDays = numberOfDays;
        this.days = new ArrayList<>();
    }

    public void addMeasurement(float temperature, float wind, float humidity, float visibility, LocalDateTime date) {
        days.removeIf(d -> d.getTime().isBefore(date.minusDays(numberOfDays)));
        for (Weather day : days) {
            if (Math.abs(Duration.between(day.getTime(), date).toSeconds()) < 150) {
                return;
            }
        }
        days.add(new Weather(temperature, wind, humidity, visibility, date));
    }

    public int total() {
        return days.size();
    }

    public void status(LocalDateTime from, LocalDateTime to) {
        // days = days.stream().sorted(Comparator.comparing(Weather::getTime)).collect(Collectors.toList());
        List<Weather> result = days.stream().filter(d -> !d.getTime().isBefore(from) && !d.getTime().isAfter(to)).collect(Collectors.toList());
        if (result.isEmpty())
            throw new RuntimeException();
        float sum = 0;
        for (Weather day : result) {
            sum += day.getTemperature();
            System.out.println(day);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Average temperature: %.2f", sum / result.size()));
        System.out.println(sb.toString());
    }
}

public class WeatherStationTest {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        int n = scanner.nextInt();
        scanner.nextLine();
        WeatherStation ws = new WeatherStation(n);
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("=====")) {
                break;
            }
            String[] parts = line.split(" ");
            float temp = Float.parseFloat(parts[0]);
            float wind = Float.parseFloat(parts[1]);
            float hum = Float.parseFloat(parts[2]);
            float vis = Float.parseFloat(parts[3]);
            line = scanner.nextLine();
            LocalDateTime date = LocalDateTime.parse(line, df);
            ws.addMeasurement(temp, wind, hum, vis, date);
        }
        String line = scanner.nextLine();
        LocalDateTime from = LocalDateTime.parse(line, df);
        line = scanner.nextLine();
        LocalDateTime to = LocalDateTime.parse(line, df);
        scanner.close();
        System.out.println(ws.total());
        try {
            ws.status(from, to);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
    }
}
