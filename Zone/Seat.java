package Zone;

public class Seat {
    private int seatRow;
    private int seatColumn;
    private boolean available;

    public Seat(int seatRow,int seatColumn,boolean available){
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.available = available;
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
    