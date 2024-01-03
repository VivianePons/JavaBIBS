
public class ExempleStatic {
	
	/** 
	 * Un exemple de variable statique
	 */
	public static double temperature;
	
	/**
	 * Un exemple de constante
	 */
	public static final double kelvin = 273.15;
	
	/**
	 * Un exemple de méthode statique
	 * @return la température en degrés celcius
	 */
	public static double temperatureC() {
		return temperature - kelvin;
	}
	
	/**
	 * Autre exemple
	 * @return la température en degrés Farenheit
	 */
	public static double temperatureF() {
		return temperature * 1.8 - 459.67;
	}
}
