package park.ticketing;

import auto.Car;
import park.ParkSlot;

import java.util.Objects;

public class BookingTicket extends Ticket {
    private int startSlice;

    public BookingTicket(int parkID, ParkSlot carParkedSlot, Car carParked, int startSlice) {
        super(parkID, carParkedSlot, carParked);
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
