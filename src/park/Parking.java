package park;

import java.util.List;
import java.util.Objects;

public class Parking {
    private String id;
    private int timeSlotNumber;
    private List<ParkingSlot> slots;

    public Parking(String id, int timeSlotNumber, List<ParkingSlot> slots) {
        this.id = id;
        this.timeSlotNumber = timeSlotNumber;
        this.slots = slots;
    }

    public String getId() {
        return id;
    }

    public int getTimeSlotNumber() {
        return timeSlotNumber;
    }

    public List<ParkingSlot> getSlots() {
        return slots;
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
