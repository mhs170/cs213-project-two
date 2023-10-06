package transactionmanager;

public class Checking extends Account {
    public static final double ANNUAL_INTEREST_RATE = 0.01;
    public static final double MONTHLY_FEE = 12;
    public static final double BALANCE_TO_WAIVE_FEE = 1000;
    public static final int MONTHS_IN_YEAR = 12;

    public Checking(Profile holder, double balance) {
        super(holder, balance, ANNUAL_INTEREST_RATE, MONTHLY_FEE);
    }

    @Override
    public double monthlyInterest() {
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
