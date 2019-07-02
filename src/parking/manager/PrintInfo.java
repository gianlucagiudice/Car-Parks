package parking.manager;

import auto.*;
import parking.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintInfo {
	
	public static void newThread(Thread thread) {
		System.out.println("New thread created: " + thread.getName() + ".");
	}
	
	public static void startThread(Thread thread) {
		System.out.println(thread.getName() + " started running.");
	}
	
	public static void threadState(Thread thread) {
		System.out.println(thread.getName() + "'state: " + thread.getState() + ".");
	}
	
	public static void terminatedThread(Thread thread) {
		System.out.println(thread.getName() + " terminated running.");
	}
	
	public static void newParking(Parking parking) {
		System.out.println("New parking created: " + parking.toString() + ".");
	}
	
	public static void newCar(Car car) {
		System.out.println("New car created: " + car.toString() + ".");
	}
	
	public static void newDriver(Driver driver) {
		System.out.println("New driver created: " + driver.getCar().toString() + ".");
	}
	
	public static void driverState(Thread thread, Driver driver) {
		System.out.println(thread.getName() + ": " + driver.toString() + ".");
	}
	
	public static void parkingState(Parking parking) {
		System.out.println(parking.toString() + ".");
	}
	
	public static void currentTime() {
		System.out.println("[Current time = " + new SimpleDateFormat("HH:mm:ss:SSS").format(new Date()) + "]");
	}
	
	public static void deliveryRequest(Thread driver, Car car, Parking parking) {
		System.out.println(driver.getName() + " requests to deliver his " + car.toString() +
				" to the parking P" + parking.getId() + ".");
	}
	
	public static void pickupRequest(Thread driver, Parking parking, int ticketId) {
		System.out.println(driver.getName() + " requests to pickup his car from the parking P" +
				parking.getId() + " with the ticket {id=" + ticketId + "}.");
	}
	
	public static void noFreeValets() {
		System.out.println("No free valets: waiting...");
	}
	
	public static void freeValets(int freeValets) {
		System.out.println(freeValets + " free valets: serving drivers...");
	}
	
	public static void ticketCreated(int ticketId) {
		System.out.println("New ticket created: {id=" + ticketId + "}.");
	}
	
	public static void ticketAcquired(Thread driver, int ticketId) {
		System.out.println(driver.getName() + " acquired the ticket {id=" + ticketId + "}.");
	}
	
	public static void carPickedUp(Thread driver, Car car) {
		System.out.println(driver.getName() + " picked up his " + car.toString() + ".");
	}
	
	public static void startDelivery(Thread valet, Car car, ParkingSpot parkingSpot) {
		System.out.println(valet.getName() + " starts parking the " + car.toString() +
				" in the parking spot: " + parkingSpot.getId() + ".");
	}
	
	public static void deliveryCompleted(Thread valet, Car car, ParkingSpot parkingSpot) {
		System.out.println(valet.getName() + " successfully parked the " + car.toString() +
				" in the parking spot: " + parkingSpot.getId() + ".");
	}
	
	public static void startPickup(Thread valet, ParkingSpot parkingSpot) {
		System.out.println(valet.getName() + " starts picking up the car from the parking spot: " +
				parkingSpot.getId() + ".");
	}
	
	public static void pickupCompleted(Thread valet, Car car, ParkingSpot parkingSpot) {
		System.out.println(valet.getName() + " successfully picked up the " + car.toString() +
				" from the parking spot: " + parkingSpot.getId() + ".");
	}
	
	public static void startGivingBack(Thread valet, Car car) {
		System.out.println(valet.getName() + " starts giving back the " + car.toString() +
				" to its driver.");
	}
	
	public static void givingBackCompleted(Thread valet, Car car) {
		System.out.println(valet.getName() + " successfully gave back the " + car.toString() +
				" to its driver.");
	}
	
	public static void valetReleased(Thread valet) {
		System.out.println(valet.getName() + " is now free.");
	}
	
	public static void waitingForCar(Thread driver) {
		System.out.println(driver.getName() + " is waiting for his car to be picked up and given back to him...");
	}
}
