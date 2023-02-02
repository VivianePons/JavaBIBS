package fr.upsaclay.bibs.students;

import java.util.regex.Pattern;

/**
 * A class representig a student with some grades
 * @author viviane
 *
 */
/// BEGIN SOLUTION
public class Student implements Comparable<Student> {
/// END SOLUTION
/* BEGIN UNCOMMENT
public class Student {
END UNCOMMENT */

	/// BEGIN SOLUTION
	/**
	 * first name of the the student
	 */
	public final String firstName;
	
	/**
	 * last name of the the student
	 */
	public final String lastName;
	
	/** 
	 * The student email
	 */
	private String email;
	
	/**
	 * A special field to identify the instance
	 */
	private int instanceId;
	
	private static int count = 0;
	/// END SOLUTION
	
	public Student(String firstName, String lastName) {
		
		/// BEGIN SOLUTION
		this.firstName = firstName.strip();
		this.lastName = lastName.strip().toUpperCase();
		
		if(! validName(this.firstName) || ! validName(this.lastName)) {
			throw new IllegalArgumentException("Not a valid name");
		}
		
		count+= 1;
		instanceId = count;
		/// END SOLUTION
	}
	
	/// BEGIN SOLUTION
	
	public Student(String firstName, String lastName, String email) {
		this(firstName, lastName);
		setEmail(email);
	}

	/**
	 * Checks that a given String is a valid name
	 * ie it contains only: letters, spaces, or "-" 
	 * @param name a String representing a name 
	 * @return true if it's a valid name, false otherwise
	 */
	public static boolean validName(String name) {
		for(int i=0; i< name.length(); i++) {
			char c = name.charAt(i);
			if(!Character.isLetter(c) && c != ' ' && c !='-') {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks that a given String is a valid email using regular expression
	 * @param email a String representing an email 
	 * @return true if it's a valid email, false otherwise
	 */
	public static boolean validEmail(String email) {
		String regexPattern = "^(.+)@(\\S+)$";
	    return Pattern.compile(regexPattern)
	      .matcher(email)
	      .matches();
	}
	
	@Override
	public String toString() {
		return lastName + " " + firstName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!Student.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		Student s2 = (Student)obj;
		return (firstName.equals(s2.firstName) && lastName.equals(s2.lastName));
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(validEmail(email)) {
			this.email = email;
		} else {
			throw new IllegalArgumentException("Not a valid email");
		}
		
	}
	
	@Override
	public int compareTo(Student st) {
		return toString().compareTo(st.toString());
	}
	
	/**
	 * Return the specific id assigned to the instance
	 * Two instances can be considered equal but have different id
	 * @return
	 */
	public int getInstanceId() {
		return instanceId;
	}
	/// END SOLUTION


	
	
	
}
