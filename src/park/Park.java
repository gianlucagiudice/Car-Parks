package park;

import auto.Car;
import main.Main;

import java.util.*;

public class Park {
    private String id;
    private ParkSlot[] parkSlots;

    private List<Valet> valets;
    private int freeValets;

    private ParkManager parkManager;

    private Queue<Integer> deliveries;
    private Queue<Integer> pickups;

    public Park(String id, int parkSlotsNumber, int valetsNumber) {
        this.id = id;
        this.parkSlots = factorySlots(parkSlotsNumber);
        this.valets = factoryValets(valetsNumber);
        startValets();
        this.freeValets = valetsNumber;
        // ParkManager must contains the id of the park
        this.parkManager = new ParkManager(this.hashCode());
    }

    public int delivery(Car car) throws ParkFullException {
        ParkSlot targetSlot;
        int ticket;
        // Acquire a free parking slot
        targetSlot = parkManager.acquireParkingSlot(this.parkSlots);
        // Factory ticket
        ticket = parkManager.factoryTicket(targetSlot, car);
        // TODO: Park a car
        occupyValet();

        releaseValet();
        notifyAll();
        return ticket;
    }

    public Car pickup(int ticket) {
        //TODO: Implement pickup car
        return null;
    }


    private List<Valet> factoryValets(int valetsNumber) {
        valets = new ArrayList<>();
        for (int i = 0; i < valetsNumber; i++) {
            this.valets.add(new Valet());
        }
        return valets;
    }

    private void startValets() {
        for (Valet v : this.valets)
            v.start();
    }

    private synchronized void occupyValet(){
        this.freeValets--;
    }

    private synchronized void releaseValet(){
        this.freeValets++;
    }

    private ParkSlot[] factorySlots(int parkSlotsNumber) {
        parkSlots = new ParkSlot[parkSlotsNumber];
        for (int i = 0; i < parkSlots.length; i++) {
            parkSlots[i] = new ParkSlot(Main.totalTimeSlices);
        }
        return parkSlots;
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
        return "Park{" +
                "id='" + id + '\'' +
                '}';
    }
}
