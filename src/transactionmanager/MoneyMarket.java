package transactionmanager;

public class MoneyMarket extends Savings{
    private int withdrawal; //number of withdrawals
    public static final double ANNUAL_INTEREST_RATE = 0.045;
    public static final double MONTHLY_FEE = 25;

    //THIS CONSTRUCTOR MIGHT NEED FIXING, IT'S AUTO_GENERATED
    public MoneyMarket(Profile holder, double balance) {
        super(holder, balance);
    }
}
