package services;

import dao.BookingDAO;
import dao.ScheduleDAO;
import model.Schedule;

import java.util.List;

public class UserService {

    private BookingDAO bookingDAO;
    private ScheduleDAO scheduleDAO;

    public UserService() {
        bookingDAO = new BookingDAO();
        scheduleDAO = new ScheduleDAO();
    }

    
    public int bookTicket(int userId, int scheduleId, int seatNumber) {

        if (scheduleId <= 0 || seatNumber <= 0) {
            System.out.println("Invalid Booking Details!");
            return -1;
        }

        return bookingDAO.bookSeat(userId, scheduleId, seatNumber);
    }


    
    public boolean cancelBooking(int bookingId) {

        if (bookingId <= 0) {
            System.out.println("Invalid Booking ID!");
            return false;
        }

        return bookingDAO.cancelBooking(bookingId);
    }

    
    public List<Schedule> searchSchedule(String source, String destination, String date) {

        if (source.isEmpty() || destination.isEmpty() || date.isEmpty()) {
            System.out.println("All fields are required!");
            return null;
        }

        return scheduleDAO.searchSchedule(source, destination, date);
    }
    
    public List<String> viewMyBookings(int userId) {
        return bookingDAO.viewMyBookings(userId);
    }
    
    public List<Integer> getBookedSeats(int scheduleId) {
        return bookingDAO.getBookedSeats(scheduleId);
    }

}

