package transactionmanager;

public class CollegeChecking extends Checking {
    private Campus campus; //campus code
    public static final double ANNUAL_INTEREST_RATE = 0.01;
    public static final double MONTHLY_FEE = 0;

    /**
     * Creates a new college checking account
     * @param holder Profile information of the account holder
     * @param balance Balance of the college checking account
     * @param campus Campus code associated with account
     */
    public CollegeChecking(Profile holder, double balance, Campus campus) {
        super(holder, balance);
        this.campus = campus;
    }
    /**
     * Method to display college checking accounts with proper formatting
     * @return String that displays account info
     */
    @Override
    public String toString() {
        return String.format("College Checking::%s %s %s::Balance $%.2f::%s",
                holder.getFname(), holder.getLname(), holder.getDob(),
                getBalance(), campus);
    }
    //College Checking::Roy Brooks 10/31/1999::Balance $2,909.10::NEWARK
}
