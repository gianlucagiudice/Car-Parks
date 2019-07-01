package parking.valet;

import auto.Car;
import parking.Parking;

public class Valet implements Runnable {

    private Parking parking;
    private Car car;

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
            this.car = taskToAccomplish.accomplishTask();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(taskToAccomplish instanceof PickupStrategy)
        	this.parking.addGiveBackCar(car);
        parking.releaseValet();
    }

    private void nothingToDo(){
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Car getCar() {
        return this.car;
    }
}
