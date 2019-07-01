package parking;

import auto.Car;
import parking.exceptions.CarNotFoundException;
import parking.exceptions.FullParkingException;
import parking.manager.*;
import parking.valet.*;

import java.util.*;

public class Parking {
    private String id;
    private ParkingSpot[] parkingSpots;

    private List<Thread> valets;
    private int freeValets;

    private ParkingManager parkingManager;


    public Parking(String id, int parkingSpotsNumber, int valetsNumber) {
        this.id = id;
        // Factory all parking spots
        this.parkingSpots = factoryParkingSpots(parkingSpotsNumber);
        // Factory all valets
        this.valets = factoryValets(valetsNumber);
        // Start all valets
        runValets();
        this.freeValets = valetsNumber;
        // Create a new parking manager
        this.parkingManager = new ParkingManager();
    }

    public int delivery(Car car) throws FullParkingException {
        int ticketId = parkingManager.delivery(car, this.parkingSpots);
        occupyValet();
        // Valet accomplishes the task
        return ticketId;
    }


    public Car pickup(Integer ticketId) throws CarNotFoundException, InterruptedException {
        if (ticketId == null) {
            return null;
        } else {
            // Start pickup process
            occupyValet();
            Car carParked = null;
            while (carParked == null){
                wait();
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



    //TODO: Wait for valets


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

    // TODO: Rilasciare il valet dopo il pickup
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

    public synchronized int getFreeValets() {
        return freeValets;
    }

    public TaskStrategy accomplishTask() {
        return parkingManager.accomplishTask();
    }

}
