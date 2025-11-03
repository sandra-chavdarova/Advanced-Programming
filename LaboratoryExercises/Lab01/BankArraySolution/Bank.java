package LaboratoryExercises.Lab01.BankArraySolution;

import java.util.Arrays;
import java.util.Objects;

public class Bank {
    private Account[] accounts;
    private String name;
    private double transfers;
    private double provisions;

    public Bank(String name, Account[] accounts) {
        this.accounts = accounts;
        this.name = name;
        this.transfers = 0;
        this.provisions = 0;
    }

    public boolean makeTransaction(Transaction t) {
        int indexFrom = -1;
        int indexTo = -1;
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getId() == t.getFromId())
                indexFrom = i;
            if (accounts[i].getId() == t.getToId())
                indexTo = i;
        }
        double transfer = 0;
        double provision = 0;
        if (t instanceof FlatAmountProvisionTransaction) {
            FlatAmountProvisionTransaction f = (FlatAmountProvisionTransaction) t;
            transfer = f.getAmount();
            provision = f.getFlatProvision();
        } else if (t instanceof FlatPercentProvisionTransaction) {
            FlatPercentProvisionTransaction f = (FlatPercentProvisionTransaction) t;
            transfer = f.getAmount();
            provision = transfer * f.getCentsPerDollar();
        }
        double amount = transfer + provision;
        if (indexFrom == -1 || indexTo == -1 || accounts[indexFrom].getBalance() < amount) {
            return false;
        }
        accounts[indexFrom].setBalance(accounts[indexFrom].getBalance() - amount);
        accounts[indexTo].setBalance(accounts[indexTo].getBalance() + transfer);
        transfers += transfer;
        provisions += provision;
        return true;
    }

    public double totalTransfers() {
        return transfers;
    }

    public double totalProvision() {
        return provisions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Name: %s", name));
        for (int i = 0; i < accounts.length; i++) {
            sb.append(String.format("%s", accounts[i]));
        }
        return sb.toString();
    }

    public Account[] getAccounts() {
        return accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Double.compare(transfers, bank.transfers) == 0 && Double.compare(provisions, bank.provisions) == 0 && Objects.equals(name, bank.name) && Objects.deepEquals(accounts, bank.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, Arrays.hashCode(accounts), transfers, provisions);
    }
}
