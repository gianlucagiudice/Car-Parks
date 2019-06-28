package park;

import auto.*;

public class Ticket {

	private Parking parking;
	private ParkingSlot parkingSlot;
	private int timeSlot;
	private int duration;
	private Driver driver;
	private Car car;
	
	public Ticket(Parking parking, ParkingSlot parkingSlot, int timeSlot, int duration, Driver driver, Car car) {
		this.parking = parking;
		this.parkingSlot = parkingSlot;
		this.timeSlot = timeSlot;
		this.duration = duration;
		this.driver = driver;
		this.car = car;
	}

	public Parking getParking() {
		return parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}

	public ParkingSlot getParkingSlot() {
		return parkingSlot;
	}

	public void setParkingSlot(ParkingSlot parkingSlot) {
		this.parkingSlot = parkingSlot;
	}

	public int getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(int timeSlot) {
		this.timeSlot = timeSlot;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		result = prime * result + ((parking == null) ? 0 : parking.hashCode());
		result = prime * result + timeSlot;
		return result;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ticket [parking=");
		builder.append(parking);
		builder.append(", parkingSlot=");
		builder.append(parkingSlot);
		builder.append(", timeSlot=");
		builder.append(timeSlot);
		builder.append(", duration=");
		builder.append(duration);
		builder.append(", driver=");
		builder.append(driver);
		builder.append(", car=");
		builder.append(car);
		builder.append("]");
		return builder.toString();
	}
}
