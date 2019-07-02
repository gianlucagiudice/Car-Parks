package parking.valet;

import main.Main;
import parking.ParkingSpot;

/**
 * 
 * @author Gianluca Giudice
 * @author Andrea Tassi
 *
 */
public abstract class TaskStrategy {
    ParkingSpot targetParkingSpot;

    TaskStrategy(ParkingSpot targetParkingSpot) {
        this.targetParkingSpot = targetParkingSpot;
    }

    abstract void accomplish() throws InterruptedException;

    void sleep() throws InterruptedException {
        sleep(Main.valetSleep);
    }

    void sleepHalf() throws InterruptedException {
        sleep(Main.valetSleep / 2);
    }

    private void sleep(int time) throws InterruptedException {
        Thread.sleep(time);
    }
}
