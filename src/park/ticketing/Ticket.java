package park.ticketing;

import auto.*;
import park.Park;
import park.ParkSlot;

import java.util.Objects;

public class Ticket {

	private Park park;
	private ParkSlot parkingSlot;
	private Car car;


	public Ticket(Park park, ParkSlot parkingSlot, Car car) {
		this.park = park;
		this.parkingSlot = parkingSlot;
		this.car = car;
	}

	public ParkSlot getParkingSlot() {
		return parkingSlot;
	}

	public Car getCar() {
		return car;
	}

	@Override
	public int hashCode() {
		return Objects.hash(park, parkingSlot, car);
	}

}
