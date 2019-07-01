package parking;

import auto.Car;
import parking.exceptions.CarNotFoundException;
import parking.exceptions.FullParkingException;
import parking.manager.ParkingManager;
import parking.valet.TaskStrategy;
import parking.valet.Valet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        System.out.print("[" + getCurrentTime() + "]" + " Request for paking car: " +  car.toString() + "\n\t");

        //System.out.print("\t");
        waitForValets();
        // Generate a ticket
        int ticketId = parkingManager.delivery(car, this.parkingSpots);
        System.out.println("\t" + "Ticked acquired with ID: " + ticketId);
        // Occupy a valet in order to accomplish operation
        occupyValet();
        System.out.println("\t" + "Valet start serving . . .");
        // Valet accomplishes the task . . .
        return ticketId;
    }

    public Car pickup(Integer ticketId) throws CarNotFoundException, InterruptedException {
        //runValets();

        waitForValets();

        if (ticketId == null) {
            return null;
        } else {
            // Start pickup process
            // TODO: SPostare sopra pickup
            occupyValet();
            Car carParked = null;
            while (carParked == null) {
                //wait();
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
            Thread valet = new Thread(new Valet(this));
            this.valets.add(valet);
            valet.start();
        }
        return valets;
    }

    private synchronized void waitForValets() throws InterruptedException {
        while (freeValets <= 0) {
            System.out.println("No valets available, waiting. . .");
            wait();
        }
        System.out.println( freeValets + " free valets, serving");

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
        //return parkingManager.accomplishTask();
    }

    private String getCurrentTime(){
        return new SimpleDateFormat("HH:mm:ss:SSS").format(new Date());
    }
}
