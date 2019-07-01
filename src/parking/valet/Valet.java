package parking.valet;

import parking.Parking;

public class Valet implements Runnable {
    private Parking parking;

    public Valet(Parking parking) {
        this.parking = parking;
    }

    @Override
    public void run() {
        nothingToDo();
        while (true) {
            TaskStrategy taskToAccomplish = parking.accomplishTask();
            System.out.println(taskToAccomplish);
            if (taskToAccomplish == null)
                nothingToDo();
            else {
                accomplishTask(taskToAccomplish);
                nothingToDo();
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

    private void nothingToDo() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
            parking.releaseValet();
        }
    }

}
