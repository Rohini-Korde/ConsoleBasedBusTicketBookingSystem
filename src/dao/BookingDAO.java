package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.TicketDetails;

public class BookingDAO {

    
	public int bookSeat(int userId, int scheduleId, int seatNumber) {

	    String sql = "INSERT INTO bookings(user_id, schedule_id, seat_number, status) VALUES(?,?,?,?)";

	    try (Connection con = DatabaseConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        ps.setInt(1, userId);
	        ps.setInt(2, scheduleId);
	        ps.setInt(3, seatNumber);
	        ps.setString(4, "Booked");

	        int rows = ps.executeUpdate();

	        if (rows > 0) {

	            ResultSet rs = ps.getGeneratedKeys();

	            if (rs.next()) {
	                return rs.getInt(1);   // 🔥 return booking_id
	            }
	        }

	    } catch (SQLIntegrityConstraintViolationException e) {
	        System.out.println("Seat already booked!");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return -1;   // ❌ booking failed
	}


    
    public boolean cancelBooking(int bookingId) {

        String sql = "UPDATE bookings SET status='Cancelled' WHERE booking_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookingId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
 
    public List<String> viewMyBookings(int userId) {

        List<String> bookingList = new ArrayList<>();

        String sql = """
            SELECT b.booking_id, b.seat_number, b.status,
                   s.schedule_id, s.departure_time, s.travel_date
            FROM bookings b
            JOIN schedules s ON b.schedule_id = s.schedule_id
            WHERE b.user_id=?
        """;

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String bookingInfo =
                        "Booking ID: " + rs.getInt("booking_id")
                        + " | Schedule ID: " + rs.getInt("schedule_id")
                        + " | Seat: " + rs.getInt("seat_number")
                        + " | Date: " + rs.getDate("travel_date")
                        + " | Status: " + rs.getString("status");

                bookingList.add(bookingInfo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookingList;
    }
    public List<Integer> getBookedSeats(int scheduleId) {

        List<Integer> bookedSeats = new ArrayList<>();

        String sql = "SELECT seat_number FROM bookings WHERE schedule_id=? AND status='Booked'";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, scheduleId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bookedSeats.add(rs.getInt("seat_number"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookedSeats;
    }
    
    public TicketDetails getTicketDetails(int bookingId) {

        String sql = """
            SELECT u.name, b.bus_name, r.source, r.destination,
                   s.travel_date, bk.seat_number, s.price
            FROM bookings bk
            JOIN users u ON bk.user_id = u.user_id
            JOIN schedules s ON bk.schedule_id = s.schedule_id
            JOIN buses b ON s.bus_id = b.bus_id
            JOIN routes r ON s.route_id = r.route_id
            WHERE bk.booking_id = ?
        """;

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookingId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new TicketDetails(
                        rs.getString("name"),
                        rs.getString("bus_name"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getString("travel_date"),
                        rs.getInt("seat_number"),
                        rs.getDouble("price")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
