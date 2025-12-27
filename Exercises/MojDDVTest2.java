package Exercises;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AmountNotAllowedException extends Exception {
    private int sum;

    public AmountNotAllowedException(int sum) {
        this.sum = sum;
    }

    @Override
    public String getMessage() {
        return String.format("Receipt with amount %d is not allowed to be scanned", sum);
    }
}

class Item {
    private int price;
    private String tax;

    public Item(int price, String tax) {
        this.price = price;
        this.tax = tax;
    }

    public int getPrice() {
        return price;
    }

    public String getTax() {
        return tax.toUpperCase();
    }

    public double getTaxAmount() {
        switch (getTax()) {
            case "A":
                return price * 0.18;
            case "B":
                return price * 0.05;
            default:
                return 0.0;
        }
    }
}

class Receipt {
    private int id;
    private List<Item> items;

    public Receipt(int id, List<Item> items) throws AmountNotAllowedException {
        this.id = id;
        this.items = items;
        if (getTotalAmount() > 30000)
            throw new AmountNotAllowedException(getTotalAmount());
    }

    public int getId() {
        return id;
    }

    public int getTotalAmount() {
        return items.stream().mapToInt(Item::getPrice).sum();
    }

    public double taxReturn() {
        return items.stream().mapToDouble(Item::getTaxAmount).sum() * 0.15;
    }

    @Override
    public String toString() {
        return String.format("%10s\t%10d\t%10.5f", id, getTotalAmount(), taxReturn());
    }
}

class MojDDV {
    private List<Receipt> receipts;

    public MojDDV() {
        this.receipts = new ArrayList<>();
    }

    public void readRecords(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split("\\s+");
            int id = Integer.parseInt(parts[0]);
            List<Item> items = new ArrayList<>();
            for (int i = 1; i < parts.length; i += 2) {
                int price = Integer.parseInt(parts[i]);
                items.add(new Item(price, parts[i + 1]));
            }
            try {
                receipts.add(new Receipt(id, items));
            } catch (AmountNotAllowedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void printTaxReturns(OutputStream outputStream) {
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream));
        for (Receipt receipt : receipts) {
            printWriter.println(receipt);
        }
        printWriter.flush();
    }

    public void printStatistics(OutputStream outputStream) {
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream));
        printWriter.println(String.format("min:\t%.3f", receipts.stream().mapToDouble(Receipt::taxReturn).min().orElse(0)));
        printWriter.println(String.format("max:\t%.3f", receipts.stream().mapToDouble(Receipt::taxReturn).max().orElse(0)));
        printWriter.println(String.format("sum:\t%.3f", receipts.stream().mapToDouble(Receipt::taxReturn).sum()));
        printWriter.println(String.format("count:\t%d", receipts.size()));
        printWriter.println(String.format("avg:\t%.3f", receipts.stream().mapToDouble(Receipt::taxReturn).average().orElse(0)));
        printWriter.flush();
    }
}

public class MojDDVTest2 {
    public static void main(String[] args) {

        MojDDV mojDDV = new MojDDV();

        System.out.println("===READING RECORDS FROM INPUT STREAM===");
        mojDDV.readRecords(System.in);

        System.out.println("===PRINTING TAX RETURNS RECORDS TO OUTPUT STREAM ===");
        mojDDV.printTaxReturns(System.out);

        System.out.println("===PRINTING SUMMARY STATISTICS FOR TAX RETURNS TO OUTPUT STREAM===");
        mojDDV.printStatistics(System.out);
    }
}
