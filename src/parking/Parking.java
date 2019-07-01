package parking;

import auto.Car;
import parking.exceptions.CarNotFoundException;
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
        // Factory all valets
        this.valets = factoryValets(valetsNumber);
        // Start all valets
        runValets();
        this.freeValets = valetsNumber;
        // Create a new parking manager
        this.parkingManager = new ParkingManager();
        System.out.println("ciao ciao");
    }

    public int delivery(Car car) throws FullParkingException, InterruptedException {
        waitForValets();

        int ticketId = parkingManager.delivery(car, this.parkingSpots);
        occupyValet();
        // Valet accomplishes the task
        return ticketId;
    }

    public Car pickup(Integer ticketId) throws CarNotFoundException, InterruptedException {
        waitForValets();

        if (ticketId == null) {
            return null;
        } else {
            // Start pickup process
            // TODO: SPostare sopra pickup
            occupyValet();
            Car carParked = null;
            while (carParked == null) {
                wait();
                // TODO: Spostare sopra
                carParked = parkingManager.pickup(ticketId);
            }
            parkingManager.pickupCompleted(ticketId);
            releaseValet();
            return carParked;
        }
    }

    private List<Thread> factoryValets(int valetsNumber) {
        valets = new ArrayList<>();
        for (int i = 0; i < valetsNumber; i++) {
            this.valets.add(new Thread(new Valet(this)));
        }
        return valets;
    }

    private void waitForValets() throws InterruptedException {
        while (getFreeValets() <= 0) {
            wait();
        }
    }

    private void runValets() {
        for (Thread valet : valets) {
            valet.run();
        }
    }

    private synchronized void occupyValet() {
        this.freeValets--;
        // Notify all waiting valets for a new task
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

    private synchronized int getFreeValets() {
        return freeValets;
    }

    public TaskStrategy accomplishTask() throws InterruptedException {
        System.out.println("ciao");
        TaskStrategy t = this.parkingManager.accomplishTask();

        return t;
        //return parkingManager.accomplishTask();
    }

}
