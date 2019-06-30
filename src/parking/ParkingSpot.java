package parking;

import auto.Car;

public class ParkingSpot {
    private boolean free;
    private Car car;

    ParkingSpot() {
        this.free = true;
        this.car = null;
    }

    boolean isFree() {
        return free;
    }

    public Car release() {
        this.free = true;
        Car parked = this.car;
        car = null;
        return parked;
    }

    void occupy() {
        this.free = false;
    }

}
