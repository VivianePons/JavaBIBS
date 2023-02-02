package fr.upsaclay.bibs.students;

/**
 * An implementation of Gradable to store a single grade
 * 
 * The grade is created with a default max_gade value which is used for display
 * 
 * @author viviane
 *
 */
public class Grade implements Gradable {

	/**
	 * The grade 
	 */
	private double grade;
	
	/**
	 * the default max_grade corresponding to the grade
	 */
	private double max_grade;
	
	private int coefficient = 1;
	
	/**
	 * Creates a grade over the given max_grade
	 * 
	 * Throws an IllegalArgumentException if grade is negative or superior to max_grade 
	 * or if max_grade is 0
	 * @param grade the grade as a positive double value
	 * @param max_grade the maximal possible grade
	 */
	public Grade(double grade, double max_grade) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	/**
	 * Creates a grade over the given max_grade and sets a grade coefficient
	 * @param grade the grade as a double value 
	 * @param max_grade the maximal possible grade
	 * @param coefficient the coefficient associated to the grade
	 */
	public Grade(double grade, double max_grade, int coefficient) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public double gradeOver(double max_grade) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	/**
	 * Change the default max grade and the grade accordingly
	 * 
	 * For example if the grade was 10/20, and the max_grade is changed to 100
	 * the new grade is 50/100
	 * 
	 * @param max_grade the new max grade
	 */
	public void setMaxGrade(double max_grade) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	/**
	 * Return the default max grade
	 * @return the max grade as a double value
	 */
	public double getMaxGrade() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	/**
	 * Return the grade as stored (over the default max grade)
	 * @return the grade as double value
	 */
	public double getGrade() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	

	@Override
	public int getCoefficient() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	public void setCoefficient(int coefficient) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	@Override
	public String toString() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	@Override
	public boolean equals(Object obj) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	@Override
	public int hashCode() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	@Override
	public int compareTo(Gradable g) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
}
