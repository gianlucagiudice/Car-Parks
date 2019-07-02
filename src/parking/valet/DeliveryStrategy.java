package parking.valet;

import auto.Car;
import parking.ParkingSpot;
import parking.manager.PrintInfo;


public class DeliveryStrategy extends TaskStrategy {
    private Car targetCar;

    public DeliveryStrategy(ParkingSpot targetParkingSpot, Car targetCar) {
        super(targetParkingSpot);
        this.targetCar = targetCar;
    }

    @Override
    void accomplish() throws InterruptedException {
        PrintInfo.getInstance().startDelivery(Thread.currentThread(), targetCar, targetParkingSpot);
    	sleep();
        targetParkingSpot.occupy(targetCar);
        PrintInfo.getInstance().deliveryCompleted(Thread.currentThread(), targetCar, targetParkingSpot);
    }
}
