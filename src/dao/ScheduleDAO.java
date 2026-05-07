package dao;

import java.sql.*;
import database.DatabaseConnection;
import model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO {
	
	
    public boolean addSchedule(Schedule schedule) {

        String sql = "INSERT INTO schedules(bus_id, route_id, departure_time, arrival_time, travel_date, price) VALUES(?,?,?,?,?,?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, schedule.getBusId());
            ps.setInt(2, schedule.getRouteId());
            ps.setTime(3, schedule.getDepartureTime());
            ps.setTime(4, schedule.getArrivalTime());
            ps.setDate(5, Date.valueOf(schedule.getTravelDate())); 
            ps.setDouble(6, schedule.getPrice());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Schedule> searchSchedule(String source, String destination, String travelDate) {

        List<Schedule> list = new ArrayList<>();

        String sql = """
            SELECT s.* FROM schedules s
            JOIN routes r ON s.route_id = r.route_id
            WHERE r.source=? AND r.destination=? AND s.travel_date=?
        """;

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, source);
            ps.setString(2, destination);
            ps.setDate(3, Date.valueOf(travelDate));

            ResultSet rs = ps.executeQuery(); 

            while (rs.next()) {

                Schedule schedule = new Schedule(
                        rs.getInt("schedule_id"),
                        rs.getInt("bus_id"),
                        rs.getInt("route_id"),
                        rs.getTime("departure_time"),
                        rs.getTime("arrival_time"),
                        rs.getDate("travel_date").toLocalDate(),
                        rs.getDouble("price")
                );

                list.add(schedule);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public int getBusIdByScheduleId(int scheduleId) {

        String sql = "SELECT bus_id FROM schedules WHERE schedule_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, scheduleId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("bus_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

}
