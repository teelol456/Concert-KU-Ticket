package Zone;

public class StandingZone extends Zone{
    private int maxTickets;

    public StandingZone(String name, double price,int maxTickets) {
        super(name, price);
        this.maxTickets = maxTickets;
    }

    public int getMaxTickets(){
        return maxTickets;
    }

}
