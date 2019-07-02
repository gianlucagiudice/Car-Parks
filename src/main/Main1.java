package main;

import auto.Car;
import auto.Driver;
import parking.Parking;

/**
 * 
 * @author Gianluca Giudice
 * @author Andrea Tassi
 *
 */
public class Main1 {

    public static void main(String[] args) {
        Thread.currentThread().setName("Main1");
        
        // Variables to modify in order to carry out different tests
        int parkingsNumber = 3; // Total number of parkings
        int driversNumber = 10; // Total number of drivers
        
        Parking[] parkings = new Parking[parkingsNumber];
        for(int i = 0; i < parkings.length; i++)
        	parkings[i] = new Parking(10, 2);
        
        Car[] cars = new Car[driversNumber];
        Driver[] drivers = new Driver[driversNumber];
        Thread[] driverThreads = new Thread[driversNumber];
        for(int i = 0; i < drivers.length; i++) {
        	cars[i] = new Car("AA000" + (char) ('A' + i / 26) + (char) ('A' + i % 26));
        	
        	int p = (int) (Math.random() * parkingsNumber);
        	drivers[i] = new Driver(parkings[p], cars[i], 1);
            
            driverThreads[i] = new Thread(drivers[i]);
            driverThreads[i].setName("Driver-" + (i + 1));
            
            driverThreads[i].start();
        }
    }
}
