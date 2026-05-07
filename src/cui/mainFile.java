package cui;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import dao.BookingDAO;
import database.DatabaseConnection;
import model.Schedule;
import model.TicketDetails;
import model.User;
import services.AdminService;
import services.AuthenticateService;
import services.UserService;
import util.TicketGenerator;

public class mainFile {

//	    public static void main(String[] args) {
//
//	        Connection con = DatabaseConnection.getConnection();
//
//	        if (con != null) {
//	            System.out.println("Connected!");
//	        } else {
//	            System.out.println("Not Connected!");
//	        }
//	    }

	    static Scanner sc = new Scanner(System.in);

	    static AuthenticateService authService = new AuthenticateService();
	    static AdminService adminService = new AdminService();
	    static UserService userService = new UserService();
	    
	    private static BookingDAO bookingDAO = new BookingDAO();

	    public static void main(String[] args) {

	        while (true) {

	            System.out.println("\n===== BUS BOOKING SYSTEM =====");
	            System.out.println("1. Register");
	            System.out.println("2. Login");
	            System.out.println("3. Exit");
	            System.out.print("Choose option: ");

	            int choice = sc.nextInt();
	            sc.nextLine();

	            switch (choice) {
	                case 1 -> register();
	                case 2 -> login();
	                case 3 -> {
	                    System.out.println("Exiting...");
	                    System.exit(0);
	                }
	                default -> System.out.println("Invalid choice!");
	            }
	        }
	    }

	    
	    private static void register() {

	        System.out.println("\n--- Register ---");

	        System.out.print("Name: ");
	        String name = sc.nextLine();

	        System.out.print("Email: ");
	        String email = sc.nextLine();

	        System.out.print("Password: ");
	        String password = sc.nextLine();

	        System.out.print("Phone: ");
	        String phone = sc.nextLine();

	        boolean success = authService.register(name, email, password, phone);

	        if (success)
	            System.out.println("Registration Successful!");
	        else
	            System.out.println("Registration Failed!");
	    }

	    
	    private static void login() {

	        System.out.println("\n--- Login ---");

	        System.out.print("Email: ");
	        String email = sc.nextLine();

	        System.out.print("Password: ");
	        String password = sc.nextLine();

	        User user = authService.login(email, password);

	        if (user != null) {

	            System.out.println("Login Successful!");

	            if (user.getRole().equalsIgnoreCase("admin")) {
	                adminMenu();
	            } else {
	                userMenu(user);
	            }

	        } else {
	            System.out.println("Invalid Credentials!");
	        }
	    }

	    
	    private static void adminMenu() {

	        while (true) {

	            System.out.println("\n--- ADMIN MENU ---");
	            System.out.println("1. Add Bus");
	            System.out.println("2. Add Route");
	            System.out.println("3. Add Schedule");
	            System.out.println("4. Logout");
	            System.out.print("Choose option: ");

	            int choice = sc.nextInt();
	            sc.nextLine();

	            switch (choice) {
	                case 1 -> addBus();
	                case 2 -> addRoute();
	                case 3 -> addSchedule();
	                case 4 -> {
	                    return;
	                }
	                default -> System.out.println("Invalid choice!");
	            }
	        }
	    }

	    private static void addBus() {

	        System.out.print("Bus Name: ");
	        String name = sc.nextLine();

	        System.out.print("Bus Type: ");
	        String type = sc.nextLine();

	        System.out.print("Total Seats: ");
	        int seats = sc.nextInt();
	        sc.nextLine();

	        boolean success = adminService.addBus(name, type, seats);

	        if (success)
	            System.out.println("Bus Added Successfully!");
	        else
	            System.out.println("Failed to Add Bus!");
	    }

	    private static void addRoute() {

	        System.out.print("Source: ");
	        String source = sc.nextLine();

	        System.out.print("Destination: ");
	        String destination = sc.nextLine();

	        System.out.print("Distance: ");
	        int distance = sc.nextInt();
	        sc.nextLine();

	        boolean success = adminService.addRoute(source, destination, distance);

	        if (success)
	            System.out.println("Route Added Successfully!");
	        else
	            System.out.println("Failed to Add Route!");
	    }

