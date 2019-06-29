package park;

import auto.*;

import java.util.Objects;

public class Ticket {

	private int id;
	private Park park;
	private ParkSlot parkingSlot;
	private int startSlot;
	private int duration;
	private Driver driver;
	private Car car;


	public Ticket(Park park, ParkSlot parkingSlot, int startSlot, int duration, Driver driver, Car car) {
		this.park = park;
		this.parkingSlot = parkingSlot;
		this.startSlot = startSlot;
		this.duration = duration;
		this.driver = driver;
		this.car = car;
		this.id = this.hashCode();
	}

	public ParkSlot getParkingSlot() {
		return parkingSlot;
	}

	public int getStartSlot() {
		return startSlot;
	}

	public int getDuration() {
		return duration;
	}

	public Driver getDriver() {
		return driver;
	}

	public int endSlot(){
		return startSlot + duration;
	}

	@Override
	public int hashCode() {
		return Objects.hash(park, parkingSlot, startSlot, duration, driver, car);
	}

	@Override
	public String toString() {
		return "Ticket{" +
				"id=" + id +
				", parkingSlot=" + parkingSlot +
				", startSlot=" + startSlot +
				", duration=" + duration +
				", driver=" + driver +
				", car=" + car +
				'}';
	}
}
