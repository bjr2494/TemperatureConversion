package gov.hasdrubal;

public class Kelvin {

	protected static double toFahrenheit(Temperature temperatureStart) {
		return temperatureStart.getNumber() * 9/5 - 459.67;
	}
	
	protected static double toCelsius(Temperature temperatureStart) {
		return temperatureStart.getNumber() - 273.15;
	}
}
