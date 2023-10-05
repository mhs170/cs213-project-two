package transactionmanager;

public enum Campus {
    NEWBRUNSWICK(0),
    NEWARK(1),
    CAMDEN(2);
    private final int campusCode;
    Campus(int campusCode){
        this.campusCode = campusCode;
    }
    public int getCampusCode(){
        return campusCode;
    }

}
