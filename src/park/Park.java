package park;

import auto.Car;
import main.Main;
import park.ticketing.Ticket;

import java.util.*;

public class Park {
    private String id;
    private ParkSlot[] parkSlots;

    private List<Valet> valets;
    private int freeValets;

    private Map<Integer, Ticket> ticketMap = new HashMap<>();
    private Queue<Integer> deliveries;
    private Queue<Integer> pickups;

    public Park(String id, int parkSlotsNumber, int valetsNumber) {
        this.id = id;
        this.parkSlots = factorySlots(parkSlotsNumber);
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
        ParkSlot targetSlot = null;
        // Find a free parkSlot
        for (ParkSlot parkSlot : parkSlots) {
            synchronized (this) {
                if (parkSlot.isFree()) {
                    parkSlot.setCar(car);
                    targetSlot = parkSlot;
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

    private int factoryTicket(ParkSlot targetSlot, Car car) {
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

    private ParkSlot[] factorySlots(int parkSlotsNumber) {
        parkSlots = new ParkSlot[parkSlotsNumber];
        for (int i = 0; i < parkSlots.length; i++) {
            parkSlots[i] = new ParkSlot(Main.totalTimeSlices);
        }
        return parkSlots;
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
