
public class Person {
	
	private static int count = 0;

	private final int id;
	private final String name;
	
	public Person(String name) {
		count++;
		this.id = count;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "Person " + id + " : " + name;
	}
}
