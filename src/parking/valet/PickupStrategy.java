package parking.valet;

import auto.Car;
import parking.ParkingSpot;

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
        sleepHalf();
        Car targetCar = targetParkingSpot.release();
        sleepHalf();
        pickups.put(ticketId, targetCar);
    }
}
