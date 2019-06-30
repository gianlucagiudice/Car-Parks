package parking;

import auto.Car;

import java.util.Objects;

public class Ticket {

	private int parkID;
	private ParkingSpot carParkedSpot;
	private Car carParked;


	Ticket(int parkID, ParkingSpot carParkedSpot, Car carParked) {
		this.parkID = parkID;
		this.carParkedSpot = carParkedSpot;
		this.carParked = carParked;
	}

	@Override
	public int hashCode() {
		return Objects.hash(parkID, carParkedSpot, carParked);
	}
}
