package parking.ticketing;

import auto.Car;
import parking.ParkingSpot;

import java.util.Objects;

public class Ticket {

	private int parkID;
	private ParkingSpot carParkedSpot;
	private Car carParked;


	public Ticket(int parkID, ParkingSpot carParkedSprot, Car carParked) {
		this.parkID = parkID;
		this.carParkedSpot = carParkedSpot;
		this.carParked = carParked;
	}

	@Override
	public int hashCode() {
		return Objects.hash(parkID, carParkedSpot, carParked);
	}
}
