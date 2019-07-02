package parking.valet;

import auto.Car;
import parking.ParkingSpot;
import parking.manager.PrintInfo;

import java.util.HashMap;

public class PickupStrategy extends TaskStrategy {

    private HashMap<Integer, Car> pickups;
    private int ticketId;

    public PickupStrategy(ParkingSpot targetParkingSpot, HashMap<Integer, Car> pickups, int ticketId) {
        super(targetParkingSpot);
        this.pickups = pickups;
        this.ticketId = ticketId;
    }

    @Override
    void accomplish() throws InterruptedException {
        PrintInfo.getInstance().startPickup(Thread.currentThread(), targetParkingSpot);
    	sleepHalf();
        Car targetCar = targetParkingSpot.release();
        PrintInfo.getInstance().pickupCompleted(Thread.currentThread(), targetCar, targetParkingSpot);
        PrintInfo.getInstance().startGivingBack(Thread.currentThread(), targetCar);
        sleepHalf();
        pickups.put(ticketId, targetCar);
        PrintInfo.getInstance().givingBackCompleted(Thread.currentThread(), targetCar);
    }
}
