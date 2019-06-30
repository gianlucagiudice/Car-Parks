package park.ticketing;

import auto.Car;
import park.Park;
import park.ParkingSlot;

import java.util.Objects;

public class BookingTicket extends Ticket {
    private int startTimeSlice;

    public BookingTicket(Park park, ParkingSlot parkingSlot, Car car, int startTimeSlice) {
        super(park, parkingSlot, car);
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
