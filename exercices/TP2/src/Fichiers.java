import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Fichiers {

    public static void main(String[] args) {
        File file = new File("resources/names.txt");
        try {
            Scanner scan = new Scanner(file);
			printFile(scan);

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
		throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Renvoie la liste des étudiants à partir d'un fichier
     * @param scan un scanner sur un fichier où les étudians sont listés dans le désordre au format "Prénom Nom"
     * @return la liste au format "NOM prénom" par ordre alphabétique
     */
    public static List<String> listeEtudiants(Scanner scan) {
		throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Affiche la liste des étudiants ligne par ligne dans out
     * @param out la sortie où afficher
     * @param etudiants une liste d'étudiants
     */
    public static void afficheEtudiants(PrintStream out, List<String> etudiants) {
		throw new UnsupportedOperationException("Not implemented yet");
    }





}