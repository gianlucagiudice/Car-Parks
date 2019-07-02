package auto;

import parking.Parking;
import parking.exceptions.FullParkingException;
import parking.manager.PrintInfo;

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
    	PrintInfo.getInstance().currentTime();
    	PrintInfo.getInstance().deliveryRequest(Thread.currentThread(), car);
        delivery();
        PrintInfo.getInstance().ticketAcquired(Thread.currentThread(), ticketId);
        
        // Sleep before pickup
        sleepBeforePickup();
        
        // Pickup car
        PrintInfo.getInstance().currentTime();
    	PrintInfo.getInstance().pickupRequest(Thread.currentThread(), ticketId);
        pickup();
        PrintInfo.getInstance().carPickedUp(Thread.currentThread(), car);
    }

    private void delivery() {
        try {
            this.ticketId = this.targetParking.delivery(this.car);
        } catch (FullParkingException | InterruptedException e) {
            e.printStackTrace();
        }
        this.car = null;
    }

    private void sleepBeforePickup() {
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
}

