package Zone;

public class Seat {
    private String seatId;
    private boolean available;

    public Seat(String seatId,boolean available){
        this.seatId = seatId;
        this.available = available;
    }

    public String getseatId(){
        return seatId;
    }

    public boolean getavailable(){
        return available;
    }
}
