package parking;

import auto.Car;
import parking.exceptions.CarNotFoundException;
import parking.exceptions.FullParkingException;
import parking.manager.ParkingManager;
import parking.manager.PrintInfo;
import parking.valet.TaskStrategy;
import parking.valet.Valet;

import java.util.ArrayList;
import java.util.List;

public class Parking {
	private String id;
    private ParkingSpot[] parkingSpots;
    private List<Thread> valets;
    private int freeValets;
    private ParkingManager parkingManager;

    public Parking(String id, int parkingSpotsNumber, int valetsNumber) {
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

    public Car pickup(Integer ticketId) throws CarNotFoundException, InterruptedException {
        //runValets();

        waitForValets();

        if (ticketId == null) {
            return null;
        } else {
            // Start pickup process
            // TODO: Spostare sopra pickup
            occupyValet();
            PrintInfo.getInstance().startPickup();
            Car parkedCar = null;
            while (parkedCar == null) {
                //wait();
                // TODO: Spostare sopra
                parkedCar = parkingManager.pickup(ticketId);
            }
            parkingManager.pickupCompleted(ticketId);
            releaseValet();
            return parkedCar;
        }
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
        while (freeValets <= 0) {
            PrintInfo.getInstance().noFreeValets();
            wait();
        }
        PrintInfo.getInstance().freeValets(freeValets);
    }

    private synchronized void occupyValet() {
        this.freeValets--;
        // Notify all valet for a new task to complete
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
        TaskStrategy t = this.parkingManager.accomplishTask();
        while (t == null){
            wait();
            t = this.parkingManager.accomplishTask();

        }
        return t;
    }
    
    @Override
    public String toString() {
        return "Parking {" +
                "id=" + id + ", " +
                "parkingSpotsNumber=" + parkingSpots.length +
                '}';
    }
}
