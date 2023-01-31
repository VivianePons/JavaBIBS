import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class ExempleCollections {
	

	public static void main(String[] args) {
		List<Integer> maListe = new ArrayList<Integer>();
		
		maListe.add(1);
		maListe.add(2);
		maListe.add(3);
		
		System.out.println(maListe);
		
		for(int i = 0; i < maListe.size(); i++) {
			System.out.println(maListe.get(i));
		}
		System.out.println();
		
		for(int v : maListe) {
			System.out.println(v);
		}
		System.out.println();

		System.out.println(maListe.indexOf(2));
		System.out.println();
		
		Integer[] monTableau = {1, 2, 3};
		List<Integer> maListe2 = Arrays.asList(monTableau);
		List<Integer> maListe3 = Arrays.asList(1, 2, 3);
		
		
		exempleReference();
		
		exempleEgalite();
		
		/*
		List<Integer> maListe4 = null;
		maListe4.add(0);
		*/
		
	}
	
	public static void exempleReference() {
		List<Integer> maListe = Arrays.asList(1, 2, 3);
		List<Integer> maListe2 = maListe;
		maListe.set(0, 4);
		System.out.println(maListe2);
		
		maListe = Arrays.asList(1, 2, 3);
		maListe2 = List.copyOf(maListe);
		maListe.set(0, 4);
		System.out.println(maListe2);
		
	}
	
	public static void exempleEgalite() {
		List<Integer> maListe = Arrays.asList(1, 2, 3);
		List<Integer> maListe2 = Arrays.asList(1, 2, 3);
		
		System.out.println(maListe == maListe2);
		
		System.out.println(maListe.equals(maListe2));
	}

}
