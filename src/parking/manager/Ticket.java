package parking.manager;

import auto.Car;
import parking.ParkingSpot;

import java.util.Objects;

public class Ticket {
    private ParkingSpot parkedCarSpot;
    private Car parkedCar;

    Ticket(ParkingSpot parkedCarSpot, Car parkedCar) {
        this.parkedCarSpot = parkedCarSpot;
        this.parkedCar = parkedCar;
    }

    ParkingSpot getParkedCarSpot() {
        return parkedCarSpot;
    }

    Car getParkedCar() {
        return parkedCar;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkedCarSpot, parkedCar);
    }
}
