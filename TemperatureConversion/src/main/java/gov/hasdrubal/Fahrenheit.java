package gov.hasdrubal;

public class Fahrenheit {

	protected static double toCelsius(Temperature temperatureStart) {
		return (temperatureStart.getNumber() - 32) * 5/9;
	}
	
	protected static double toKelvin(Temperature temperatureStart) {
		return (temperatureStart.getNumber() + 459.67) * 5/9;
	}
}
