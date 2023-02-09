package fr.upsaclay.bibs.students;

import java.util.List;

/// BEGIN SOLUTION
import java.util.Arrays;
import java.util.Iterator;
import java.util.ArrayList;
/// END SOLUTION

/**
 * Implements a list of grades (or gradable) and perform computations using grades and their coefficient
 * 
 * It is itself an implementation Gradable: the grade associated to the list is the mean
 * grade using coefficients.
 * 
 * In particular, this means that a GradeList can contain other GradeList
 * 
 * @author viviane
 *
 */
public class GradeList <E extends Gradable> implements Gradable, Iterable<E>, Comparable<E> {

	/**
	 * The field storing the grade list
	 */
	private final List<E> grades;
	
	/**
	 * The coefficient of the final grade obtained from the list
	 */
	private int coefficient = 1;
	
	/**
	 * Create an empty grade list
	 */
	public GradeList() {
		grades = new ArrayList<E>();
	}
	
	/**
	 * Create a grade list initiated with given grades
	 * @param grades of type Gradable
	 */
	public GradeList(E...grades) {
		this.grades = Arrays.asList(grades);
	}
	
	@Override
	public String toString() {
		return grades.toString();
	}
	
	/**
	 * Add a single grade to the list
	 * @param grade of type Gradable
	 */
	public void add(E grade) {
		this.grades.add(grade);
	}
	
	/**
	 * Add all grades from an iterable
	 * @param grades of type Gradable
	 */
	public void addAll(Iterable<E> grades) {
		for(E g: grades) {
			add(g);
		}
	}
	
	/**
	 * Return the number of grades in the list
	 * @return the number of grades
	 */
	public int numberOfGrades() {
		return grades.size();
	}
	
	/**
	 * Returns the maximal grade over the list
	 * @return a Gradable of generic type E
	 */
	public E max() {
		E grade = null;
		for(E g : grades) {
			if(grade == null ||Gradable.compares(g, grade) > 0) {
				grade = g;
			}
		}
		return grade;
	}
	
	/**
	 * Compute the mean grade of the list using grade's coefficient
	 * If the list is empty, it thows an IllegalStateException
	 */
	@Override
	public double gradeOver(double max_grade) {
		if(grades.size() == 0) {
			throw new IllegalStateException("Undefined grade (0 grades)");
		}
		/// BEGIN SOLUTION
		int div = 0;
		double sum = 0;
		for(Gradable g : grades) {
			div += g.getCoefficient();
			sum += g.gradeOver(max_grade) * g.getCoefficient(); 
		}
		return sum / div;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}

	@Override
	public int getCoefficient() {
		return coefficient;
	}

	/**
	 * Set the coefficient of the final grade obtained from the list
	 * @param coefficient an int
	 */
	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

	@Override
	public Iterator<E> iterator() {
		return grades.iterator();
	}
	
	@Override
	public int compareTo(Gradable g) {
		return Gradable.compares(this, g);
	}
	
}
