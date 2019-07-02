package main;

import auto.Car;
import auto.Driver;
import parking.Parking;
import parking.manager.PrintInfo;

public class Main1 {

    public static void main(String[] args) {
        Parking p1 = new Parking("P1", 10, 2);
        PrintInfo.getInstance().newParking(p1);
        Car c1 = new Car("AB123CD");
        PrintInfo.getInstance().newCar(c1);
        Driver d1 = new Driver(p1, c1, 1);
        PrintInfo.getInstance().newDriver();

        Thread t1 = new Thread(d1);
        t1.setName("Driver-1");
        PrintInfo.getInstance().newThread(t1);
        t1.start();
        PrintInfo.getInstance().startThread(t1);
    }
}
