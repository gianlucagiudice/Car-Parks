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
        	PrintInfo.getInstance().startAccomplishTask(Thread.currentThread());
            parking.accomplishTask().accomplish();
            PrintInfo.getInstance().taskAccomplished(Thread.currentThread());
            parking.releaseValet();
            PrintInfo.getInstance().valetReleased(Thread.currentThread());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
