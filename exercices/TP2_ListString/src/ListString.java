import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListString {

	public static void main(String[] args) {
		
		
		List<String> noms = new ArrayList<String>();
		
		// Remplissez la fonction main pour qu'elle lise depuis l'entrée standard
		// Une liste de nom jusqu'à ce qu'on écrive STOP
		// les noms seront enregistrés en MAJUSCULE dans la liste `noms`
		
		
		// Doit afficher les noms en majuscule
		System.out.println(noms);
		
		// Complétez la fonction `queDesLettres` ci-dessous 
		
		// Modifier votre code ci-dessus pour que seuls les "vrais noms" (composés de lettres et éventuellement
		// d'un symbole "-" soit ajoutés dans la liste)
		
		// Complétez les deux fonctions prenomsSansE et prenomsSansA tel que le code commenté ci-dessous fonctionne
		
		/*
		List<String> listeTeste = Arrays.asList("VIVIANE", "SÉBASTIEN", "ÉMILIE", "OCTAVE", "LUCAS", "LAURA", "LILLY");
		// Affiche les prénoms sans S de la liste entrée au clavier
		System.out.println(prenomsSansS(noms));
		// Affiche [VIVIANE, ÉMILIE, OCTAVE, LAURA, LILLY]
		System.out.println(prenomsSansS(listeTeste));
		// Affiche les prénoms sans C de la liste entrée au clavier
		System.out.println(prenomsSansC(noms));
		// Affiche [VIVIANE, SÉBASTIEN, ÉMILIE, LAURA, LILLY]
		System.out.println(prenomsSansC(listeTeste));
		*/
		

	}
	
	
	/**
	 * Teste si un nom ne contient que des lettres (minuscules ou majuscule) ou des "-"
	 * @param nom la chaine de caractère à tester
	 * @return True si c'est un nom contenant des lettres et des tirets "-"
	 */
	public static boolean prequeQueDesLettres(String nom) {
		// La ligne suivante doit être supprimée et remplacée par votre code
		// Lisez le boc d'explications "Exception Java"
		throw new UnsupportedOperationException("Not implemented yet");
		
		// Pour résoudre l'exercice, lisez les expications "char et String" et "Chacracter et char : les wrappers"
	}
	
	/**
	 * Crée une NOUVELLE liste contenant uniquement les prénoms sans la lettre S
	 * @param noms une liste de noms en majuscule
	 * @return une nouvelle liste contenant les noms sans S
	 */
	public static List<String> prenomsSansS(List<String> noms) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	/**
	 * Crée une NOUVELLE liste contenant uniquement les prénoms sans la lettre C
	 * @param noms une liste de noms en majuscule
	 * @return une nouvelle liste contenant les noms sans C
	 */
	public static List<String> prenomsSansC(List<String> noms) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

}
