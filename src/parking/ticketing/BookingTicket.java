package parking.ticketing;

import auto.Car;
import parking.ParkingSpot;

import java.util.Objects;

public class BookingTicket extends Ticket {
    private int startTimeSlice;

    public BookingTicket(int parkID, ParkingSpot carParkedSpot, Car carParked, int startTimeSlice) {
        super(parkID, carParkedSpot, carParked);
        this.startTimeSlice = startTimeSlice;
    }

    public int getStartTimeSlice() {
        return startTimeSlice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), startTimeSlice);
    }
}
