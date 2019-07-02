package parking.valet;

import parking.Parking;
import parking.manager.PrintInfo;

public class Valet implements Runnable {
    private Parking parking;

    public Valet(Parking parking) {
        this.parking = parking;
    }

    @Override
    public void run() {
        while (true){
            accomplishTask();
        }
    }

    private void accomplishTask() {
        try {
            parking.accomplishTask().accomplish();
            parking.releaseValet();
            PrintInfo.getInstance().valetReleased(Thread.currentThread());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
