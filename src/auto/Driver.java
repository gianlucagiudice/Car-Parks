package auto;

import parking.Parking;
import parking.exceptions.FullParkingException;
import util.Logger;

/**
 * 
 * @author Gianluca Giudice
 * @author Andrea Tassi
 *
 */
public class Driver extends Logger implements Runnable {
    private Parking targetParking;
    private Car car;
    private Integer ticketId;
    private int timeBeforePickup;

    /**
     * Public constructor.
     * @param targetParking The parking where the driver wants to delivery his car.
     * @param car The driver's car.
     * @param timeBeforePickup The time to sleep before picking up the car.
     */
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

    /**
     * Deliveries the driver's car to the target parking.
     */
    private void delivery() {
        try {
            this.ticketId = this.targetParking.delivery(this.car);
        } catch (FullParkingException | InterruptedException e) {
            e.printStackTrace();
        }
        this.car = null;
    }

    /**
     * Sleeps before picking up the car.
     */
    private void sleepToPickup() {
        try {
            Thread.sleep(timeBeforePickup);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Picks up the car.
     */
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
                "targetParking=" + targetParking.getId() +
                ", car=" + car +
                ", ticketId=" + ticketId +
                "} ";
    }
}

