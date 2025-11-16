package ExercisesFirstPartialExam;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Product implements Comparable<Product> {
    private int original;
    private int discounted;

    public Product(int original, int discounted) {
        this.original = original;
        this.discounted = discounted;
    }

    public int discount() {
        return (original - discounted) * 100 / original;
    }

    public int difference() {
        return original - discounted;
    }

    @Override
    public int compareTo(Product o) {
        int comparison = Integer.compare(o.discount(), this.discount());
        if (comparison == 0)
            return Integer.compare(o.difference(), this.difference());
        return comparison;
    }

    @Override
    public String toString() {
        return String.format("%2d%% %d/%d\n", discount(), discounted, original);
    }
}

class Store implements Comparable<Store> {
    private String name;
    private List<Product> products;

    public Store(String name, List<Product> prices) {
        this.name = name;
        this.products = prices;
    }

    public double averageDiscount() {
        double sum = 0;
        for (Product product : products) {
            sum += product.discount();
        }
        return sum / products.size();
    }

    public int absoluteDiscount() {
        int sum = 0;
        for (Product product : products) {
            sum += product.difference();
        }
        return sum;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        products.sort(null);
        sb.append(String.format("%s\nAverage discount: %.1f%%\nTotal discount: %d\n", name, averageDiscount(), absoluteDiscount()));
        for (Product product : products) {
            sb.append(product);
        }
        return sb.toString().trim();
    }

    public static Store createStore(String line) {
        String[] parts = line.split("\\s+");
        String name = parts[0];
        List<Product> products = new ArrayList<>();
        for (int i = 1; i < parts.length; i++) {
            String[] pricesPair = parts[i].split(":");
            products.add(new Product(Integer.parseInt(pricesPair[1]), Integer.parseInt(pricesPair[0])));
        }
        return new Store(name, products);
    }

    @Override
    public int compareTo(Store o) {
        int comparison = Double.compare(o.averageDiscount(), this.averageDiscount());
        if (comparison == 0)
            return this.name.compareTo(o.name);
        return comparison;
    }
}

class Discounts {
    private List<Store> stores;

    public Discounts() {
        this.stores = new ArrayList<>();
    }

    public int readStores(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        stores = reader.lines().filter(Objects::nonNull).map(Store::createStore).collect(Collectors.toList());
        return stores.size();
    }

    public List<Store> byAverageDiscount() {
        return stores.stream().sorted(Comparator.comparingDouble(Store::averageDiscount).reversed()
                .thenComparing(Store::getName)).limit(3).collect(Collectors.toList());
    }

    public List<Store> byTotalDiscount() {
        return stores.stream().sorted(Comparator.comparingInt(Store::absoluteDiscount)
                .thenComparing(Store::getName)).limit(3).collect(Collectors.toList());
    }
}

public class DiscountsTest {
    public static void main(String[] args) {
        Discounts discounts = new Discounts();
        int stores = discounts.readStores(System.in);
        System.out.println("Stores read: " + stores);
        System.out.println("=== By average discount ===");
        discounts.byAverageDiscount().forEach(System.out::println);
        System.out.println("=== By total discount ===");
        discounts.byTotalDiscount().forEach(System.out::println);
    }
}
