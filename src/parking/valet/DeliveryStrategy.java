package parking.valet;

import auto.Car;
import parking.ParkingSpot;

public class DeliveryStrategy extends TaskStrategy {
    Car targetCar;

    public DeliveryStrategy(ParkingSpot targetParkingSpot, Car targetCar) {
        super(targetParkingSpot);
        this.targetCar = targetCar;
    }

    @Override
    Car accomplishTask() throws InterruptedException {
        sleep();
        targetParkingSpot.occupy(targetCar);
        return null;
    }
}
