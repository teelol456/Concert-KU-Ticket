package Zone;

import java.util.*;

public class SeatingZone extends Zone{
    private List<Seat> seatList = new ArrayList<>();;

    public SeatingZone(String name, double price,List<Seat> seatList) {
        super(name, price);
        this.seatList = seatList;
    }
    
    public List<Seat> getSeatList(){
        return seatList;
    }
}
