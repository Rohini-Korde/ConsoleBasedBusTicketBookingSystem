package dao;

import java.sql.*;
import database.DatabaseConnection;
import model.Bus;

import java.util.ArrayList;
import java.util.List;

public class BusDAO {

    public boolean addBus(Bus bus) {

        String sql = "INSERT INTO buses(bus_name,bus_type,total_seats) VALUES(?,?,?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bus.getBusName());
            ps.setString(2, bus.getBusType());
            ps.setInt(3, bus.getTotalSeats());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    
    public List<Bus> getAllBuses() {

        List<Bus> busList = new ArrayList<>();
        String sql = "SELECT * FROM buses";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Bus bus = new Bus(
                        rs.getInt("bus_id"),
                        rs.getString("bus_name"),
                        rs.getInt("total_seats"),
                        rs.getString("bus_type")
                );

                busList.add(bus);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return busList;
    }
    
    public int getTotalSeatsByBusId(int busId) {

        String sql = "SELECT total_seats FROM buses WHERE bus_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, busId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("total_seats");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

}
