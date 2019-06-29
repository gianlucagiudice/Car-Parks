package park.ticketing;

import auto.Car;
import park.Park;
import park.ParkSlot;

import java.util.Objects;

public class BookingTicket extends Ticket {
    private int startSlice;

    public BookingTicket(Park park, ParkSlot parkingSlot, Car car, int startSlice) {
        super(park, parkingSlot, car);
        this.startSlice = startSlice;
    }

    public int getStartSlice() {
        return startSlice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), startSlice);
    }
}
