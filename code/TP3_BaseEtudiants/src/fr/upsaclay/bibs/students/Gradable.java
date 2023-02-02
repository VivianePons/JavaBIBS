package fr.upsaclay.bibs.students;

/**
 * This interface describes objects that possess a way to compute a grade
 * 
 * It extends Comparable<Gradable> the comparaison should be done using the grade value
 * 
 * @author Viviane Pons
 *
 */
public interface Gradable extends Comparable<Gradable>{

	/**
	 * Returns the grade over the given max_grade
	 * @param max_grade
	 * @return the grade computed using max_grade
	 */
	public double gradeOver(double max_grade);
	
	/**
	 * Returns the grade coefficient
	 * @return the grade coefficient
	 */
	public int getCoefficient();
}
