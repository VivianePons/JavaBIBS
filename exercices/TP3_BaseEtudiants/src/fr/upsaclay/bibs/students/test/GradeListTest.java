package fr.upsaclay.bibs.students.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Arrays;
import java.util.List;
import fr.upsaclay.bibs.students.Gradable;
import fr.upsaclay.bibs.students.Grade;
import fr.upsaclay.bibs.students.GradeList;

class GradeListTest {

	@Test
	void testCreate() {
		assertEquals(new GradeList<Grade>().toString(), "[]");
		assertEquals(new GradeList<Grade>(new Grade(10,20), new Grade(5,20)).toString(), "[10.0/20.0, 5.0/20.0]");
	}
	
	@Test
	void testAddGrade() {
		GradeList<Grade> gl = new GradeList<Grade>();
		gl.add(new Grade(10,20));
		assertEquals(gl.toString(), "[10.0/20.0]");
		assertEquals(gl.numberOfGrades(), 1);
	}
	
	@Test
	void testAddGrades() {
		GradeList<Grade> gl = new GradeList<Grade>();
		gl.addAll(Arrays.asList(new Grade(10,20), new Grade(5,20)));
		assertEquals(gl.toString(), "[10.0/20.0, 5.0/20.0]");
		assertEquals(gl.numberOfGrades(), 2);
	}
	
	@Test
	void testIterator() {
		List<Grade> grades = Arrays.asList(new Grade(10,20), new Grade(5,20));
		GradeList<Grade> gl = new GradeList<Grade>();
		gl.addAll(grades);
		Iterator<Grade> it =  grades.iterator();
		for(Gradable g1 : gl) {
			Grade g2 = it.next();
			assertEquals(g1,g2);
		}
	}
	
	@Test
	void testMax() {
		GradeList<Grade> gl = new GradeList<Grade>(new Grade(10,20), new Grade(5,20,2), new Grade(15,20,2));
		assertEquals(gl.max(), new Grade(15,20));
	}
	
	@Test
	void testGradeOver() {
		/*
		GradeList<Grade> gl = new GradeList<Grade>(new Grade(10,20), new Grade(5,20,2), new Grade(15,20,2));
		assertEquals(gl.gradeOver(100), 50);
		assertEquals(gl.gradeOver(20), 10);
		GradeList<Grade> gl2 = new GradeList<Grade>();
		assertThrows(IllegalStateException.class, () -> gl2.gradeOver(10));
		*/
	}
	
	@Test 
	void testSetGetCoefficient() {
	}

}
