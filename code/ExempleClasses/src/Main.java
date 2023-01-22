
public class Main {

	public static void main(String[] args) {
		ExempleStatic.temperature = 300;
		System.out.print("Constante Kelvin : ");
		System.out.println(ExempleStatic.kelvin);
		System.out.print("Température en deg C : ");
		System.out.println(ExempleStatic.temperatureC());
		System.out.print("Température en deg F : ");
		System.out.println(ExempleStatic.temperatureF());

	}

}
