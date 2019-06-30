package auto;

import parking.Parking;
import parking.ParkFullException;

public class Driver implements Runnable{
	private Parking parking;
	private Car car;
	private int ticket;

	public Driver(Parking parking, Car car) {
		this.car = car;
		this.parking = parking;
	}

	@Override
	public void run() {
		waitForValets();
		// Parking the car
		try {
			ticket = parking.delivery(car);
		} catch (ParkFullException e) {
			// Parking is full;
			e.printStackTrace();
		}
		car = parking.pickup(ticket);
	}

	private void waitForValets(){
		while (parking.getFreeValets() <= 0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

}
