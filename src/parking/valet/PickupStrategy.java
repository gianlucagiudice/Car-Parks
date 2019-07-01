package parking.valet;

import auto.Car;
import parking.ParkingSpot;

import java.util.HashMap;

public class PickupStrategy extends TaskStrategy {

    private HashMap<Integer, Car> pickus;
    private int ticketId;

    public PickupStrategy(ParkingSpot targetParkingSpot, HashMap<Integer, Car> pickus, int ticketId) {
        super(targetParkingSpot);
        this.pickus = pickus;
        this.ticketId = ticketId;
    }

    @Override
    void accomplish() throws InterruptedException {
        sleepHalf();
        Car carTarget = targetParkingSpot.release();
        sleepHalf();
        pickus.put(ticketId, carTarget);
    }
}
