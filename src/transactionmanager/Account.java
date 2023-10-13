package transactionmanager;

public abstract class Account implements Comparable<Account> {
    protected Profile holder;
    protected double balance;

    public abstract double monthlyInterest();

    public abstract double monthlyFee();

    /**
     * Creates a new account object
     * @param holder Profile information of the account holder
     * @param balance account's balance
     * @param monthlyInterest account's monthly interest
     * @param monthlyFee account's monthly fee
     */
    public Account(Profile holder, double balance, double monthlyInterest,
                   double monthlyFee) {
        this.holder = holder;
        this.balance = balance;
        this.monthlyInterest();
        this.monthlyFee();
    }

    public Profile getHolder(){
        return holder;
    }

    /**
     * Getter method to output balance in account
     * @return account's balance
     */
    public double getBalance(){
        return balance;
    }
}