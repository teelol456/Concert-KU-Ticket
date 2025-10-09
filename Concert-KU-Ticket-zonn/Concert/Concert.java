package Concert;

public class Concert {
    private String concertName,date,location,image; 
    private double standPrice,seatPrice;

    public Concert(String concertName, String date, String location, 
                   double standPrice, double seatPrice, String image) {
        this.concertName = concertName;
        this.date = date;
        this.location = location;
        this.standPrice = standPrice;
        this.seatPrice = seatPrice;
        this.image = image; 
    }

    public String getConcertName() {
        return concertName;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getImage() {
        return image;
    }

    public double getStandPrice() {
        return standPrice;
    }

    public double getSeatPrice() {
        return seatPrice;
    }

    public String toString() {
        return "Concert{" +
                "Name='" + concertName + '\'' +
                ", Date='" + date + '\'' +
                ", Location='" + location + '\'' +
                ", StandPrice=" + standPrice +
                ", SeatPrice=" + seatPrice +
                ", Image='" + image + '\'' +
                '}';
    }

    public static Concert fromString(String concert){
        String[] acc_con = concert.split(",");
        String name = acc_con[0];
        String date = acc_con[1];
        String location = acc_con[2];
        double standPrice = Double.parseDouble(acc_con[3]);
        double seatPrice = Double.parseDouble(acc_con[4]);
        String image = acc_con[5];
        return new Concert(name, date, location, standPrice, seatPrice, image);
    }
}