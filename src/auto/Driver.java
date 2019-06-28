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
		StringBuilder builder = new StringBuilder();
		builder.append("Driver [fiscalCode=");
		builder.append(fiscalCode);
		builder.append(", cars=");
		builder.append(cars);
		builder.append("]");
		return builder.toString();
	}
}
