package parking.valet;

import auto.Car;
import parking.manager.ParkingSpot;

import java.util.HashMap;

public class PickupStrategy extends TaskStrategy {

    private HashMap<Integer, Car> givesBack;
    private int ticketId;

    public PickupStrategy(ParkingSpot targetParkingSpot, HashMap<Integer, Car> givesBack, int ticketId) {
        super(targetParkingSpot);
        this.givesBack = givesBack;
        this.ticketId = ticketId;
    }

    @Override
    void accomplish() throws InterruptedException {
        sleepHalf();
        Car carTarget = targetParkingSpot.release();
        sleepHalf();
        givesBack.put(ticketId, carTarget);
    }
}
