package LaboratoryExercises.Lab01.BankArraySolution;

import java.util.Objects;
import java.util.Random;

public class Account {
    private String name;
    private long id;
    private double balance;

    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
        Random random = new Random();
        id = random.nextLong();
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nBalance: %f$");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && Double.compare(balance, account.balance) == 0 && Objects.equals(name, account.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, balance);
    }
}
