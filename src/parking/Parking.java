package parking;

import auto.Car;
import parking.exceptions.FullParkingException;
import parking.manager.ParkingManager;
import parking.valet.TaskStrategy;
import parking.valet.Valet;

import java.util.ArrayList;
import java.util.List;

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

    public int delivery(Car car) throws FullParkingException, InterruptedException {
        // Wait an available valet
        //System.out.print("[" + getCurrentTime() + "]" + " Request for delivery car: " +  car.toString() + "\n\t");
        waitForValets();
        // Generate a ticket
        int ticketId = parkingManager.delivery(car, this.parkingSpots);
        //System.out.println("\t" + "Ticked acquired with ID: " + ticketId);
        // Occupy a valet in order to accomplish operation
        occupyValet();
        //System.out.println("\t" + "Valet start serving . . .");
        // Valet accomplishes the task . . .
        return ticketId;
    }

    public synchronized Car pickup(Integer ticketId) throws InterruptedException {
        waitForValets();
        parkingManager.prepareParking(ticketId);
        // Start pickup process
        occupyValet();
        Car carParked;
        do {
            carParked = parkingManager.pickup(ticketId);
            if (carParked == null)
                wait();
        }while (carParked == null);
        //releaseValet();
        parkingManager.pickupCompleted(ticketId);
        return carParked;
    }

    private List<Thread> factoryValets(int valetsNumber) {
        valets = new ArrayList<>();
        for (int i = 0; i < valetsNumber; i++) {
            Thread valet = new Thread(new Valet(this));
            this.valets.add(valet);
            valet.start();
        }
        return valets;
    }

    private synchronized void waitForValets() throws InterruptedException {
        // TODO: Devo notificare che non c'è più un valet libero
        while (freeValets <= 0) {
            //System.out.println("No valets available, waiting. . .");
            wait();
        }
        //System.out.println( freeValets + " free valets, serving");
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

}
