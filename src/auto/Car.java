package auto;

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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((licensePlate == null) ? 0 : licensePlate.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Car{" +
				"licensePlate='" + licensePlate + '\'' +
				'}';
	}
}
