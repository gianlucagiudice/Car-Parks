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
    private int id;
    private ParkingSpot[] parkingSpots;
    private List<Thread> valets;
    private int freeValets;
    private ParkingManager parkingManager;
    private boolean isOpen;

    /**
     * Public constructor.
     * @param id The parking id.
     * @param parkingSpotsNumber
     * @param valetsNumber
     */
    public Parking(int id, int parkingSpotsNumber, int valetsNumber) {
        this.id = id;
        // Factory all parking spots
        this.parkingSpots = factoryParkingSpots(parkingSpotsNumber);
        // Create a new parking manager
        this.parkingManager = new ParkingManager();
        // Factory all valets
        this.freeValets = valetsNumber;
        this.valets = factoryValets(valetsNumber);
        // Parking is open
        this.isOpen = true;
    }
    
    /**
     * Deliveries the car.
     * @param car
     * @return The ticket id.
     * @throws FullParkingException
     * @throws InterruptedException
     */
    public synchronized int delivery(Car car) throws FullParkingException, InterruptedException {
        // Wait an available valet
        waitForValets();
        // Generate a ticket
        int ticketId = parkingManager.delivery(car, this.parkingSpots);
        // Notify all valet for a new task
        notifyAll();
        return ticketId;
    }

    /**
     * Picks up the car.
     * @param ticketId
     * @return The parked car.
     * @throws InterruptedException
     */
    public synchronized Car pickup(Integer ticketId) throws InterruptedException {
        // Can't pickup if not delivered yet
        while (!parkingManager.containsTicket(ticketId)) wait();
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

    /**
     * 
     * @return The task to accomplish.
     * @throws InterruptedException
     */
    public synchronized TaskStrategy accomplishTask() throws InterruptedException {
        TaskStrategy taskToAccomplish;
        while ((taskToAccomplish = this.parkingManager.accomplishTask()) == null)
            wait();
        return taskToAccomplish;
    }

    /**
     * 
     * @throws InterruptedException
     */
    private synchronized void waitForValets() throws InterruptedException {
        while (freeValets <= 0) {
            wait();
        }
        this.freeValets--;
    }

    /**
     * Releases the valets.
     */
    public synchronized void releaseValet() {
        // A valet is now free
        this.freeValets++;
        // Notify all waiting drivers for a free valet
        notifyAll();
    }

    /**
     * 
     * @param parkingSpotsNumber
     * @return
     */
    private ParkingSpot[] factoryParkingSpots(int parkingSpotsNumber) {
        parkingSpots = new ParkingSpot[parkingSpotsNumber];
        for (int i = 0; i < parkingSpots.length; i++) {
            parkingSpots[i] = new ParkingSpot();
        }
        return parkingSpots;
    }

    /**
     * 
     * @param valetsNumber
     * @return
     */
    private synchronized List<Thread> factoryValets(int valetsNumber) {
        valets = new ArrayList<>();
        for (int i = 0; i < valetsNumber; i++) {
            Thread valet = new Thread(new Valet(this));
            this.valets.add(valet);
            valet.start();
        }
        return valets;
    }

    /**
     * Getter for the parking id.
     * @return The parking id.
     */
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "parkingSpots=" + Arrays.toString(parkingSpots) +
                '}';
    }

    /**
     * Getter for the attribute isOpen.
     * @return The value of the attribute isOpen.
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * Closes the parking.
     */
    public void closeParking(){
        this.isOpen = false;
    }
}
