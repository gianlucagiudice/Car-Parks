package parking.valet;

import auto.Car;
import parking.ParkingSpot;

public class PickupStrategy extends TaskStrategy {

    public PickupStrategy(ParkingSpot targetParkingSpot) {
        super(targetParkingSpot);
    }

    @Override
    Car accomplishTask() throws InterruptedException {
        sleep();
        return targetParkingSpot.release();
    }
}
