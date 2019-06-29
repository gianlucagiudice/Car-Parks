package park;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class Park {
    private String id;
    private ParkSlot[] slots;
    private int timeSlotNumber;
    private List<Valet> valets;
    private Queue<Ticket> deliveries;
    private Queue<Ticket> pickups;

    public Park(String id, int timeSlotNumber, int parkSlotsNumber, int valetsNumber) {
        this.id = id;
        this.timeSlotNumber = timeSlotNumber;
        this.slots = factorySlots(parkSlotsNumber);
        this.valets = factoryValets(valetsNumber);
    }

    private List<Valet> factoryValets(int valetsNumber) {
        valets = new ArrayList<>();
        for (int i = 0; i < valetsNumber; i++) {
            this.valets.add(new Valet());
        }
        return valets;
    }

    private ParkSlot[] factorySlots(int parkSlotsNumber) {
        slots = new ParkSlot[parkSlotsNumber];
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new ParkSlot(this.timeSlotNumber);
        }
        return slots;
    }

    public String getId() {
        return id;
    }

    public int getTimeSlotNumber() {
        return timeSlotNumber;
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
