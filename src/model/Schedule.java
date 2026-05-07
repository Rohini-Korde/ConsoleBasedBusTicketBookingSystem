package model;

import java.time.LocalDate;
import java.sql.Time;

public class Schedule {

    private int scheduleId;
    private int busId;
    private int routeId;
    private Time departureTime;     
    private Time arrivalTime;       
    private LocalDate travelDate;   
    private double price;

    public Schedule() {
    }

    public Schedule(int busId, int routeId, Time departureTime,
                    Time arrivalTime, LocalDate travelDate, double price) {
        this.busId = busId;
        this.routeId = routeId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.travelDate = travelDate;
        this.price = price;
    }

    public Schedule(int scheduleId, int busId, int routeId, Time departureTime,
                    Time arrivalTime, LocalDate travelDate, double price) {
        this.scheduleId = scheduleId;
        this.busId = busId;
        this.routeId = routeId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.travelDate = travelDate;
        this.price = price;
    }

    // Getters & Setters

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
