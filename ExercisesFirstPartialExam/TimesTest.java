package ExercisesFirstPartialExam;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class UnsupportedFormatException extends Exception {
    private String format;

    public UnsupportedFormatException(String format) {
        this.format = format;
    }

    @Override
    public String getMessage() {
        return format;
    }
}

class InvalidTimeException extends Exception {
    private String time;

    public InvalidTimeException(String time) {
        this.time = time;
    }

    @Override
    public String getMessage() {
        return String.format("InvalidTimeException: %s", time);
    }
}

class Time {
    private int hours;
    private int minutes;

    public Time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        if (hours < 10)
            result.append(" ");
        result.append(String.format("%d:", hours));
        if (minutes < 10)
            result.append(String.format("0%d", minutes));
        else
            result.append(String.format("%d", minutes));
        return result.toString();
    }

    public String toStringAMPM() {
        String time = (hours > 11) ? "PM" : "AM";
        if (hours == 0)
            hours = 12;
        else hours = (hours > 12) ? hours - 12 : hours;

        StringBuilder result = new StringBuilder();
        if (hours < 10)
            result.append(" ");
        result.append(String.format("%d:", hours));
        if (minutes < 10)
            result.append(String.format("0%d %s", minutes, time));
        else
            result.append(String.format("%d %s", minutes, time));
        return result.toString();
    }
}

class TimeTable {
    private List<Time> timetable;

    public TimeTable() {
        this.timetable = new ArrayList<>();
    }

    public void readTimes(InputStream inputStream) throws UnsupportedFormatException, InvalidTimeException {
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            String line = scanner.next();
            String[] parts = line.split("[.:]");
            if (parts.length != 2)
                throw new UnsupportedFormatException(line);
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            if (hours > 23 || minutes > 59)
                throw new InvalidTimeException(line);
            timetable.add(new Time(hours, minutes));
            timetable.sort((a, b) -> {
                if (a.getHours() != b.getHours()) {
                    return Integer.compare(a.getHours(), b.getHours());
                } else {
                    return Integer.compare(a.getMinutes(), b.getMinutes());
                }
            });
        }
    }

    public void writeTimes(OutputStream outputStream, TimeFormat format) {
        PrintWriter writer = new PrintWriter(outputStream);
        if (format.equals(TimeFormat.FORMAT_AMPM)) {
            for (Time time : timetable)
                writer.println(time.toStringAMPM());
        } else {
            for (Time time : timetable)
                writer.println(time.toString());
        }
        writer.flush();
    }
}

public class TimesTest {
    public static void main(String[] args) {
        TimeTable timeTable = new TimeTable();
        try {
            timeTable.readTimes(System.in);
        } catch (UnsupportedFormatException e) {
            System.out.println("UnsupportedFormatException: " + e.getMessage());
        } catch (InvalidTimeException e) {
            System.out.println("InvalidTimeException: " + e.getMessage());
        }
        System.out.println("24 HOUR FORMAT");
        timeTable.writeTimes(System.out, TimeFormat.FORMAT_24);
        System.out.println("AM/PM FORMAT");
        timeTable.writeTimes(System.out, TimeFormat.FORMAT_AMPM);
    }
}

enum TimeFormat {
    FORMAT_24, FORMAT_AMPM
}