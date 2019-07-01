package auto;

import parking.Parking;
import parking.ParkingFullException;

public class Driver implements Runnable {
    private Parking targetParking;
    private Car car;
    private Integer ticket;

    public Driver(Parking targetParking, Car car) {
        this.targetParking = targetParking;
        this.car = car;
    }

    @Override
    public void run() {
        waitForValets();
        // Parking the car
        try {
            ticket = delivery();
        } catch (ParkingFullException e) {
            // Parking is full;
            e.printStackTrace();
        }
        car = pickup();
    }

    private void waitForValets() {
        while (targetParking.getFreeValets() <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int delivery() throws ParkingFullException {
        int tickedId = this.targetParking.delivery(this.car);
        this.car = null;
        return tickedId;
    }

    private Car pickup() {
        /*
        Car car = null;
        while (car == null){
            car = targetParking.pickup(this.ticket);
        }
        this.ticket = null;
        while (this.car == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
         */
        Car car = targetParking.pickup(this.ticket);
        this.ticket = null;
        return car;
    }

    public Car getCar() {
        return car;
    }

    public void setParking(Parking parking) {
        this.targetParking = parking;
    }
}

