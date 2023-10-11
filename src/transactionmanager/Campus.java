package transactionmanager;

public enum Campus {
    NEWBRUNSWICK(0),
    NEWARK(1),
    CAMDEN(2);
    private final int campusCode;

    /**
     * Constructor method that initializes the campus object
     * @param campusCode campus code, new brunswick - 0, newark - 1,camden - 2
     */
    Campus(int campusCode){
        this.campusCode = campusCode;
    }

    /**
     * Getter method to output the campus code
     * @return campus code
     */
    public int getCampusCode(){
        return campusCode;
    }

}
