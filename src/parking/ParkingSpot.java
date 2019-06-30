package parking;

import auto.Car;
import main.Main;

public class ParkingSpot {
    private boolean[] booking;
    private boolean free;
    private Car car;

    ParkingSpot(int timeSlicesNumber) {
        this.booking = new boolean[timeSlicesNumber];
        this.car = null;
        this.free = true;
    }

    private boolean isBooked(int timeSlice) {
        return booking[timeSlice];
    }

    public boolean isBookable(int timeSlice, int numberSlices) {
        for (int i = timeSlice; i < timeSlice + numberSlices; i++)
            if (isBooked(i))
                return false;
        return true;
    }

    boolean isFree() {
        return free && !booking[Main.currentTimeSlice];
    }

    public Car release() {
        this.free = true;
        Car parked = this.car;
        car = null;
        return parked;
    }

    void occupy(){
        this.free = false;
    }

    /*
    private void freeSlices() {
        setBooking(0, booking.length, false);
    }

    private void setBooking(int timeSliceboolean bookOrFree) {
        for (int i = timeSlice; i < timeSlice + numberSlices; i++)
            setBooking(i, bookOrFree);
    }

    private void setBooking(int timeSlice, boolean bookOrFree) {
        booking[timeSlice] = bookOrFree;
    }
     */

}