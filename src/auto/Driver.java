package auto;

import parking.Parking;
<<<<<<< HEAD
import parking.ParkFullException;
=======
import parking.ParkingFullException;
>>>>>>> branch 'master' of https://github.com/gianlucagiudice/Car-Parks.git

public class Driver implements Runnable{
	private Parking parking;
	private Car car;
	private int ticket;

	public Driver(Car car) {
		this.car = car;
	}

	@Override
	public void run() {
		waitForValets();
		// Parking the car
		try {
			ticket = parking.delivery(car);
<<<<<<< HEAD
		} catch (ParkFullException e) {
=======
		} catch (ParkingFullException e) {
>>>>>>> branch 'master' of https://github.com/gianlucagiudice/Car-Parks.git
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

	public void setParking(Parking parking) {
		this.parking = parking;
	}
}
