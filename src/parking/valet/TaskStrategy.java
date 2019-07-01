package parking.valet;

import auto.Car;
import main.Main;
import parking.ParkingSpot;

public abstract class TaskStrategy {
    ParkingSpot targetParkingSpot;

    TaskStrategy(ParkingSpot targetParkingSpot) {
        this.targetParkingSpot = targetParkingSpot;
    }

    abstract Car accomplishTask() throws InterruptedException;

    void sleep() throws InterruptedException {
        Thread.sleep(Main.valetSleep);
    }
}
