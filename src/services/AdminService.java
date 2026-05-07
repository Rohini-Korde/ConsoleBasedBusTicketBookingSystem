package services;

import dao.*;
import model.*;

import java.sql.Time;
import java.time.LocalDate;

public class AdminService {

    private BusDAO busDAO;
    private RouteDAO routeDAO;
    private ScheduleDAO scheduleDAO;

    public AdminService() {
        busDAO = new BusDAO();
        routeDAO = new RouteDAO();
        scheduleDAO = new ScheduleDAO();
    }

    
    public boolean addBus(String name, String type, int seats) {

        if (name.isEmpty() || type.isEmpty() || seats <= 0) {
            System.out.println("Invalid Bus Details!");
            return false;
        }

        Bus bus = new Bus(name, type, seats);
        return busDAO.addBus(bus);
    }

    
    public boolean addRoute(String source, String destination, int distance) {

        if (source.isEmpty() || destination.isEmpty() || distance <= 0) {
            System.out.println("Invalid Route Details!");
            return false;
        }

        Route route = new Route(source, destination, distance);
        return routeDAO.addRoute(route);
    }

    
    public boolean addSchedule(int busId, int routeId,
                               String departure, String arrival,
                               String travelDate, double price) {

        try {
            Time departureTime = Time.valueOf(departure);
            Time arrivalTime = Time.valueOf(arrival);
            LocalDate date = LocalDate.parse(travelDate);

            Schedule schedule = new Schedule(
                    busId,
                    routeId,
                    departureTime,
                    arrivalTime,
                    date,
                    price
            );

            return scheduleDAO.addSchedule(schedule);

        } catch (Exception e) {
            System.out.println("Invalid Date/Time Format!");
        }

        return false;
    }
}

