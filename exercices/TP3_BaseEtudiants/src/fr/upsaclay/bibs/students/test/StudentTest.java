package fr.upsaclay.bibs.students.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.upsaclay.bibs.students.Student;

class StudentTest {

	@Test
	void testCreation() {
		/*
		assertEquals(new Student("Ada","Lovelace").lastName, "LOVELACE");
		assertEquals(new Student("  Ada ","Lovelace").firstName, "Ada");
		assertEquals(new Student("Ada"," Lovelace  ").lastName, "LOVELACE");
		assertEquals(new Student("Godefroy"," de Montmirail").firstName, "Godefroy");
		assertEquals(new Student("Godefroy"," de Montmirail").lastName, "DE MONTMIRAIL");
		assertEquals(new Student("Jean-Godefroy","de Montmirail").firstName, "Jean-Godefroy");
		assertEquals(new Student("Jean-Godefroy"," de Montmirail").lastName, "DE MONTMIRAIL");
		*/
	}
	
	@Test
	void testToString() {
		/*
		assertEquals(new Student("Ada", "Lovelace").toString(), "LOVELACE Ada");
		assertEquals(new Student("Godefroy"," de Montmirail").toString(), "DE MONTMIRAIL Godefroy");
		*/
	}
	
	@Test
	void testEquals() {
		/*
		assertEquals(new Student("Ada","Lovelace"), new Student("Ada","Lovelace"));
		assertEquals(new Student(" Ada","Lovelace "), new Student("Ada","Lovelace"));
		assertNotEquals(new Student("Ada","Lovelace"), new Student("Ada","De Montmirail"));
		assertNotEquals(new Student("Ada","Lovelace"), new Student("Godefroy","Lovelace"));
		assertNotEquals(new Student("Ada","Lovelace"), null);
		assertNotEquals(new Student("Ada","Lovelace"), "Ada Lovelace");
		*/
	}
	
	@Test
	void testHashCode() {
		/*
		assertEquals(new Student("Ada","Lovelace").hashCode(), new Student("Ada","Lovelace").hashCode());
		assertEquals(new Student(" Ada","Lovelace ").hashCode(), new Student("Ada","Lovelace").hashCode());
		assertNotEquals(new Student("Ada","Lovelace").hashCode(), new Student("Ada","De Montmirail").hashCode());
		assertNotEquals(new Student("Ada","Lovelace").hashCode(), new Student("Godefroy","Lovelace").hashCode());
		*/
	}
	
	@Test
	void testValidName() {
		/*
		assertTrue(Student.validName("Ada"));
		assertTrue(Student.validName("Lovelace"));
		assertTrue(Student.validName("Jean-Godefroy"));
		assertTrue(Student.validName("Adélaïde"));
		assertTrue(Student.validName("De Monmirail"));
		assertFalse(Student.validName("@bidule"));
		assertFalse(Student.validName("loverDu91"));
		*/
	}
	
	@Test
	void testExceptionCreation() {
		/*
		assertThrows(IllegalArgumentException.class, () -> new Student("Ada", "blob12blob"));
		assertThrows(IllegalArgumentException.class, () -> new Student("blob12blob", "Lovelace"));
		*/
	}
	
	@Test
	void testEmail() {
		/*
		Student st = new Student("Ada","Lovelave");
		String email = "ada.lovelace@universite-paris-saclay.fr";
		st.setEmail("ada.lovelace@universite-paris-saclay.fr");
		assertEquals(st.getEmail(), email);
		st = new Student("Ada", "Lovelace", email);
		assertEquals(st.getEmail(), email);
		assertThrows(IllegalArgumentException.class, () -> new Student("Ada", "blob12blob", email));
		assertThrows(IllegalArgumentException.class, () -> new Student("blob12blob", "Lovelace", email));
		*/
	}
	
	@Test
	void testValidEmail() {
		/*
		assertTrue(Student.validEmail("ada.lovelace@universite-paris-saclay.fr"));
		assertFalse(Student.validEmail("Ada Lovelace"));
		*/
	}
	
	@Test
	void testExceptionEmail() {
		/*
		Student st = new Student("Ada","Lovelave");
		assertThrows(IllegalArgumentException.class, () -> st.setEmail("notanemail"));
		assertThrows(IllegalArgumentException.class, () -> new Student("Ada", "Lovelace", "notanemail"));
		*/
	}
	
	@Test
	void testCompareTo() {
		/*
		assertEquals(new Student("Ada", "Lovelace").compareTo(new Student("Ada", "Lovelace")), 0);
		assertTrue(new Student("Ada", "Lovelace").compareTo(new Student("Godefroy", "De Montmirail")) > 0);
		assertTrue(new Student("Godefroy", "De Montmirail").compareTo(new Student("Ada", "Lovelace")) < 0);
		*/
	}
	
	@Test
	void testInstanceId() {
		/*
		Student st1 = new Student("Ada", "Lovelace");
		Student st2 = new Student("Ada", "Lovelace");
		assertEquals(st1.getInstanceId() + 1, st2.getInstanceId());
		*/
	}

}
