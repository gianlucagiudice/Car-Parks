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
        // Parking the car
        delivery();
        // Sleep before pickup
        sleepToPickup();
        // Pickup car
        pickup();
    }

    private void delivery(){
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
            this.car = this.targetParking.pickup(ticketId);
        } catch (CarNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
        this.ticketId = null;
    }

}

