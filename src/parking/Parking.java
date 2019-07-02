package parking;

import auto.Car;
import parking.exceptions.FullParkingException;
import parking.manager.ParkingManager;
import parking.manager.PrintInfo;
import parking.valet.TaskStrategy;
import parking.valet.Valet;

import java.util.ArrayList;
import java.util.List;

public class Parking {
	private int id;
    private ParkingSpot[] parkingSpots;
    private List<Thread> valets;
    private int freeValets;
    private ParkingManager parkingManager;

    public Parking(int id, int parkingSpotsNumber, int valetsNumber) {
        // Initialize parking id
    	this.id = id;
    	// Factory all parking spots
        this.parkingSpots = factoryParkingSpots(parkingSpotsNumber);
        // Create a new parking manager
        this.parkingManager = new ParkingManager();
        // Factory all valets
        this.freeValets = valetsNumber;
        this.valets = factoryValets(valetsNumber);
    }

    public int delivery(Car car) throws FullParkingException, InterruptedException {
        // Wait for an available valet
        waitForValets();
        // Generate a ticket
        int ticketId = parkingManager.delivery(car, this.parkingSpots);
        PrintInfo.getInstance().ticketCreated(ticketId);
        // Occupy a valet in order to accomplish an operation
        occupyValet();
        PrintInfo.getInstance().startDelivery();
        // Valet accomplishes the task...
        return ticketId;
    }

    public synchronized Car pickup(Integer ticketId) throws InterruptedException {
        waitForValets();
        // Prepare to pickup
        parkingManager.prepareToPickup(ticketId);
        // Start pickup process
        occupyValet();
        PrintInfo.getInstance().startPickup();
        Car parkedCar;
        do {
            parkedCar = parkingManager.pickup(ticketId);
            if (parkedCar == null)
                wait();
        } while (parkedCar == null);
        //releaseValet();
        parkingManager.pickupCompleted(ticketId);
        return parkedCar;
    }

    private List<Thread> factoryValets(int valetsNumber) {
        valets = new ArrayList<>();
        for (int i = 0; i < valetsNumber; i++) {
            Thread valet = new Thread(new Valet(this));
            valet.setName("Valet-" + id + "." + (i + 1));
            this.valets.add(valet);
            valet.start();
        }
        return valets;
    }

    private synchronized void waitForValets() throws InterruptedException {
        // TODO: Devo notificare che non c'è più un valet libero
        while (freeValets <= 0) {
            PrintInfo.getInstance().noFreeValets();
            wait();
        }
        PrintInfo.getInstance().freeValets(freeValets);
        this.freeValets--;
    }

    private synchronized void occupyValet() {
        // Notify all valet for a new task to accomplish
        notifyAll();
    }

    public synchronized void releaseValet() {
        this.freeValets++;
        // Notify all waiting drivers for a new delivery/pickup valet
        notifyAll();
    }

    private ParkingSpot[] factoryParkingSpots(int parkingSpotsNumber) {
        parkingSpots = new ParkingSpot[parkingSpotsNumber];
        for (int i = 0; i < parkingSpots.length; i++) {
            parkingSpots[i] = new ParkingSpot();
        }
        return parkingSpots;
    }

    public synchronized TaskStrategy accomplishTask() throws InterruptedException {
        TaskStrategy taskToAccomplish = this.parkingManager.accomplishTask();
        while (taskToAccomplish == null) {
            wait();
            taskToAccomplish = this.parkingManager.accomplishTask();
        }
        return taskToAccomplish;
    }

    @Override
    public String toString() {
        return "Parking {" +
                "id=" + id + ", " +
                "parkingSpotsNumber=" + parkingSpots.length +
                '}';
    }
}
