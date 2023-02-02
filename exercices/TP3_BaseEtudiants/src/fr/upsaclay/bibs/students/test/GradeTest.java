package fr.upsaclay.bibs.students.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.upsaclay.bibs.students.Grade;

class GradeTest {

	@Test
	void testCreate() {
		assertEquals(new Grade(10,20).gradeOver(20), 10);
		assertEquals(new Grade(0,10,1).gradeOver(20), 0);
		assertThrows(IllegalArgumentException.class, () -> new Grade(-1,20));
		assertThrows(IllegalArgumentException.class, () -> new Grade(25,20));
		assertThrows(IllegalArgumentException.class, () -> new Grade(0,0));
	}
	
	@Test
	void testGradeOver() {
		assertEquals(new Grade(10,20).gradeOver(100),50);
		assertEquals(new Grade(100,100).gradeOver(20),20);
		assertEquals(new Grade(0,5).gradeOver(1),0);
		assertEquals(new Grade(15,20).gradeOver(10), 7.5);
	}
	
	@Test
	void testSetGetMaxGrade() {
		Grade g = new Grade(10,20);
		assertEquals(g.getMaxGrade(), 20);
		g.setMaxGrade(100);
		assertEquals(g.getMaxGrade(),100);
		assertEquals(g, new Grade(10,20));
		assertThrows(IllegalArgumentException.class, () -> g.setMaxGrade(0));
		assertThrows(IllegalArgumentException.class, () -> g.setMaxGrade(-1));
	}
	
	@Test
	void testGetGrade() {
		assertEquals(new Grade(10,20).getGrade(), 10);
	}
	
	@Test
	void testSetGetCoefficient() {
		assertEquals(new Grade(10,20).getCoefficient(), 1);
		assertEquals(new Grade(10,20,2).getCoefficient(), 2);
		Grade g = new Grade(15,20);
		g.setCoefficient(3);
		assertEquals(g.getCoefficient(), 3);
	}
	
	@Test
	void testToString() {
		assertEquals(new Grade(10,20).toString(),"10.0/20.0");
		assertEquals(new Grade(50,100).toString(), "50.0/100.0");
	}
	
	@Test
	void testEquals() {
		assertEquals(new Grade(0,20), new Grade(0,100));
		assertEquals(new Grade(10,20), new Grade(50,100));
		assertEquals(new Grade(10,100), new Grade(1,10));
		assertEquals(new Grade(10,10), new Grade(20,20));
		assertEquals(new Grade(4.5,10), new Grade(9, 20));
		assertEquals(new Grade(45,100), new Grade(4.5,10));
		assertNotEquals(new Grade(1,10), new Grade(15,100));
		assertNotEquals(new Grade(1,10), null);
		assertNotEquals(new Grade(1,10),"1/10");
	}
	
	@Test
	void testHashCode() {
		assertEquals(new Grade(0,20).hashCode(), new Grade(0,100).hashCode());
		assertEquals(new Grade(10,20).hashCode(), new Grade(50,100).hashCode());
		assertEquals(new Grade(10,100).hashCode(), new Grade(1,10).hashCode());
		assertEquals(new Grade(10,10).hashCode(), new Grade(20,20).hashCode());
		assertEquals(new Grade(4.5,10).hashCode(), new Grade(9, 20).hashCode());
		assertEquals(new Grade(45,100).hashCode(), new Grade(4.5,10).hashCode());
		assertNotEquals(new Grade(1,10).hashCode(), new Grade(15,100).hashCode());
	}
	
	@Test
	void testCompareTo() {
		assertEquals(new Grade(0,10).compareTo(new Grade(0,100)), 0);
		assertTrue(new Grade(0,20).compareTo(new Grade(10,100)) < 0);
		assertTrue(new Grade(50,100).compareTo(new Grade(2,20)) > 0);
	}

}
