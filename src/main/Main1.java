package main;

import java.util.Random;

import auto.Car;
import auto.Driver;
import parking.Parking;
import parking.manager.PrintInfo;

public class Main1 {

    public static void main(String[] args) {
        Thread.currentThread().setName("Main1");
        PrintInfo.newThread(Thread.currentThread());
        PrintInfo.startThread(Thread.currentThread());
        PrintInfo.threadState(Thread.currentThread());
        
        // Variables to modify in order to carry out different tests
        int parkingsNumber = 3; // Total number of parkings
        int driversNumber = 10; // Total number of drivers
        
        Parking[] parkings = new Parking[parkingsNumber];
        for(int i = 0; i < parkings.length; i++) {
        	parkings[i] = new Parking(i + 1, 10, 2);
        	PrintInfo.newParking(parkings[i]);
        }
        
        Car[] cars = new Car[driversNumber];
        Driver[] drivers = new Driver[driversNumber];
        Thread[] driverThreads = new Thread[driversNumber];
        for(int i = 0; i < drivers.length; i++) {
        	cars[i] = new Car("AA000" + (char) ('A' + i / 26) + (char) ('A' + i % 26));
        	PrintInfo.newCar(cars[i]);
        	
        	int p = (int) (Math.random() * parkingsNumber);
        	drivers[i] = new Driver(parkings[p], cars[i], 1);
            PrintInfo.newDriver(drivers[i]);
            
            driverThreads[i] = new Thread(drivers[i]);
            driverThreads[i].setName("Driver-" + (i + 1));
            PrintInfo.newThread(driverThreads[i]);
            PrintInfo.threadState(driverThreads[i]);
            
            driverThreads[i].start();
            PrintInfo.startThread(driverThreads[i]);
            PrintInfo.threadState(driverThreads[i]);
        }
        
        PrintInfo.terminatedThread(Thread.currentThread());
    }
    
    public static String randomLicensePlate() {
    	String licensePlate = "";
    	for(int i = 0; i < 2; i++)
    		licensePlate += randomLetter();
    	for(int i = 0; i < 3; i++)
    		licensePlate += (int) (Math.random() * 10);
    	for(int i = 0; i < 2; i++)
    		licensePlate += randomLetter();
    	return licensePlate;
    }
    
    public static char randomLetter() {
    	int c = (int) (Math.random() * 26);
    	return (char) ('A' + c);
    }
}
