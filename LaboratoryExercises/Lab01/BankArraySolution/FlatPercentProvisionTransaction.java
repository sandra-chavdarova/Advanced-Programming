package LaboratoryExercises.Lab01.BankArraySolution;

import java.util.Objects;

public class FlatPercentProvisionTransaction extends Transaction {
    private double centsPerDollar;

    public FlatPercentProvisionTransaction(long fromId, long toId, double amount, double centsPerDollar) {
        super(fromId, toId, "FlatPercent", amount);
        this.centsPerDollar = centsPerDollar;
    }

    public double getPercent() {
        return getAmount() * (1 + centsPerDollar / 100);
    }

    public double getCentsPerDollar() {
        return centsPerDollar / 100;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FlatPercentProvisionTransaction that = (FlatPercentProvisionTransaction) o;
        return Double.compare(centsPerDollar, that.centsPerDollar) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), centsPerDollar);
    }
}
