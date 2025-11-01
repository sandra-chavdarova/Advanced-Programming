package AuditoryExercises.Exercise1.Bank;

public class NonInterestCheckingAccount extends Account {

    public NonInterestCheckingAccount(String accountHolder, double accountBalance) {
        super(accountHolder, accountBalance);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.NON_INTEREST;
    }
}
