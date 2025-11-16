package ExercisesFirstPartialExam;
//TODO
// 16ta zadaca
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class AmountNotAllowedException extends Exception {
    private int amount;

    public AmountNotAllowedException(int amount) {
        this.amount = amount;
    }

    @Override
    public String getMessage() {
        return String.format("Receipt with amount %d is not allowed to be scanned", amount);
    }
}

class Receipt {
    private String id;
    private List<Integer> prices;
    private List<String> types;

    public Receipt(String id, List<Integer> prices, List<String> type) {
        this.id = id;
        this.prices = prices;
        this.types = type;
    }

    public static Receipt createReceipt(String line) throws AmountNotAllowedException {
        String[] parts = line.split("\\s+");
        List<Integer> prices = new ArrayList<>();
        List<String> types = new ArrayList<>();
        int total = 0;
        for (int i = 1; i < parts.length; i += 2) {
            types.add((parts[i + 1]));
            prices.add(Integer.parseInt(parts[i]));
            total += Integer.parseInt(parts[i]);
        }
        if (total > 30000)
            throw new AmountNotAllowedException(total);
        return new Receipt(parts[0], prices, types);
    }

    public int sumOfAmounts() {
        return prices.stream().mapToInt(Integer::intValue).sum();
    }

    public double taxReturn() {
        double sum = 0;
        for (int i = 0; i < prices.size(); i++) {
            if (types.get(i).equalsIgnoreCase("A"))
                sum += prices.get(i) * 0.18d;
            else if (types.get(i).equalsIgnoreCase("B")) {
                sum += prices.get(i) * 0.05d;
            }
        }
        return sum * 0.15d;
    }

    @Override
    public String toString() {
        return String.format("%s %d %.2f", id, sumOfAmounts(), taxReturn());
    }
}

class MojDDV {
    private List<Receipt> receipts;

    public MojDDV() {
        this.receipts = new ArrayList<>();
    }

    public void readRecords(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        receipts = reader.lines().filter(Objects::nonNull).map(line -> {
            try {
                return Receipt.createReceipt(line);
            } catch (AmountNotAllowedException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public void printTaxReturns(OutputStream outputStream) {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));
        for (Receipt receipt : receipts) {
            writer.println(receipt);
        }
        writer.flush();
    }
}

public class MojDDVTest {
    public static void main(String[] args) {
        MojDDV mojDDV = new MojDDV();

        System.out.println("===READING RECORDS FROM INPUT STREAM===");
        mojDDV.readRecords(System.in);

        System.out.println("===PRINTING TAX RETURNS RECORDS TO OUTPUT STREAM ===");
        mojDDV.printTaxReturns(System.out);
    }
}