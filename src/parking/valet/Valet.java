package parking.valet;

import parking.Parking;
import util.Logger;

/**
 * 
 * @author Gianluca Giudice
 * @author Andrea Tassi
 *
 */
public class Valet extends Logger implements Runnable {
    private Parking parking;
    private TaskStrategy taskToAccomplish;

    public Valet(Parking parking) {
        this.parking = parking;
    }

    @Override
    public void run() {
        while (parking.isOpen()) {
            accomplishTask();
        }
    }

    private void accomplishTask() {
        try {
            this.taskToAccomplish = parking.accomplishTask();
            if (taskToAccomplish == null) {
                // Parking closed
                return;
            }
            if (taskToAccomplish instanceof DeliveryStrategy)
                setOperation(Operation.delivering);
            else
                setOperation(Operation.pickingUp);

            taskToAccomplish.accomplish();
            parking.releaseValet();
            this.taskToAccomplish = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Valet{" +
                "parkingID= " + parking.getId() +
                ", parking=" + parking +
                "} ";
    }
}
