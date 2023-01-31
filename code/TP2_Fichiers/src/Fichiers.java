import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/// BEGIN SOLUTION
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.Map;
import java.util.Set;
/// END SOLUTION

public class Fichiers {

	public static void main(String[] args) {
		File file = new File("resources/names.txt");
		try {
			Scanner scan = new Scanner(file);
			/// BEGIN SOLUTION
			System.out.println(formatNOMPrenom("Godefroy de Montmirail"));
			List<String> etudiants = listeEtudiants(scan);
			//System.out.println(etudiants);
			scan.close();
			
			//afficheEtudiants(System.out, etudiants);
			File output = new File("output/names.txt");
			PrintStream out = new PrintStream(output);
			afficheEtudiants(out, etudiants);
			out.close();
			Set<String> prenoms = extractPrenoms(etudiants);
			System.out.print("Nombre de prénoms différents : ");
			System.out.println(prenoms.size());
			
			File gradesFile = new File("resources/grades.txt");
			scan = new Scanner(gradesFile);
			Map<String, Double> notes = notesEtudiants(scan);
			scan.close();
			//afficheNotes(System.out, notes);
			output = new File("output/grades.txt");
			out = new PrintStream(output);
			afficheNotes(out, notes);
			out.close();
			System.out.print("La moyenne des notes est : ");
			System.out.println(moyenne(notes.values()));
			System.out.println("Les étudiants ayant obtenu la meilleure note sont : ");
			List<String> meilleurs = lesMeilleurs(notes);
			afficheEtudiants(System.out, meilleurs);
			System.out.print("La meilleure note est : " );
			System.out.println(notes.get(meilleurs.get(0)));
			/// END SOLUTION
			/* BEGIN UNCOMMENT
			printFile(scan);
			END UNCOMMENT */
			
		} catch(FileNotFoundException e) {
			System.out.println("Problem finding the file");
			e.printStackTrace();
		}
	}
	
	/**
	 * Affiche le contenu d'un fichier
	 * @param scan un Scanner sur un fichier
	 */
	public static void printFile(Scanner scan) {
		while(scan.hasNextLine()) {
			String data = scan.nextLine();
			System.out.println(data);
		}
	}
	
	/**
	 * Place le nom en premier et en majuscule
	 * @param prenomNom une chaine où le prénom apparait en premier 
	 * @return une chaine au format "NOM prénom"
	 */
	public static String formatNOMPrenom(String prenomNom) {
		/// BEGIN SOLUTION
		String prenom;
		String nom;
		int sep = prenomNom.indexOf(" ");
		prenom = prenomNom.substring(0, sep);
		nom = prenomNom.substring(sep+1, prenomNom.length());
		return nom.toUpperCase() + " " + prenom;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}
	
	/**
	 * Renvoie la liste des étudiants à partir d'un fichier 
	 * @param scan un scanner sur un fichier où les étudians sont listés dans le désordre au format "Prénom Nom"
	 * @return la liste au format "NOM prénom" par ordre alphabétique
	 */
	public static List<String> listeEtudiants(Scanner scan) {
		/// BEGIN SOLUTION
		List<String> etudiants = new ArrayList<String>();
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			etudiants.add(formatNOMPrenom(line));
		}
		etudiants.sort(null);
		return etudiants;
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}
	
	/**
	 * Affiche la liste des étudiants ligne par ligne dans out
	 * @param out la sortie où afficher
	 * @param etudiants une liste d'étudiants
	 */
	public static void afficheEtudiants(PrintStream out, List<String> etudiants) {
		/// BEGIN SOLUTION
		for(String etudiant : etudiants) {
			out.println(etudiant);
		}
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}
	
	/// BEGIN SOLUTION
	
	/**
	 * Renvoie les différents prénoms de la liste
	 * @param etudiants une liste d'étdudiants au format NOM prenom
	 * @return un Set contenant les différents prénoms
	 */
	public static Set<String> extractPrenoms(List<String> etudiants) {
		Set<String> prenoms = new HashSet<String>();
		for(String etudiant : etudiants) {
			int sep = etudiant.lastIndexOf(" ");
			String prenom = etudiant.substring(sep+1);
			prenoms.add(prenom);
		}
		return prenoms;
	}
	
	/**
	 * Récupère les notes des étudiants dans un fichier
	 * @param scan un scanner sur un fichier où chaque ligne a le format "prénom nom note"
	 * @return une map telle que les clés soient des chaines au format "NOM prénom" et les valeurs des notes
	 */
	public static Map<String, Double> notesEtudiants(Scanner scan) {
		Map<String, Double> notes = new TreeMap<String, Double>();
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			int sep = line.lastIndexOf(" ");
			String key = formatNOMPrenom(line.substring(0, sep));
			notes.put(key, Double.parseDouble(line.substring(sep+1)));
		}
		return notes;
	}
	
	/**
	 * Affiche les notes des étudiants ligne par ligne dans out
	 * @param out la sortie où afficher
	 * @param etudiants une map où les clés sont les noms des étudiants et les valeurs les notes
	 */
	public static void afficheNotes(PrintStream out, Map<String, Double> notes) {
		/// BEGIN SOLUTION
		for(String etudiant : notes.keySet()) {
			out.println(etudiant + " " + notes.get(etudiant));
		}
		/// END SOLUTION
		/* BEGIN UNCOMMENT
		throw new UnsupportedOperationException("Not implemented yet");
		END UNCOMMENT */
	}
	
	/**
	 * Calcule la moyenne d'une collection de double
	 * @param notes la collection de valeurs
	 * @return la moyenne
	 */
	public static double moyenne(Collection<Double> notes) {
		double d = 0;
		for(Double note : notes) {
			d+=note;
		}
		return d/notes.size();
	}
	
	/**
	 * Renvoie la liste des étudiants ayant obtenu la note maximale
	 * @param notes la map associant étudiants et notes
	 * @return une liste d'étudiants au format "NOM prénom"
	 */
	public static List<String> lesMeilleurs(Map<String, Double> notes) {
		double noteMax = Collections.max(notes.values());
		List<String> etudiants = new ArrayList<String>();
		for(String etudiant : notes.keySet()) {
			if(notes.get(etudiant) == noteMax) {
				etudiants.add(etudiant);
			}
		}
		return etudiants;
	}
	
	/// END SOLUTION
	
	
	

}
