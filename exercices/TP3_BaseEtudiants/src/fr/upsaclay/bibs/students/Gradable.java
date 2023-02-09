package fr.upsaclay.bibs.students;

/**
 * This interface describes objects that possess a way to compute a grade
 * 
 * It extends Comparable<Gradable> the comparaison should be done using the grade value
 * 
 * @author Viviane Pons
 *
 */
public interface Gradable {

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
	default public int getCoefficient() {
		return 1;
	}
	
	/**
	 * A static method provided to compare Gradable
	 * @param g1 an object of type Gradable
	 * @param g2 an object of type Gradable
	 * @return 0 if g1 and g2 represents the same grade, a negative number if g1 is smaller 
	 *         and a positive number if g1 is bigger
	 */
	public static int compares(Gradable g1, Gradable g2) {
		double v1 = g1.gradeOver(1);
		double v2 = g2.gradeOver(1);
		if(v1 == v2) {
			return 0;
		}
		return v1 < v2 ? -1 : 1;
	}
	
}
