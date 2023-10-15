package transactionmanager;

public class MoneyMarket extends Savings {
    private int withdrawal; //number of withdrawals
    public static final double ANNUAL_INTEREST_RATE = 0.045;
    public static final double MONTHLY_FEE = 25;

    /**
     * Creates a new money market savings account
     *
     * @param holder     Profile information of the account holder
     * @param balance    Balance of the college checking account
     * @param withdrawal number of withdrawals
     */
    public MoneyMarket(Profile holder, double balance, boolean isLoyal,
                       int withdrawal) {
        super(holder, balance, isLoyal);
        this.withdrawal = withdrawal;
    }

    /**
     * Method to compare if objects are equal
     * @param obj the account to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MoneyMarket)) {
            return false;
        }
        MoneyMarket compareThis = (MoneyMarket) obj;
        return this.getHolder().equals(compareThis.getHolder());
    }

    /**
     * Method to display Money Market accounts with proper formatting
     * @return String that displays account info
     */
    @Override
    public String toString() {
        if (isLoyal) {
            return String.format("Money Market::Savings::%s %s %s::Balance " +
                            "$%.2f::is loyal::withdrawal: %d",
                    holder.getFname(), holder.getLname(), holder.getDob(),
                    getBalance(), withdrawal);
        }
        return String.format("Money Market::Savings::%s %s %s::Balance $%" +
                        ".2f::withdrawal: %d",
                holder.getFname(), holder.getLname(), holder.getDob(),
                getBalance(), withdrawal);
    }
    //Money Market::Savings::Roy Brooks 10/31/1979::Balance $2,909.10::is
    // loyal::withdrawal: 0
}
