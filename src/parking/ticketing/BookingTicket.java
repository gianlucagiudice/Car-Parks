package parking.ticketing;

import auto.Car;
import parking.ParkingSpot;

import java.util.Objects;

public class BookingTicket extends Ticket {
    private int startSlice;

    public BookingTicket(int parkID, ParkingSpot carParkedSlot, Car carParked, int startSlice) {
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
