package auto;

import java.util.Objects;

public class Car {
	private String licensePlate;

	public Car(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

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
