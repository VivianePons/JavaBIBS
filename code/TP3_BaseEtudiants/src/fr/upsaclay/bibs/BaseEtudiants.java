package fr.upsaclay.bibs;

/// BEGIN SOLUTION
import fr.upsaclay.bibs.students.Student;
import fr.upsaclay.bibs.students.Gradable;
import fr.upsaclay.bibs.students.Grade;
import fr.upsaclay.bibs.students.GradeList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/// END SOLUTION

public class BaseEtudiants {

	public static void main(String[] args) {
		/// BEGIN SOLUTION
		int[] coefficients = {2,1,1};
		File file = new File("resources/grades.txt");
		GradeList<Student> students = new GradeList<Student>();
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] entries = line.split(",");
				Student st = new Student(entries[1], entries[0]);
				for(int i = 2; i < entries.length; i++) {
					st.getGradeList().add(new Grade(Double.valueOf(entries[i]), 20, coefficients[i-2]));
				}
				students.add(st);
			}
			scan.close();
			for(Student student : students) {
				System.out.println(student + " " + student.gradeOver(20));
			}
			System.out.print("Moyenne de la classe : ");
			System.out.println(students.gradeOver(20));
			System.out.print("Etudiant-e avec la meilleure note : ");
			Student maxStudent = students.max();
			System.out.print(maxStudent);
			System.out.print(" avec la note ");
			System.out.println(maxStudent.gradeOver(20));
			
			
		} catch(FileNotFoundException e) {
			System.out.println("Problem finding the file");
			e.printStackTrace();
		}
		/// END SOLUTION

	}

}
