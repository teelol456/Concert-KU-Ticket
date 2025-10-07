package Concert;

public class Concert {
    private String concertName,date,location,image; 
    private double standPrice,seatPrice; 
    private int maxSeatTickets;

    public Concert(String concertName, String date, String location, String image, 
                   double standPrice, double seatPrice, int maxSeatTickets) {
        this.concertName = concertName;
        this.date = date;
        this.location = location;
        this.image = image; 
        this.standPrice = standPrice;
        this.seatPrice = seatPrice;
        this.maxSeatTickets = maxSeatTickets;
    }

    public String getConcertName() {
        return concertName;
    }

    public void setConcertName(String concertName) {
        this.concertName = concertName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getStandPrice() {
        return standPrice;
    }

    public void setStandPrice(double standPrice) {
        this.standPrice = standPrice;
    }

    public double getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(double seatPrice) {
        this.seatPrice = seatPrice;
    }

    public int getMaxSeatTickets() {
        return maxSeatTickets;
    }

    public void setMaxSeatTickets(int maxSeatTickets) {
        this.maxSeatTickets = maxSeatTickets;
    }

    public String toString() {
        return "Concert{" +
                "Name='" + concertName + '\'' +
                ", Date='" + date + '\'' +
                ", Location='" + location + '\'' +
                ", Image='" + image + '\'' +
                ", StandPrice=" + standPrice +
                ", SeatPrice=" + seatPrice +
                ", MaxSeatTickets=" + maxSeatTickets +
                '}';
    }

    public static Concert fromString(String concert){
        String[] acc_con = concert.split(",");
        String name = acc_con[0];
        String date = acc_con[1];
        String location = acc_con[2];
        String image = acc_con[3];
        double standPrice = Double.parseDouble(acc_con[4]);
        double seatPrice = Double.parseDouble(acc_con[5]);
        int maxSeatTickets = Integer.parseInt(acc_con[6]);
        return new Concert(name, date, location, image, standPrice, seatPrice, maxSeatTickets);
    }
}