package LaboratoryExercises.Lab01.Bank;

import java.util.List;
import java.util.Objects;

public class Bank {
    private List<Account> accounts;
    private String name;
    private double transfers;
    private double provisions;

    public Bank(String name, List<Account> accounts) {
        this.accounts = accounts;
        this.name = name;
        this.transfers = 0;
        this.provisions = 0;
    }

    boolean makeTransaction(Transaction t) {
        Account accountFrom = accounts.stream().filter(a -> a.getId() == t.getFromId()).findFirst().orElse(null);
        Account accountTo = accounts.stream().filter(a -> a.getId() == t.getToId()).findFirst().orElse(null);
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
        if (accountFrom != null && accountTo != null && accountFrom.getBalance() >= amount) {
            accountFrom.setBalance(accountFrom.getBalance() - amount);
            accountTo.setBalance(accountTo.getBalance() + transfer);
            transfers += transfer;
            provisions += provision;
            return true;
        }
        return false;
    }

    double totalTransfers() {
        return transfers;
    }

    double totalProvision() {
        return provisions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Name: %s", name));
        for (Account account : accounts) {
            sb.append(String.format("%s", account));
        }
        return sb.toString();
    }

    public Account getAccounts(int from_idx) {
        return accounts.get(from_idx);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Double.compare(transfers, bank.transfers) == 0 && Double.compare(provisions, bank.provisions) == 0 && Objects.equals(accounts, bank.accounts) && Objects.equals(name, bank.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accounts, name, transfers, provisions);
    }
}
