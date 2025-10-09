package Zone;

public abstract class Zone {
    private String name;
    private double price;

    public Zone(String name, double price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }
}
