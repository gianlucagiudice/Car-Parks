package parking.valet;

import parking.Parking;

public class Valet implements Runnable {
    private Parking parking;

    public Valet(Parking parking) {
        this.parking = parking;
    }

    @Override
    public void run() {

        while (true) {
            try {
                parking.accomplishTask().accomplish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    private void accomplishTask(TaskStrategy taskToAccomplish) {
        try {
            taskToAccomplish.accomplish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
