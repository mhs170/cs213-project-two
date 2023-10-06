package transactionmanager;

import javax.print.DocFlavor;

public class Savings extends Account{
    protected boolean isLoyal; //loyal customer status
    public static final double ANNUAL_INTEREST_RATE = 0.04;
    public static final double MONTHLY_FEE = 25;
    public static final double INTEREST_IF_LOYAL = 0.0425;
    public static final double BALANCE_TO_WAIVE_FEE = 500;
    public static final int MONTHS_IN_YEAR = 12;

    public Savings(Profile holder, double balance) {
        super(holder, balance, ANNUAL_INTEREST_RATE, MONTHLY_FEE);
    }
    @Override
    public double monthlyInterest() {
        if (isLoyal){
            return INTEREST_IF_LOYAL / MONTHS_IN_YEAR;
        }
        return ANNUAL_INTEREST_RATE / MONTHS_IN_YEAR;
    }

    @Override
    public double monthlyFee() {
        if (balance < BALANCE_TO_WAIVE_FEE){
            return MONTHLY_FEE;
        }
        return 0;
    }

    @Override
    public int compareTo(Account o) {
        return 0;
    }
}
