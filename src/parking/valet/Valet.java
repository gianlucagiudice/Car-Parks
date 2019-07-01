package parking.valet;

import parking.Parking;

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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
