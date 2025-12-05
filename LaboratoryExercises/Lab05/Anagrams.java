package LaboratoryExercises.Lab05;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {
    public static void findAll(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        Map<List<String>, TreeSet<String>> anagrams = new LinkedHashMap<>();
        List<String> words = new ArrayList<>();

        while (scanner.hasNextLine()) {
            words.add(scanner.nextLine().trim());
        }
        for (String word : words) {
            List<String> letters = Arrays.asList(word.split(""));
            List<String> sorted = letters.stream().sorted().collect(Collectors.toList());

            anagrams.putIfAbsent(sorted, new TreeSet<>());
            anagrams.get(sorted).add(word);
        }

        StringBuilder sb = new StringBuilder();
        for (TreeSet<String> combinations : anagrams.values()) {
            for (String word : combinations) {
                sb.append(word).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static void main(String[] args) {
        findAll(System.in);
    }
}
