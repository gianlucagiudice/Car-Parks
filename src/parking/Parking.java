package parking;

import auto.Car;

import java.util.*;

public class Parking {
    private String id;
    private ParkingSpot[] parkingSpots;

    private List<Thread> valets;
    private int freeValets;

    private ParkingManager parkingManager;

    private Queue<Integer> deliveries;
    private Queue<Integer> pickups;

    public Parking(String id, int parkingSpotsNumber, int valetsNumber) {
        this.id = id;
        this.parkingSpots = factorySpots(parkingSpotsNumber);
        this.valets = factoryValets(valetsNumber);
        startValets();
        this.freeValets = valetsNumber;
        // ParkingManager must contain the id of the parking
        this.parkingManager = new ParkingManager(this.hashCode());
    }

    public int delivery(Car car) throws ParkingFullException {
        ParkingSpot targetSpot;
        int ticket;
        // Acquire a free parking spot
        targetSpot = parkingManager.acquireParkingSpot(this.parkingSpots);
        // Factory ticket
        ticket = parkingManager.factoryTicket(targetSpot, car);
        // TODO: Parking a car
        occupyValet();
        //deliveries
        releaseValet();
        notifyAll();
        return ticket;
    }

    public Car pickup(int ticket) {
        //TODO: Implement pickup car
        return null;
    }


    private List<Thread> factoryValets(int valetsNumber) {
        valets = new ArrayList<>();
        for (int i = 0; i < valetsNumber; i++) {
            this.valets.add(new Thread(new Valet()));
        }
        return valets;
    }

    private void startValets() {
        for (Thread valet : valets) {
            valet.start();
        }
    }

    private synchronized void occupyValet(){
        this.freeValets--;
    }

    private synchronized void releaseValet(){
        this.freeValets++;
    }

    private ParkingSpot[] factorySpots(int parkingSpotsNumber) {
        parkingSpots = new ParkingSpot[parkingSpotsNumber];
        for (int i = 0; i < parkingSpots.length; i++) {
            parkingSpots[i] = new ParkingSpot();
        }
        return parkingSpots;
    }

    public String getId() {
        return id;
    }

    public synchronized int getFreeValets() {
        return freeValets;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Parking{" +
                "id='" + id + '\'' +
                '}';
    }
}
