package auto;

import park.Park;
import park.ParkFullException;

public class Driver extends Thread{
	private Car car;

	public void run(Park park) throws InterruptedException {
		while (park.getFreeValets() <= 0)
			wait();
		try {
			park.park(car);
		} catch (ParkFullException e) {
			e.printStackTrace();
		}
	}
	
	public Driver(Car car) {
		this.car = car;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
}

