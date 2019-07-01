package auto;

import parking.Parking;
import parking.exceptions.CarNotFoundException;
import parking.exceptions.FullParkingException;

public class Driver implements Runnable {
    private Parking targetParking;
    private Car car;
    private Integer ticketId;
    private int timeBeforePickup;

    public Driver(Parking targetParking, Car car, int timeBeforePickup) {
        this.targetParking = targetParking;
        this.car = car;
        this.ticketId = null;
        this.timeBeforePickup = timeBeforePickup;
    }

    @Override
    public void run() {
        waitForValets();
        // Parking the car
        delivery();

        sleepToPickup();

        waitForValets();
        pickup();
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

    private void delivery(){
        try {
            this.ticketId = this.targetParking.delivery(this.car);
        } catch (FullParkingException e) {
            e.printStackTrace();
        }
        this.car = null;
    }

    private void pickup() {
        try {
            this.car = this.targetParking.pickup(ticketId);
        } catch (CarNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
        this.ticketId = null;
    }

    public Car getCar() {
        return car;
    }

    public void setParking(Parking parkingTaskManager) {
        this.targetParking = parkingTaskManager;
    }
}

