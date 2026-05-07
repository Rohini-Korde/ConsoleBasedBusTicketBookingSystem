package model;

import java.time.LocalDate;

public class Booking {

    private int bookingId;
    private int userId;
    private int scheduleId;
    private int seatNumber;
    private String status;
    private LocalDate bookingDate;

    public Booking() {
    }

    public Booking(int bookingId, int userId, int scheduleId,
                   int seatNumber, String status, LocalDate bookingDate) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.seatNumber = seatNumber;
        this.status = status;
        this.bookingDate = bookingDate;
    }

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

    
}
