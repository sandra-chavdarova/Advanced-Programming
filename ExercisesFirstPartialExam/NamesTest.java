package ExercisesFirstPartialExam;

import java.util.*;
import java.util.stream.Collectors;

class Names {
    private List<String> names;

    public Names() {
        this.names = new ArrayList<>();
    }

    public void addName(String name) {
        names.add(name);
    }

    public void printN(int n) {
        Map<String, Integer> mapRepeats = new TreeMap<>();

        for (String name : names) {
            mapRepeats.merge(name, 1, Integer::sum);
//            WITHOUT MERGE:
//            int repeats = names.stream().filter(name1 -> name1.equalsIgnoreCase(name)).collect(Collectors.toList()).size();
//            if (repeats >= n) {
//                mapRepeats.put(name, repeats);
//            }
        }
        Map<String, Long> mapUniqueLetters = new TreeMap<>();
        for (String name : names) {
            long uniqueCount = name.toLowerCase().chars().map(c -> (char) c).distinct().count();
            mapUniqueLetters.put(name, uniqueCount);
        }

        names.sort(null);
        for (String name : mapRepeats.keySet()) {
            if (mapRepeats.get(name) >= n) {
                String line = String.format("%s (%d) %d", name, mapRepeats.get(name), mapUniqueLetters.get(name));
                System.out.println(line);
            }
        }
    }

    public String findName(int len, int x) {
        List<String> filtered = names.stream().filter(n -> n.length() < len).distinct().collect(Collectors.toList());
        while (x >= filtered.size()) {
            x %= filtered.size();
        }
        return filtered.get(x);
    }
}

public class NamesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        Names names = new Names();
        for (int i = 0; i < n; ++i) {
            String name = scanner.nextLine();
            names.addName(name);
        }
        n = scanner.nextInt();
        System.out.printf("===== PRINT NAMES APPEARING AT LEAST %d TIMES =====\n", n);
        names.printN(n);
        System.out.println("===== FIND NAME =====");
        int len = scanner.nextInt();
        int index = scanner.nextInt();
        System.out.println(names.findName(len, index));
        scanner.close();
    }
}