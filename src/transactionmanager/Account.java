package transactionmanager;

public abstract class Account implements Comparable<Account> {
    protected Profile holder;
    protected double balance;

    public abstract double monthlyInterest();

    public abstract double monthlyFee();

    public Account(Profile holder, double balance, double monthlyInterest,
                   double monthlyFee) {
        this.holder = holder;
        this.balance = balance;
        this.monthlyInterest();
        this.monthlyFee();
    }

}