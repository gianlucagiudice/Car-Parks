package park.ticketing;

import auto.Car;
import park.ParkSlot;

import java.util.Objects;

public class Ticket {

	private int parkID;
	private ParkSlot carParkedSlot;
	private Car carParked;


	public Ticket(int parkID, ParkSlot carParkedSlot, Car carParked) {
		this.parkID = parkID;
		this.carParkedSlot = carParkedSlot;
		this.carParked = carParked;
	}

	@Override
	public int hashCode() {
		return Objects.hash(parkID, carParkedSlot, carParked);
	}
}
