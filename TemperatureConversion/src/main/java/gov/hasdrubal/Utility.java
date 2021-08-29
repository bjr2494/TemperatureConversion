package gov.hasdrubal;

public class Utility {

	protected static Temperature toScaleEnd(Scale scaleEnd, double endingTemperature) {
		Temperature temperatureEnd = createTemperatureEnd();
		configureTemperatureEnd(temperatureEnd, scaleEnd, endingTemperature);
		editTemperatureEndNumber(temperatureEnd, endingTemperature);

		return temperatureEnd;
	}
	
	private static Temperature createTemperatureEnd() {
		return new Temperature();
	}
	
	private static void configureTemperatureEnd(Temperature temperatureEnd, Scale scaleEnd, double endingTemperature) {
		temperatureEnd.setScale(scaleEnd);
		temperatureEnd.setNumber(endingTemperature);
	}
	
	private static void editTemperatureEndNumber(Temperature temperatureEnd, double endingTemperature) {
		String temperatureEndContent = String.valueOf(endingTemperature);
		if (temperatureEndContent.contains(".")) {
			temperatureEnd.setContent(String.valueOf(endingTemperature));			
		}
		else
		{
			temperatureEnd.setContent(temperatureEndContent.substring(0, temperatureEndContent.length() - 2));
		}
	}
	
	protected static double determineOperation(Temperature temperatureStart, Scale scaleStart, Scale scaleEnd) {
			switch (scaleStart.toString()) {
			case "FAHRENHEIT":
				switch (scaleEnd.toString()) {
				case "CELSIUS":
					return Fahrenheit.toCelsius(temperatureStart);
				case "KELVIN":
					return Fahrenheit.toKelvin(temperatureStart);
				}
				break;
			case "CELSIUS":
				switch (scaleEnd.toString()) {
				case "FAHRENHEIT":
					return Celsius.toFahrenheit(temperatureStart);
				case "KELVIN":
					return Celsius.toKelvin(temperatureStart);
				}
				break;
			case "KELVIN":
				switch (scaleEnd.toString()) {
				case "FAHRENHEIT":
					return Kelvin.toFahrenheit(temperatureStart);
				case "CELSIUS":
					return Kelvin.toCelsius(temperatureStart);
				}
				break;
			}
			return 0.0;
	}
}
