package main;

import auto.Car;
import auto.Driver;
import parking.Parking;

public class Main1 {

    public static void main(String[] args) {
        Parking p1 = new Parking(10, 2);

        Thread d1 = new Thread(new Driver(p1, new Car("AB1234"), 1));
        d1.start();


    }
}
