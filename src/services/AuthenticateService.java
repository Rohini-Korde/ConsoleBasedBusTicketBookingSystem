package services;

import dao.UserDAO;
import model.User;

public class AuthenticateService {

    private UserDAO userDAO;

    public AuthenticateService() {
        userDAO = new UserDAO();
    }

    
    public boolean register(String name, String email, String password, String phone) {

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            System.out.println("All fields are required!");
            return false;
        }

        User user = new User(name, email, password, phone, "user");

        return userDAO.register(user);
    }

    
    public User login(String email, String password) {

        if (email.isEmpty() || password.isEmpty()) {
            System.out.println("Email and Password required!");
            return null;
        }

        return userDAO.login(email, password);
    }
}
