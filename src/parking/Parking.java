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
        // Create a new parking manager
        this.parkingManager = new ParkingManager();
        System.out.println("sono entrato");
        // Factory all valets
        this.freeValets = valetsNumber;
        this.valets = factoryValets(valetsNumber);
    }

    public int delivery(Car car) throws FullParkingException, InterruptedException {


        //waitForValets();

        System.out.println("fatto");

        int ticketId = parkingManager.delivery(car, this.parkingSpots);

        System.out.println("Ticket ID: " + ticketId);


        occupyValet();
        // Valet accomplishes the task

        System.out.println("deivery finito");


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

    private void waitForValets() throws InterruptedException {
        while (getFreeValets() <= 0) {
            wait();
        }
    }

    //TODO: startValets
    private void runValets() {
        for (Thread valet : valets) {
            System.out.println("sto facendo il runValets");
            valet.start();
            System.out.println(valet);
        }
    }

    private synchronized void occupyValet() {
        this.freeValets--;
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
        System.out.println("Prima dell'accomplish");
        //Thread.sleep(20000);
        TaskStrategy t = this.parkingManager.accomplishTask();
        System.out.println("Dopo accomplish");
        //wait();
        System.out.println("Dopo wait accomplish");

        return t;
        //return parkingManager.accomplishTask();
    }

}
