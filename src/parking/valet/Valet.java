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
            TaskStrategy taskToAccomplish = parking.accomplishTask();
            if (taskToAccomplish == null)
                nothingToDo();
            else
                accomplishTask(taskToAccomplish);
        }
    }

    private void accomplishTask(TaskStrategy taskToAccomplish){
        try {
            taskToAccomplish.accomplishTask();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        parking.releaseValet();
    }

    private void nothingToDo(){
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
