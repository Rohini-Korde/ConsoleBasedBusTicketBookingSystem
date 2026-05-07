package model;

public class TicketDetails {

    private String name;
    private String busName;
    private String source;
    private String destination;
    private String travelDate;
    private int seatNumber;
    private double price;

    public TicketDetails(String name, String busName, String source,
                         String destination, String travelDate,
                         int seatNumber, double price) {
        this.name = name;
        this.busName = busName;
        this.source = source;
        this.destination = destination;
        this.travelDate = travelDate;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public String getName() { return name; }
    public String getBusName() { return busName; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public String getTravelDate() { return travelDate; }
    public int getSeatNumber() { return seatNumber; }
    public double getPrice() { return price; }
}

