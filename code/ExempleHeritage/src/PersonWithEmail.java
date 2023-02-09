
public class PersonWithEmail extends Person {
	
	private String email;

	public PersonWithEmail(String name) {
		super(name);
	}
	
	public PersonWithEmail(String name, String email) {
		this(name);
		setEmail(email);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return super.toString() + " -- email : " + getEmail();
	}

}
