package model;

public class Bus {

    private int busId;
    private String busName;
    private int totalSeats;
    private String busType;

    public Bus() {
    }

    public Bus(String busName, String busType,int totalSeats) {
        this.busName = busName;
        this.totalSeats = totalSeats;
        this.busType = busType;
    }

    public Bus(int busId, String busName, int totalSeats, String busType) {
		super();
		this.busId = busId;
		this.busName = busName;
		this.totalSeats = totalSeats;
		this.busType = busType;
	}

	public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }
}

