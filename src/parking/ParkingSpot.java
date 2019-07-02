package parking;

import auto.Car;

public class ParkingSpot {
    private boolean isFree;
    private Car car;

    ParkingSpot() {
        this.isFree = true;
        this.car = null;
    }

    public boolean isFree() {
        return isFree;
    }

    public Car release() {
        this.isFree = true;
        Car parkedCar = this.car;
        this.car = null;
        return parkedCar;
    }

    public void occupy() {
        this.isFree = false;
    }

    public void occupy(Car car) {
        this.isFree = false;
        this.car = car;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "isFree=" + isFree +
                ", car=" + car +
                '}';
    }
}
