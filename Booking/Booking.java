package Booking;

import java.util.ArrayList;
import java.util.List;

import Concert.Seat;

public class Booking {
    private String concert_name;
    private int stand_maxTickets;
    private List<Seat> seatList;

    public Booking(String concert_name, int stand_maxTickets, List<Seat> seatList) {
        this.concert_name = concert_name;
        this.stand_maxTickets = stand_maxTickets;
        this.seatList = seatList;
    }

    public String getConcertName() {
        return concert_name;
    }

    public int getStandMaxTickets() {
        return stand_maxTickets;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (seatList == null || seatList.isEmpty()) {
        } else {
            for (int i = 0; i < seatList.size(); i++) {
                Seat s = seatList.get(i);
                sb.append(s.getRow()).append("-").append(s.getColumn());
                if (i < seatList.size() - 1) sb.append(";");
            }
        }

        return concert_name + "," + stand_maxTickets + ",[" + sb.toString() + "]";
    }

    public static Booking fromString(String bookingString) {
        String[] acc_book = bookingString.split(",");
        String name = acc_book[0];
        int stand_max = Integer.parseInt(acc_book[1]);
        String seatList = acc_book[2];

        seatList = seatList.replace("[", "").replace("]", "");
        List<Seat> seats = new ArrayList<>();
        

        if (!seatList.isEmpty()) {
            String[] seatList_rc = seatList.split(";");
            for (String pair : seatList_rc) {
                String[] parts = pair.split("-");
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                seats.add(new Seat(row, col));
            }
        }
        return new Booking(name, stand_max, seats);
    } 

    public String getstrseat() {
        StringBuilder sb = new StringBuilder();

        if (seatList == null || seatList.isEmpty()) {
        } else {
            for (int i = 0; i < seatList.size(); i++) {
                Seat s = seatList.get(i);
                sb.append(s.getRow()).append("-").append(s.getColumn());
                if (i < seatList.size() - 1) sb.append(";");
            }
        }
        return "" + sb.toString();
    } 

}