package transactionmanager;

public class MoneyMarket extends Savings{
    private int withdrawal; //number of withdrawals
    public static final double ANNUAL_INTEREST_RATE = 0.045;
    public static final double MONTHLY_FEE = 25;

    /**
     * Creates a new money market savings account
     * @param holder Profile information of the account holder
     * @param balance Balance of the college checking account
     * @param withdrawal number of withdrawals
     */
    public MoneyMarket(Profile holder, double balance, int withdrawal) {
        super(holder, balance);
        this.withdrawal = withdrawal;
    }
}
