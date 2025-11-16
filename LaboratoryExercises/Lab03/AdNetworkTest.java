package LaboratoryExercises.Lab03;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Ad implements Comparable<Ad> {
    private String id;
    private String category;
    private double bidValue;
    private double ctr;
    private String content;
    private double score;

    public Ad(String id, String category, double bidValue, double ctr, String content) {
        this.id = id;
        this.category = category;
        this.bidValue = bidValue;
        this.ctr = ctr;
        this.content = content;
        this.score = 0;
    }

    @Override
    public String toString() {
        return String.format("%s %s (bid=%.2f, ctr=%.2f%%) %s", id, category, bidValue, ctr * 100, content);
    }

    @Override
    public int compareTo(Ad other) {
        int comparison = Double.compare(other.bidValue, this.bidValue);
        if (comparison == 0)
            return this.id.compareTo(other.id);
        return comparison;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public double getBidValue() {
        return bidValue;
    }

    public double getCtr() {
        return ctr;
    }

    public String getContent() {
        return content;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}

class AdRequest {
    private String id;
    private String category;
    private double floorBid;
    private String keywords;

    public AdRequest(String id, String category, double floorBid, String keywords) {
        this.id = id;
        this.category = category;
        this.floorBid = floorBid;
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return String.format("%s %s (floor=%.2f): %s", id, category, floorBid, keywords);
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getKeywords() {
        return keywords;
    }
}

class AdNetwork {
    private List<Ad> ads;

    public AdNetwork() {
        this.ads = new ArrayList<>();
    }

    public void readAds(BufferedReader bf) throws IOException {
        String line;
        while ((line = bf.readLine()) != null && !line.trim().isEmpty()) {
            try {
                String[] parts = line.split("\\s+");
                StringBuilder sb = new StringBuilder();
                for (int i = 4; i < parts.length; i++) {
                    sb.append(parts[i]).append(" ");
                }
                ads.add(new Ad(parts[0], parts[1], Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), sb.toString()));
            } catch (Exception e) {
                System.out.print("");
            }
        }
    }

    public List<Ad> placeAds(BufferedReader bf, int k, PrintWriter pw) throws IOException {
        String[] parts = bf.readLine().split("\\s+");
        String id = parts[0];
        String category = parts[1];
        double floorBid = Double.parseDouble(parts[2]);
        String keywords = parts[3];
        AdRequest request = new AdRequest(id, category, floorBid, keywords);

        List<Ad> result = new ArrayList<>();
        for (Ad ad : ads) {
            if (ad.getBidValue() >= floorBid) {
                ad.setScore(relevanceScore(ad, request) + 5.0 * ad.getBidValue() + 100.0 * ad.getCtr());
                result.add(ad);
            }
        }
        result = result.stream().sorted(Comparator.comparing(Ad::getScore).reversed()).limit(k).sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        System.out.println("Top ads for request " + id + ":");
        for (Ad ad : result) {
            pw.println(ad);
        }
        pw.flush();
        return result;
    }

    private int relevanceScore(Ad ad, AdRequest req) {
        int score = 0;
        if (ad.getCategory().equalsIgnoreCase(req.getCategory())) score += 10;
        String[] adWords = ad.getContent().toLowerCase().split("\\s+");
        String[] keywords = req.getKeywords().toLowerCase().split("\\s+");
        for (String kw : keywords) {
            for (String aw : adWords) {
                if (kw.equals(aw)) score++;
            }
        }
        return score;
    }
}

public class AdNetworkTest {
    public static void main(String[] args) throws IOException {
        AdNetwork network = new AdNetwork();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int k = Integer.parseInt(br.readLine().trim());

        if (k == 0) {
            network.readAds(br);
            network.placeAds(br, 1, pw);
        } else if (k == 1) {
            network.readAds(br);
            network.placeAds(br, 3, pw);
        } else {
            network.readAds(br);
            network.placeAds(br, 8, pw);
        }
        pw.flush();
    }
}