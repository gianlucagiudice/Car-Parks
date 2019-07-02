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
	 * 
	 */
	private String licensePlate;

	/**
	 * 
	 * @param licensePlate
	 */
    public Car(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    /**
     * 
     */
    @Override
    public int hashCode() {
        return Objects.hash(licensePlate);
    }

    @Override
    public String toString() {
        return "Car{" +
                "licensePlate='" + licensePlate + '\'' +
                '}';
    }
}
