package auto;

import park.Park;
import park.ParkFullException;

public class Driver implements Runnable{
	private Park park;
	private Car car;
	private int ticket;

	public Driver(Park park, Car car) {
		this.car = car;
		this.park = park;
	}

	@Override
	public void run() {
		waitForValets();
		// Park the car
		try {
			ticket = park.delivery(car);
		} catch (ParkFullException e) {
			// Park is full;
			e.printStackTrace();
		}
		car = park.pickup(ticket);
	}

	private void waitForValets(){
		while (park.getFreeValets() <= 0){
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
