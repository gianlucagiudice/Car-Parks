package parking.valet;

import parking.Parking;
import util.Logger;

public class Valet extends Logger implements Runnable {
    private Parking parking;
    private TaskStrategy taskToAccomplish;

    public Valet(Parking parking) {
        this.parking = parking;
    }

    @Override
    public void run() {
        while (true) {
            accomplishTask();
        }
    }

    private void accomplishTask() {
        try {
            this.taskToAccomplish = parking.accomplishTask();

            if (taskToAccomplish instanceof DeliveryStrategy)
                setOperation(Operation.delivering);
            else
                setOperation(Operation.pickingUp);

            taskToAccomplish.accomplish();

            setOperation(Operation.waiting);
            parking.releaseValet();
            this.taskToAccomplish = null;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Valet{" +
                "parking=" + parking +
                "} ";
    }

}
