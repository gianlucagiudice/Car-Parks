package parking.manager;

import auto.Car;
import parking.ParkingSpot;

import java.util.Objects;

/**
 * 
 * @author Gianluca Giudice
 * @author Andrea Tassi
 *
 */
public class Ticket {
    private ParkingSpot carParkedSpot;
    private Car parkedCar;

    /**
     * Constructor.
     * @param carParkedSpot
     * @param parkedCar
     */
    Ticket(ParkingSpot carParkedSpot, Car parkedCar) {
        this.carParkedSpot = carParkedSpot;
        this.parkedCar = parkedCar;
    }

    /**
     * Getter for the parking spot.
     * @return The parking spot where the car is parked.
     */
    ParkingSpot getParkedCarSpot() {
        return carParkedSpot;
    }

    /**
     * Getter for the parked car.
     * @return The parked car.
     */
    Car getParkedCar() {
        return parkedCar;
    }

    @Override
    public int hashCode() {
        return Objects.hash(carParkedSpot, parkedCar);
    }
}
