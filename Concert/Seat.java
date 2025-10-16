package Concert;

public class Seat {
    private int seatRow;
    private int seatColumn;
    private boolean available;

    public Seat(int seatRow,int seatColumn){
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
    }

    public boolean getavailable(){
        return available;
    }

    public Object getRow() {
        return seatRow;
    }

    public Object getColumn() {
        return seatColumn;
    }


}
    