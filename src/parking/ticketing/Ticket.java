package parking.ticketing;

import auto.Car;
import parking.ParkingSpot;

import java.util.Objects;

public class Ticket {

	private int parkID;
	private ParkingSpot carParkedSlot;
	private Car carParked;


	public Ticket(int parkID, ParkingSpot carParkedSlot, Car carParked) {
		this.parkID = parkID;
		this.carParkedSlot = carParkedSlot;
		this.carParked = carParked;
	}

	@Override
	public int hashCode() {
		return Objects.hash(parkID, carParkedSlot, carParked);
	}
}
