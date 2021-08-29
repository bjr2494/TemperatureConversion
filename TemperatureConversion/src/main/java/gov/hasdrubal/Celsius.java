package gov.hasdrubal;

public class Celsius {
	
	protected static double toFahrenheit(Temperature temperatureStart) {
		return temperatureStart.getNumber() * 9/5 + 32;
	}
	
	protected static double toKelvin(Temperature temperatureStart) {
		return temperatureStart.getNumber() - 273.15;
	}
}
