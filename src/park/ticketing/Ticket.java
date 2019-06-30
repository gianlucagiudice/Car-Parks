package park.ticketing;

import auto.*;
import park.Park;
import park.ParkingSlot;

import java.util.Objects;

public class Ticket {
	private Park park;
	private ParkingSlot parkingSlot;
	private Car car;
	
	public Ticket(Park park, ParkingSlot parkingSlot, Car car) {
		this.park = park;
		this.parkingSlot = parkingSlot;
		this.car = car;
	}

	public ParkingSlot getParkingSlot() {
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
