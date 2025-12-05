package LaboratoryExercises.Lab04;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class TermFrequency {
    private List<String> stopWords;
    private Map<String, Integer> map;

    public TermFrequency(InputStream inputStream, String[] stopWords) {
        this.map = new TreeMap<>();
        this.stopWords = Arrays.stream(stopWords).filter(Objects::nonNull).map(String::toLowerCase).collect(Collectors.toList());
        Scanner sc = new Scanner(inputStream);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            line = line.replaceAll("\\.", "");
            line = line.replaceAll(",", "");
            String[] ws = line.split("\\s+");
            for (String w : ws) {
                if (this.stopWords.contains(w.toLowerCase())) {
                    continue;
                }
                map.merge(w.toLowerCase(), 1, Integer::sum);
            }
        }
        map.remove(" ");
        map.remove("");
    }

    public int countTotal() {
        return map.values().stream().mapToInt(Integer::intValue).sum();
    }

    public int countDistinct() {
        return map.size();
    }

    public List<String> mostOften(int k) {
        List<String> wds = new ArrayList<>(map.keySet());
        wds.sort(Comparator.comparing((String w) -> map.get(w), Comparator.reverseOrder()).thenComparing(Comparator.naturalOrder()));
        return wds.subList(0, k);
    }
}

public class TermFrequencyTest {
    public static void main(String[] args) throws FileNotFoundException {
        String[] stop = new String[]{"во", "и", "се", "за", "ќе", "да", "од",
                "ги", "е", "со", "не", "тоа", "кои", "до", "го", "или", "дека",
                "што", "на", "а", "но", "кој", "ја"};
        TermFrequency tf = new TermFrequency(System.in, stop);
        System.out.println(tf.countTotal());
        System.out.println(tf.countDistinct());
        System.out.println(tf.mostOften(10));
    }
}
