package dao;

import java.sql.*;
import database.DatabaseConnection;
import model.Route;

import java.util.ArrayList;
import java.util.List;

public class RouteDAO {

    
    public boolean addRoute(Route route) {

        String sql = "INSERT INTO routes(source, destination, distance) VALUES(?,?,?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, route.getSource());
            ps.setString(2, route.getDestination());
            ps.setInt(3, route.getDistance());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    
    public List<Route> getAllRoutes() {

        List<Route> routeList = new ArrayList<>();
        String sql = "SELECT * FROM routes";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Route route = new Route(
                        rs.getInt("route_id"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getInt("distance")
                );

                routeList.add(route);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return routeList;
    }

    
    public Route getRouteById(int routeId) {

        String sql = "SELECT * FROM routes WHERE route_id=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, routeId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Route(
                        rs.getInt("route_id"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getInt("distance")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
