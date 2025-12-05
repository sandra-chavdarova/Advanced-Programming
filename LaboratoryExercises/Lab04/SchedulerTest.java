package LaboratoryExercises.Lab04;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Scheduler<T> {
    private Map<LocalDateTime, T> scheduler;

    public Scheduler() {
        this.scheduler = new TreeMap<>();
    }

    public void add(LocalDateTime date, T t) {
        scheduler.put(date, t);
    }

    public boolean remove(LocalDateTime date) {
        if (scheduler.containsKey(date)) {
            scheduler.remove(date);
            return true;
        }
        return false;
    }

    public T next() {
        for (LocalDateTime date : scheduler.keySet()) {
            if (date.isAfter(LocalDateTime.now()))
                return scheduler.get(date);
        }
        return null;
    }

    public T last() {
        T result = null;
        for (LocalDateTime date : scheduler.keySet()) {
            if (date.isBefore(LocalDateTime.now()))
                result = scheduler.get(date);
        }
        return result;
    }

    public ArrayList<T> getAll(LocalDateTime begin, LocalDateTime end) {
        ArrayList<T> result = new ArrayList<>();
        for (LocalDateTime date : scheduler.keySet()) {
            if (date.isAfter(begin) && date.isBefore(end))
                result.add(scheduler.get(date));
        }
        return result;
    }

    public T getFirst() {
        Set<LocalDateTime> keys = new TreeSet<>();
        keys = scheduler.keySet();
        return scheduler.get(keys.stream().findFirst().orElse(null));
    }

    public T getLast() {
        LocalDateTime lastKey = scheduler.keySet().stream().reduce((first, second) -> second).orElse(null);
        return scheduler.get(lastKey);
    }
}

public class SchedulerTest {
    private static final DateTimeFormatter DF =
            DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss 'GMT' yyyy", Locale.ENGLISH);

    private static String format(LocalDateTime t) {
        return t.atZone(ZoneId.of("GMT")).format(DF);
    }

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();


        if (k == 0) {
            Scheduler<String> scheduler = new Scheduler<>();
            LocalDateTime now = LocalDateTime.now();

            scheduler.add(now.minusHours(2), jin.next());
            scheduler.add(now.minusHours(1), jin.next());
            scheduler.add(now.minusHours(4), jin.next());
            scheduler.add(now.plusHours(2), jin.next());
            scheduler.add(now.plusHours(4), jin.next());
            scheduler.add(now.plusHours(1), jin.next());
            scheduler.add(now.plusHours(5), jin.next());

            System.out.println(scheduler.getFirst());
            System.out.println(scheduler.getLast());
        }

        if (k == 3) { // test Scheduler with String
            Scheduler<String> scheduler = new Scheduler<>();
            LocalDateTime now = LocalDateTime.now();

            scheduler.add(now.minusHours(2), jin.next());
            scheduler.add(now.minusHours(1), jin.next());
            scheduler.add(now.minusHours(4), jin.next());
            scheduler.add(now.plusHours(2), jin.next());
            scheduler.add(now.plusHours(4), jin.next());
            scheduler.add(now.plusHours(1), jin.next());
            scheduler.add(now.plusHours(5), jin.next());

            System.out.println(scheduler.next());
            System.out.println(scheduler.last());

            ArrayList<String> res = scheduler.getAll(
                    now.minusHours(3),
                    now.plusHours(5)
            );

            Collections.sort(res);
            for (String t : res) {
                System.out.print(t + " , ");
            }
        }

        if (k == 4) { // test Scheduler with ints complex
            Scheduler<Integer> scheduler = new Scheduler<>();
            int counter = 0;

            ArrayList<LocalDateTime> to_remove = new ArrayList<>();

            while (jin.hasNext()) {
                if (!jin.hasNextLong()) break;
                long epochMillis = jin.nextLong();
                int value = jin.nextInt();

                LocalDateTime time = LocalDateTime.ofEpochSecond(
                        epochMillis / 1000, 0, java.time.ZoneOffset.UTC
                );

                if ((counter & 7) == 0) {
                    to_remove.add(time);
                }
                scheduler.add(time, value);
                counter++;
            }

            jin.next();

            while (jin.hasNextLong()) {
                long lo = jin.nextLong();
                long hi = jin.nextLong();

                LocalDateTime l = LocalDateTime.ofEpochSecond(lo / 1000, 0, java.time.ZoneOffset.UTC);
                LocalDateTime h = LocalDateTime.ofEpochSecond(hi / 1000, 0, java.time.ZoneOffset.UTC);

                ArrayList<Integer> res = scheduler.getAll(l, h);
                Collections.sort(res);
                System.out.println(format(l) + " <: " + print(res) + " >: " + format(h));
            }

            System.out.println("test");

            ArrayList<Integer> res = scheduler.getAll(
                    LocalDateTime.ofEpochSecond(0, 0, java.time.ZoneOffset.UTC),
                    LocalDateTime.ofEpochSecond(Long.MAX_VALUE / 1000, 0, java.time.ZoneOffset.UTC)
            );

            Collections.sort(res);
            System.out.println(print(res));

            for (LocalDateTime d : to_remove) {
                scheduler.remove(d);
            }

            res = scheduler.getAll(
                    LocalDateTime.ofEpochSecond(0, 0, java.time.ZoneOffset.UTC),
                    LocalDateTime.ofEpochSecond(Long.MAX_VALUE / 1000, 0, java.time.ZoneOffset.UTC)
            );

            Collections.sort(res);
            System.out.println(print(res));
        }
    }

    private static <T> String print(ArrayList<T> res) {
        if (res == null || res.isEmpty()) return "NONE";
        StringBuilder sb = new StringBuilder();
        for (T t : res) {
            sb.append(t).append(" , ");
        }
        return sb.substring(0, sb.length() - 3);
    }
}