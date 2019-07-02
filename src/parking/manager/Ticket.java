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

    Ticket(ParkingSpot carParkedSpot, Car parkedCar) {
        this.carParkedSpot = carParkedSpot;
        this.parkedCar = parkedCar;
    }

    ParkingSpot getParkedCarSpot() {
        return carParkedSpot;
    }

    Car getParkedCar() {
        return parkedCar;
    }

    @Override
    public int hashCode() {
        return Objects.hash(carParkedSpot, parkedCar);
    }
}
