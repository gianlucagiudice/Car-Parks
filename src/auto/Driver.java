package auto;

import parking.Parking;
import parking.ParkingFullException;

public class Driver implements Runnable {
    private Parking targetParking;
    private Car car;
    private Integer ticket;
    private int timeBeforePickup;

    public Driver(Parking targetParking, Car car, int timeBeforePickup) {
        this.targetParking = targetParking;
        this.car = car;
        this.ticket = null;
        this.timeBeforePickup = timeBeforePickup;
    }

    @Override
    public void run() {
        waitForValets();

        // Parking the car
        try {
            ticket = delivery();
        } catch (ParkingFullException e) {
            // Parking is full;
            e.printStackTrace();
        }

        sleepToPickup();

        waitForValets();
        car = pickup();
    }

    private void waitForValets() {
        while (targetParking.getFreeValets() <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sleepToPickup() {
        try {
            Thread.sleep(timeBeforePickup);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int delivery() throws ParkingFullException {
        int tickedId = this.targetParking.delivery(this.car);
        this.car = null;
        return tickedId;
    }

    private Car pickup() {
        Car car = targetParking.pickup(this.ticket);
        this.ticket = null;
        return car;
    }

    public Car getCar() {
        return car;
    }

    public void setParking(Parking parking) {
        this.targetParking = parking;
    }
}

