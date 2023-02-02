package fr.upsaclay.bibs;

import fr.upsaclay.bibs.students.Student;
import fr.upsaclay.bibs.students.Grade;

public class BaseEtudiants {

	public static void main(String[] args) {
		/// BEGIN SOLUTION
		Student st = new Student("Ada", "Lovelace");
		System.out.println(st);
		
		Student st2 = new Student("Ada", "Lovelace");
		
		System.out.println(st.equals(st2));
		
		System.out.println(new Grade(10,20));
		
				
		/// END SOLUTION

	}

}
