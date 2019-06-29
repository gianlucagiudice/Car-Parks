package auto;

import java.util.List;
import java.util.Objects;

public class Driver {

	private String fiscalCode;
	private List<Car> cars;

	public Driver(String fiscalCode, List<Car> cars) {
		this.fiscalCode = fiscalCode;
		this.cars = cars;
	}

	public String getFiscalCode() {
		return fiscalCode;
	}

	public List<Car> getCars() {
		return cars;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Driver driver = (Driver) o;
		return fiscalCode.equals(driver.fiscalCode) &&
				Objects.equals(cars, driver.cars);
	}

	@Override
	public int hashCode() {
		return Objects.hash(fiscalCode, cars);
	}
}