	    private static void addSchedule() {

	        System.out.print("Bus ID: ");
	        int busId = sc.nextInt();

	        System.out.print("Route ID: ");
	        int routeId = sc.nextInt();
	        sc.nextLine();

	        System.out.print("Departure Time (HH:MM:SS): ");
	        String departure = sc.nextLine();

	        System.out.print("Arrival Time (HH:MM:SS): ");
	        String arrival = sc.nextLine();

	        System.out.print("Travel Date (yyyy-MM-dd): ");
	        String date = sc.nextLine();

	        System.out.print("Price: ");
	        double price = sc.nextDouble();
	        sc.nextLine();

	        boolean success = adminService.addSchedule(busId, routeId, departure, arrival, date, price);

	        if (success)
	            System.out.println("Schedule Added Successfully!");
	        else
	            System.out.println("Failed to Add Schedule!");
	    }

	    
	    private static void userMenu(User user) {

	        while (true) {

	            System.out.println("\n--- USER MENU ---");
	            System.out.println("1. Search Bus");
	            System.out.println("2. Book Ticket");
	            System.out.println("3. View My Bookings");
	            System.out.println("4. Cancel Booking");
	            System.out.println("5. Logout");
	            System.out.print("Choose option: ");

	            int choice = sc.nextInt();
	            sc.nextLine();

	            switch (choice) {
	                case 1 -> searchBus();
	                case 2 -> bookTicket(user);
	                case 3 -> viewMyBookings(user);
	                case 4 -> cancelBooking();
	                case 5 -> {
	                    return;
	                }
	                default -> System.out.println("Invalid choice!");
	            }
	        }
	    }

	    private static void searchBus() {

	        System.out.print("Source: ");
	        String source = sc.nextLine();

	        System.out.print("Destination: ");
	        String destination = sc.nextLine();

	        System.out.print("Travel Date (yyyy-MM-dd): ");
	        String date = sc.nextLine();

	        List<Schedule> schedules = userService.searchSchedule(source, destination, date);

	        if (schedules == null || schedules.isEmpty()) {
	            System.out.println("No schedules found!");
	            return;
	        }

	        for (Schedule s : schedules) {
	            System.out.println("Schedule ID: " + s.getScheduleId()
	                    + " | Bus ID: " + s.getBusId()
	                    + " | Departure: " + s.getDepartureTime()
	                    + " | Price: " + s.getPrice());
	        }
	    }

	    private static void bookTicket(User user) {

	        System.out.print("Enter Schedule ID: ");
	        int scheduleId = sc.nextInt();

	        System.out.print("Enter Seat Number: ");
	        int seatNumber = sc.nextInt();
	        sc.nextLine();

	        int bookingId = userService.bookTicket(
	                user.getUserId(),
	                scheduleId,
	                seatNumber
	        );

	        if (bookingId != -1) {

	            System.out.println("Ticket Booked Successfully!");
	            System.out.println("Booking ID: " + bookingId);

	            TicketDetails details =
	                    bookingDAO.getTicketDetails(bookingId);

	            if (details != null) {
	                TicketGenerator.generateTicket(
	                        user.getUserId(),
	                        bookingId,
	                        details
	                );
	            }

	        } else {
	            System.out.println("Booking Failed!");
	        }
	    }



	    private static void cancelBooking() {

	        System.out.print("Enter Booking ID: ");
	        int bookingId = sc.nextInt();
	        sc.nextLine();

	        boolean success = userService.cancelBooking(bookingId);

	        if (success)
	            System.out.println("Booking Cancelled!");
	        else
	            System.out.println("Cancel Failed!");
	    }
	    
	    private static void viewMyBookings(User user) {

	        List<String> bookings = userService.viewMyBookings(user.getUserId());

	        if (bookings == null || bookings.isEmpty()) {
	            System.out.println("No bookings found!");
	            return;
	        }

	        for (String b : bookings) {
	            System.out.println(b);
	        }
	    }

	}
