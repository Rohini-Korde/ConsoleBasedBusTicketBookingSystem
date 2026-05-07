package util;

import java.io.FileWriter;
import java.io.IOException;

import model.TicketDetails;

public class TicketGenerator {

	public static void generateTicket(int userId, int bookingId, TicketDetails details) {

	    String fileName = "ticket_" + userId + "_" + bookingId + ".txt";

	    try (FileWriter writer = new FileWriter(fileName)) {

	        writer.write("========== BUS TICKET ==========\n");
	        writer.write("Booking ID : " + bookingId + "\n");
	        writer.write("Passenger  : " + details.getName() + "\n");
	        writer.write("Bus        : " + details.getBusName() + "\n");
	        writer.write("Route      : " + details.getSource() + " -> "
	                + details.getDestination() + "\n");
	        writer.write("Travel Date: " + details.getTravelDate() + "\n");
	        writer.write("Seat No    : " + details.getSeatNumber() + "\n");
	        writer.write("Price      : ₹" + details.getPrice() + "\n");
	        writer.write("================================\n");

	        System.out.println("Ticket generated: " + fileName);

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
