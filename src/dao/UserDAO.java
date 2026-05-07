package dao;

import java.sql.*;
import database.DatabaseConnection;
import model.User;

public class UserDAO {

    public boolean register(User user) {

        String sql = "INSERT INTO users(name,email,password,phone,role) VALUES(?,?,?,?,?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getRole());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public User login(String email, String password) {

        String sql = "SELECT * FROM users WHERE email=? AND password=?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("role")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
