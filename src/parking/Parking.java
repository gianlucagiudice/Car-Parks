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
    
    public int getId() {
		return id;
	}
    
	public synchronized int delivery(Car car) throws FullParkingException, InterruptedException {
        // Wait for an available valet
        while (freeValets <= 0) {
        	PrintInfo.noFreeValets();
            wait();
        }
        PrintInfo.freeValets(freeValets);
        this.freeValets--;
        
        // Generate a ticket
        int ticketId = parkingManager.delivery(car, this.parkingSpots);
		PrintInfo.ticketCreated(ticketId);
		
        // Occupy a valet in order to accomplish a task (delivery)
        notifyAll();
        // The valet accomplishes the task (delivery)...
        return ticketId;
    }

    public synchronized Car pickup(Integer ticketId) throws InterruptedException {
        // Wait for an available valet
    	while (freeValets <= 0) {
        	PrintInfo.noFreeValets();
            wait();
        }
        PrintInfo.freeValets(freeValets);
        this.freeValets--;

        // Prepare to pickup
        parkingManager.prepareToPickup(ticketId);
        
        // Start pickup process
        notifyAll();

        PrintInfo.waitingForCar(Thread.currentThread());
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
            valet.setName("Valet-P" + id + "." + (i + 1));
            this.valets.add(valet);
            valet.start();
        }
        return valets;
    }

    public synchronized void releaseValet() {
        this.freeValets++;
        // Notify all waiting drivers for a new delivery/pickup valet
        notifyAll();
    }

    private ParkingSpot[] factoryParkingSpots(int parkingSpotsNumber) {
        parkingSpots = new ParkingSpot[parkingSpotsNumber];
        for (int i = 0; i < parkingSpots.length; i++) {
            parkingSpots[i] = new ParkingSpot(i + 1);
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
                "id=P" + id + ", " +
                "parkingSpotsNumber=" + parkingSpots.length +
                '}';
    }
}
