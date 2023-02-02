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
		/// BEGIN SOLUTION
		if(grade < 0 || grade > max_grade || max_grade == 0) {
			throw new IllegalArgumentException("Invalid grade");
		}
		this.grade = grade;
		this.max_grade = max_grade;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}
	
	/**
	 * Creates a grade over the given max_grade and sets a grade coefficient
	 * @param grade the grade as a double value 
	 * @param max_grade the maximal possible grade
	 * @param coefficient the coefficient associated to the grade
	 */
	public Grade(double grade, double max_grade, int coefficient) {
		/// BEGIN SOLUTION
		this(grade, max_grade);
		this.coefficient = coefficient;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}

	@Override
	public double gradeOver(double max_grade) {
		/// BEGIN SOLUTION
		return grade *max_grade / this.max_grade;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
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
		/// BEGIN SOLUTION
		if(max_grade <= 0) {
			throw new IllegalArgumentException("Invalid max grade");
		}
		grade = gradeOver(max_grade);
		this.max_grade = max_grade;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}
	
	/**
	 * Return the default max grade
	 * @return the max grade as a double value
	 */
	public double getMaxGrade() {
		/// BEGIN SOLUTION
		return max_grade;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}
	
	/**
	 * Return the grade as stored (over the default max grade)
	 * @return the grade as double value
	 */
	public double getGrade() {
		/// BEGIN SOLUTION
		return grade;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}
	

	@Override
	public int getCoefficient() {
		/// BEGIN SOLUTION
		return coefficient;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}

	public void setCoefficient(int coefficient) {
		/// BEGIN SOLUTION
		this.coefficient = coefficient;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}
	
	@Override
	public String toString() {
		/// BEGIN SOLUTION
		return String.valueOf(grade) + "/" + String.valueOf(max_grade);
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}
	
	@Override
	public boolean equals(Object obj) {
		/// BEGIN SOLUTION
		if(obj == null) {
			return false;
		}
		if(!Grade.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		Grade g = (Grade)obj;
		return g.gradeOver(max_grade) == grade;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}
	
	@Override
	public int hashCode() {
		/// BEGIN SOLUTION
		return Double.hashCode(grade/max_grade);
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}
	
	@Override
	public int compareTo(Gradable g) {
		/// BEGIN SOLUTION
		double g2 = g.gradeOver(max_grade);
		if(grade == g2) {
			return 0;
		}
		return grade < g2 ? -1 : 1;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}
}
