package parking;

import auto.Car;

public class ParkingSpot {
    private boolean isFree;
    private Car car;


    ParkingSpot() {
        this.isFree = true;
        this.car = null;
    }

    boolean isFree() {
        return isFree;
    }

    public Car release() {
        this.isFree = true;
        Car parked = this.car;
        car = null;
        return parked;
    }

    void occupy() {
        this.isFree = false;
    }

}
