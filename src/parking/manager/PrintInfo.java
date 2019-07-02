package parking.manager;

import auto.*;
import parking.Parking;

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
	
	public void currentTime() {
		System.out.println("Current time: " + new SimpleDateFormat("HH:mm:ss:SSS").format(new Date()));
	}
	
	public void newParking(Parking parking) {
		System.out.println("New parking created: " + parking.toString());
	}
	
	public void newCar(Car car) {
		System.out.println("New car created: " + car.toString());
	}
	
	public void newDriver(Driver driver) {
		System.out.println("New driver created: " + driver.getCar().toString());
	}
	
	public void newThread(Thread thread) {
		System.out.println("New thread created: " + thread.getName());
	}
	
	public void startThread(Thread thread) {
		System.out.println(thread.getName() + " started running.");
	}
	
	public void deliveryRequest(Thread driver, Car car) {
		System.out.println(driver.getName() + " requests to deliver his " + car.toString());
	}
	
	public void pickupRequest(Thread driver, int ticketId) {
		System.out.print(driver.getName() + " requests to pickup his car with the ticket id: " + ticketId);
	}
	
	public void noFreeValets() {
		System.out.print("No free valets: waiting...");
	}
	
	public void freeValets(int freeValets) {
		System.out.print(freeValets + " free valets: serving drivers...");
	}
	
	public void ticketCreated(int ticketId) {
		System.out.println("Ticket acquired, id: " + ticketId);
	}
	
	public void ticketAcquired(Thread driver, int ticketId) {
		System.out.println(driver.getName() + " acquired the ticket, id: " + ticketId);
	}
	
	public void carPickedUp(Thread driver, Car car) {
		System.out.println(driver.getName() + " picked up his " + car.toString());
	}
	
	public void driverCar(Thread driver, Car car) {
		System.out.println(driver.getName() + "'s " + car.toString());
	}
	
	public void startDelivery() {
		System.out.println("Valet starts parking the car...");
	}
	
	public void startPickup() {
		System.out.println("Valet starts picking up the car...");
	}
	
	public void startAccomplishTask(Thread valet) {
		System.out.println(valet.getName() + " starts to accomplish his task...");
	}
	
	public void taskAccomplished(Thread valet) {
		System.out.println(valet.getName() + " accomplished his task.");
	}
	
	public void valetReleased(Thread valet) {
		System.out.println(valet.getName() + " is now free.");
	}
}
