package parking;

import auto.Car;
import parking.exceptions.FullParkingException;
import parking.manager.ParkingManager;
import parking.valet.TaskStrategy;
import parking.valet.Valet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Gianluca Giudice
 * @author Andrea Tassi
 *
 */
public class Parking {
    private ParkingSpot[] parkingSpots;
    private List<Thread> valets;
    private int freeValets;
    private ParkingManager parkingManager;

    public Parking(int parkingSpotsNumber, int valetsNumber) {
        // Factory all parking spots
        this.parkingSpots = factoryParkingSpots(parkingSpotsNumber);
        // Create a new parking manager
        this.parkingManager = new ParkingManager();
        // Factory all valets
        this.freeValets = valetsNumber;
        this.valets = factoryValets(valetsNumber);
    }
    
    public synchronized int delivery(Car car) throws FullParkingException, InterruptedException {
        // Wait an available valet
        waitForValets();
        // Generate a ticket
        int ticketId = parkingManager.delivery(car, this.parkingSpots);
        // Notify all valet for a new task
        notifyAll();
        return ticketId;
    }

    public synchronized Car pickup(Integer ticketId) throws InterruptedException {
        // Wait an available valet
        waitForValets();
        // Prepare pickup operation
        parkingManager.prepareParking(ticketId);
        // Start pickup process
        notifyAll();
        Car carParked;
        while ((carParked = parkingManager.pickup(ticketId)) == null)
            wait();
        parkingManager.pickupCompleted(ticketId);
        return carParked;
    }

    public synchronized TaskStrategy accomplishTask() throws InterruptedException {
        TaskStrategy taskToAccomplish;
        while ((taskToAccomplish = this.parkingManager.accomplishTask()) == null)
            wait();
        return taskToAccomplish;
    }

    private synchronized void waitForValets() throws InterruptedException {
        while (freeValets <= 0) {
            wait();
        }
        this.freeValets--;
    }

    public synchronized void releaseValet() {
        // A valet is now free
        this.freeValets++;
        // Notify all waiting drivers for a free valet
        notifyAll();
    }

    private ParkingSpot[] factoryParkingSpots(int parkingSpotsNumber) {
        parkingSpots = new ParkingSpot[parkingSpotsNumber];
        for (int i = 0; i < parkingSpots.length; i++) {
            parkingSpots[i] = new ParkingSpot();
        }
        return parkingSpots;
    }

    private synchronized List<Thread> factoryValets(int valetsNumber) {
        valets = new ArrayList<>();
        for (int i = 0; i < valetsNumber; i++) {
            Thread valet = new Thread(new Valet(this));
            this.valets.add(valet);
            valet.start();
        }
        return valets;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "parkingSpots=" + Arrays.toString(parkingSpots) +
                '}';
    }
}
