package parking.manager;

import auto.*;
import parking.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintInfo {

	private static PrintInfo instance = null;
	
	private PrintInfo() {
	}
	
	public static PrintInfo getInstance() {
        if (instance == null) {
        	instance = new PrintInfo();
        }
        return instance;
    }
	
	public void newThread(Thread thread) {
		System.out.println("New thread created: " + thread.getName() + ".");
	}
	
	public void startThread(Thread thread) {
		System.out.println(thread.getName() + " started running.");
	}
	
	public void terminatedThread(Thread thread) {
		System.out.println(thread.getName() + " terminated running.");
	}
	
	public void newParking(Parking parking) {
		System.out.println("New parking created: " + parking.toString() + ".");
	}
	
	public void newCar(Car car) {
		System.out.println("New car created: " + car.toString() + ".");
	}
	
	public void newDriver(Driver driver) {
		System.out.println("New driver created: " + driver.getCar().toString() + ".");
	}
	
	public void currentTime() {
		System.out.println("[Current time = " + new SimpleDateFormat("HH:mm:ss:SSS").format(new Date()) + "]");
	}
	
	public void deliveryRequest(Thread driver, Car car, Parking parking) {
		System.out.println(driver.getName() + " requests to deliver his " + car.toString() +
				" to the parking P" + parking.getId() + ".");
	}
	
	public void pickupRequest(Thread driver, Parking parking, int ticketId) {
		System.out.println(driver.getName() + " requests to pickup his car from the parking P" +
				parking.getId() + " with the ticket {id=" + ticketId + "}.");
	}
	
	public void noFreeValets() {
		System.out.println("No free valets: waiting...");
	}
	
	public void freeValets(int freeValets) {
		System.out.println(freeValets + " free valets: serving drivers...");
	}
	
	public void ticketCreated(int ticketId) {
		System.out.println("New ticket created: {id=" + ticketId + "}.");
	}
	
	public void ticketAcquired(Thread driver, int ticketId) {
		System.out.println(driver.getName() + " acquired the ticket {id=" + ticketId + "}.");
	}
	
	public void carPickedUp(Thread driver, Car car) {
		System.out.println(driver.getName() + " picked up his " + car.toString() + ".");
	}
	
	public void driverCar(Thread driver, Car car) {
		System.out.println(driver.getName() + "'s car: " + car + ".");
	}
	
	public void startDelivery(Thread valet, Car car, ParkingSpot parkingSpot) {
		System.out.println(valet.getName() + " starts parking the " + car.toString() +
				" in the parking spot: " + parkingSpot.getId() + ".");
	}
	
	public void deliveryCompleted(Thread valet, Car car, ParkingSpot parkingSpot) {
		System.out.println(valet.getName() + " successfully parked the " + car.toString() +
				" in the parking spot: " + parkingSpot.getId() + ".");
	}
	
	public void startPickup(Thread valet, ParkingSpot parkingSpot) {
		System.out.println(valet.getName() + " starts picking up the car from the parking spot: " +
				parkingSpot.getId() + ".");
	}
	
	public void pickupCompleted(Thread valet, Car car, ParkingSpot parkingSpot) {
		System.out.println(valet.getName() + " successfully picked up the " + car.toString() +
				" from the parking spot: " + parkingSpot.getId() + ".");
	}
	
	public void startGivingBack(Thread valet, Car car) {
		System.out.println(valet.getName() + " starts giving back the " + car.toString() +
				" to its driver.");
	}
	
	public void givingBackCompleted(Thread valet, Car car) {
		System.out.println(valet.getName() + " successfully gave back the " + car.toString() +
				" to its driver.");
	}
	
	public void valetReleased(Thread valet) {
		System.out.println(valet.getName() + " is now free.");
	}
	
	public void waitingForCar(Thread driver) {
		System.out.println(driver.getName() + " is waiting for his car to be picked up and given back to him...");
	}
}
