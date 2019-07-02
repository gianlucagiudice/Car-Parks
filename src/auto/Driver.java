package auto;

import parking.Parking;
import parking.exceptions.FullParkingException;
import util.Logger;

public class Driver extends Logger implements Runnable {
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
        // Parking the car
        setOperation(Operation.delivering);
        delivery();
        setOperation(Operation.delivered);
        // Sleep before pickup
        setOperation(Operation.sleeping);
        sleepToPickup();
        // Pickup car
        setOperation(Operation.pickingUp);
        pickup();
        setOperation(Operation.pickedUp);
    }

    private void delivery() {
        try {
            this.ticketId = this.targetParking.delivery(this.car);
        } catch (FullParkingException | InterruptedException e) {
            e.printStackTrace();
        }
        this.car = null;
    }

    private void sleepToPickup() {
        try {
            Thread.sleep(timeBeforePickup);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void pickup() {
        try {
            this.car = this.targetParking.pickup(this.ticketId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.ticketId = null;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "car=" + car +
                ", ticketId=" + ticketId +
                "} ";
    }
}

