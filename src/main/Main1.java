package main;

import auto.Car;
import auto.Driver;
import parking.manager.Parking;

import java.util.Random;

public class Main1 {

    public static void main(String[] args) {
        Parking[] parkings = factoryParkings();
        Thread[] drivers = factoryDriver(parkings);
        startDrivers(drivers);
        closeAllParking(parkings);
    }

    private static String getRandomLicensePlate() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "1234567890";
        Random rnd = new Random();

        StringBuilder licensePlate = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            licensePlate.append(alphabet.charAt(rnd.nextInt(alphabet.length())));
        }
        for (int i = 0; i < 3; i++) {
            licensePlate.append(numbers.charAt(rnd.nextInt(numbers.length())));
        }
        for (int i = 0; i < 2; i++) {
            licensePlate.append(alphabet.charAt(rnd.nextInt(alphabet.length())));
        }
        return licensePlate.toString();
    }

    private static Parking[] factoryParkings() {
        Parking[] parkings = new Parking[Main.parkingsNumber];
        for (int i = 0; i < Main.parkingsNumber; i++) {
            parkings[i] = new Parking(i, Main.parkingSpotNumber, Main.valetsNumber);
        }
        return parkings;
    }

    private static Thread[] factoryDriver(Parking[] parkings) {
        Thread[] drivers = new Thread[Main.driversNumber];
        Random rnd = new Random();
        for (int i = 0; i < Main.driversNumber; i++) {
            Parking randomTargetParking = parkings[rnd.nextInt(parkings.length)];
            Driver d = new Driver(randomTargetParking, new Car(getRandomLicensePlate()), Main.driverSleepBeforePickup);
            drivers[i] = new Thread(d);
        }
        return drivers;
    }

    private static void startDrivers(Thread[] drivers) {
        for (Thread driver : drivers){
            driver.start();
        }
        for (Thread driver : drivers){
            try {
                driver.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ;
        }
    }

    private static void closeAllParking(Parking[] parkings) {
        for (Parking parking : parkings)
            parking.closeParking();
    }

}
