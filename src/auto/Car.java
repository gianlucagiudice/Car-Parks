package auto;

import java.util.Objects;

/**
 * 
 * @author Gianluca Giudice
 * @author Andrea Tassi
 *
 */
public class Car {
	
	/**
	 * The license plate of the car.
	 */
	private String licensePlate;

	/**
	 * Public constructor using the license plate.
	 * @param licensePlate The license plate of the car.
	 */
    public Car(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    /**
     * Generates a hash code for the car using its license plate.
     */
    @Override
    public int hashCode() {
        return Objects.hash(licensePlate);
    }

    /**
     * Generates a string representation of the car, using its license plate.
     */
    @Override
    public String toString() {
        return "Car{" +
                "licensePlate='" + licensePlate + '\'' +
                '}';
    }
}
