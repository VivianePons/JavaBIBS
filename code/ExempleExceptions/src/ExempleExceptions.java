import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ExempleExceptions {
	
	public static char returnFirst(String s) {
		return s.charAt(0);
	}
	
	public static int finfinite(int a) {
		return finfinite(a);
	}
	
	public static void readNumbers(String filename) throws FileNotFoundException, MyProblemException {
		File f = new File(filename);
		Scanner scan = new Scanner(f);
		while(scan.hasNext()) {
			try {
				int v = scan.nextInt();
				System.out.println(v);
			} catch(InputMismatchException e)  {
				throw new MyProblemException(e);
			}
		} 
		scan.close();
	}
	
	public static void safeRead(String filename) {
		try {
			readNumbers(filename);
		} catch(FileNotFoundException e) {
			System.out.println("I cannot open this file : " + filename);
		} catch(MyProblemException e) {
			System.out.println("This file does not contain numbers : " + filename);
		}
	}
	
	

	public static void main(String[] args) {
		String s = "abc";
		// Exemple NumberFormatException
		// int v = Integer.valueOf(s);
		
		// Exemple StackOverflowError
		//finfinite(0);
		
		// Exemple NullPointerException
		String t = "bla";
		t = null;
		//returnFirst(t);
		
		// Exemple IndexOutOfBoundException
		List<Integer> mylist = new ArrayList<Integer>();
		//mylist.get(0);
		
		// Exemple ArrayIndexOutOfBoundException
		int[] tab = new int[3];
		//System.out.println(tab[3]);
		
		/*
		File f = new File("IDontExist");
		try {
			Scanner scan = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("I am still here");
		*/
		safeRead("blob");
		safeRead("resources/notnumbers.txt");
		safeRead("resources/numbers.txt");
		
		
	}

}
