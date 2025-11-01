package AuditoryExercises.Exercise1.Bank;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        Account acc1 = new NonInterestCheckingAccount("Alice", 1000.0);
        Account acc2 = new InterestCheckingAccount("Bob", 2000.0);
        Account acc3 = new PlatinumCheckingAccount("Charlie", 5000.0);

        bank.addAccount(acc1);
        bank.addAccount(acc2);
        bank.addAccount(acc3);

        System.out.println("Total assets before interest: " + bank.totalAssets());
        bank.addInterest();
        System.out.println("Total assets after interest: " + bank.totalAssets());
    }
}