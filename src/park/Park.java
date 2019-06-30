package park;

import auto.Car;
import main.Main;
import park.ticketing.Ticket;

import java.util.*;

public class Park {
    private String id;
    private ParkingSlot[] parkingSlots;

    private List<Valet> valets;
    private int freeValets;

    private Map<Integer, Ticket> ticketMap = new HashMap<>();
    private Queue<Integer> deliveries;
    private Queue<Integer> pickups;

    public Park(String id, int parkingSlotsNumber, int valetsNumber) {
        this.id = id;
        this.parkingSlots = factorySlots(parkingSlotsNumber);
        this.valets = factoryValets(valetsNumber);
        startValets();
        this.freeValets = valetsNumber;
    }

    private void startValets() {
        for (Valet v : this.valets)
            v.start();
    }

    // Park a car
    public int park(Car car) throws ParkFullException {
        ParkingSlot targetSlot = null;
        // Find a free ParkingSlot
        for (ParkingSlot ParkingSlot : parkingSlots) {
            synchronized (this) {
                if (ParkingSlot.isFree()) {
                    ParkingSlot.setCar(car);
                    targetSlot = ParkingSlot;
                }
            }
            if (targetSlot != null)
                break;
        }
        if (targetSlot == null)
            throw new ParkFullException("Park is full");
        // Generate the ticket
        return factoryTicket(targetSlot, car);
    }

    private int factoryTicket(ParkingSlot targetSlot, Car car) {
        // Generate the ticket
        Ticket generatedTicket = new Ticket(this, targetSlot, car);
        this.ticketMap.put(generatedTicket.hashCode(), generatedTicket);
        return generatedTicket.hashCode();
    }

    private List<Valet> factoryValets(int valetsNumber) {
        valets = new ArrayList<>();
        for (int i = 0; i < valetsNumber; i++) {
            this.valets.add(new Valet());
        }
        return valets;
    }

    private ParkingSlot[] factorySlots(int parkingSlotsNumber) {
        parkingSlots = new ParkingSlot[parkingSlotsNumber];
        for (int i = 0; i < parkingSlots.length; i++) {
            parkingSlots[i] = new ParkingSlot(Main.totalTimeSlices);
        }
        return parkingSlots;
    }

    public synchronized int getFreeValets() {
        return freeValets;
    }

    public String getId() {
        return id;
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
