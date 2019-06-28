package auto;

import java.util.List;

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

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		return "Driver{" +
				"fiscalCode='" + fiscalCode + '\'' +
				'}';
	}
}
