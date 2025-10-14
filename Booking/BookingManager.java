package Booking;

import java.io.*;
import java.util.*;

public class BookingManager {
    private List<Booking> bookings = new ArrayList<>();
    private String filename = "./File/Booking.CSV";

    

    public BookingManager() {
        loadBooking();
    }

    public void loadBooking() {
        File f = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            f = new File(filename);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                bookings.add(Booking.fromString(line));
            }
        } catch (Exception e) {
            System.out.println("Error loading booking: " + e);
        } finally {
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            } catch (Exception e) {
                System.out.println("Error closing file: " + e);
            }
        }
    }

    public void saveBooking(Booking booking) {
        File f = null;
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            f = new File(filename);
            fw = new FileWriter(f, true); 
            bw = new BufferedWriter(fw);
            bw.write(booking.toString() + "\n");
            System.out.println("Successfully wrote booking to the file.");
        } catch (IOException e) {
            System.out.println("Error writing booking: " + e);
        } finally {
            try {
                if (bw != null) bw.close();
                if (fw != null) fw.close();
            } catch (Exception e) {
                System.out.println("Error closing file: " + e);
            }
        }
        bookings.add(booking);
    }
    public void updateBooking() {
        File f = null;
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            f = new File(filename);
            fw = new FileWriter(f); 
            bw = new BufferedWriter(fw);
            for(Booking b : bookings) bw.write(b.toString() + "\n");
            System.out.println("Successfully wrote booking to the file.");
        } catch (IOException e) {
            System.out.println("Error writing booking: " + e);
        } finally {
            try {
                if (bw != null) bw.close();
                if (fw != null) fw.close();
            } catch (Exception e) {
                System.out.println("Error closing file: " + e);
            }
        }
    }

    public void setBooking(Booking newBooking) {
        for (int i = 0; i < bookings.size(); i++) {
            Booking b = bookings.get(i);
            if (b.getConcertName().equals(newBooking.getConcertName())) {
                bookings.set(i, newBooking);
                updateBooking();
                break;
            }
        }
    }

    public void deleteNotIn(List<String> concertNames) {
        List<Booking> toRemove = new ArrayList<>();
        for (Booking b : bookings) {
            if (!concertNames.contains(b.getConcertName())) {
                toRemove.add(b);
            }
        }
        bookings.removeAll(toRemove);
        updateBooking();
    }

    public Booking getBooking(String concertName) {
        for (Booking b : bookings) {
            if (b.getConcertName().equals(concertName)) {
                return b;
            }
        }
        return null;
    }

    public List<Booking> getBookingList() {
        return bookings;
    }

    public boolean hasBooking(String concertName) {
        for (Booking b : bookings) {
            if (b.getConcertName().equals(concertName)) {
                return true;
            }
        }
        return false;
    }

    public int size(){
        return bookings.size();
    }

    public void printList() {
        for (Booking b : bookings) {
            System.out.println(b.toString());
        }
    }
}