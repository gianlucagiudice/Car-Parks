package park;

import auto.Car;
import main.Main;

public class ParkSlot {
    private boolean[] booking;
    private Car car;

    ParkSlot(int timeSlicesNumber){
        this.booking = new boolean[timeSlicesNumber];
        this.car = null;
        freeSlots();
    }

    private boolean isBooked(int timeSlice){
        return booking[timeSlice];
    }

    public boolean isBookable(int timeSlice, int numberSlices){
        for (int i = timeSlice; i < timeSlice + numberSlices; i++)
            if (isBooked(i))
                return false;
        return true;
    }


    boolean isFree(){
        return car == null && !booking[Main.currentTimeSlice];
    }

    public Car getCar() {
        return car;
    }

    void setCar(Car car) {
        this.car = car;
    }

    private void freeSlots(){
        setBooking(0, booking.length, false);
    }

    private void setBooking(int timeSlice, int numberSlices, boolean bookOrFree){
        for (int i = timeSlice; i < timeSlice + numberSlices; i++)
            setBooking(i, bookOrFree);
    }

    private void setBooking(int timeSlice, boolean bookOrFree){
        booking[timeSlice] = bookOrFree;
    }

}
