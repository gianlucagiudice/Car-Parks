package parking.valet;

import auto.Car;
import parking.manager.ParkingSpot;


public class DeliveryStrategy extends TaskStrategy {
    private Car targetCar;

    public DeliveryStrategy(ParkingSpot targetParkingSpot, Car targetCar) {
        super(targetParkingSpot);
        this.targetCar = targetCar;
    }

    @Override
    void accomplish() throws InterruptedException {
        sleep();
        targetParkingSpot.occupy(targetCar);
    }
}
