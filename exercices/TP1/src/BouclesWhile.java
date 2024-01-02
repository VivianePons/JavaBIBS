import java.util.Scanner;

public class BouclesWhile {

    /**
     * Lit une réponse oui / non
     * @param scanner, le scanner qui doit lire la réponse
     * @return True si la prochaine chaine de scanner est "oui"
     */
    public static boolean readOui(Scanner scanner) {
        return scanner.next().equals("oui");
    }

    public static void main(String[] args) {
        // Regardez les deux exemples suivant, exécutez en testant
        // plusieurs réponses aux question

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Voulez vous continuer ?");

        } while(readOui(scanner));

        System.out.println("On recommence ?");

        while(readOui(scanner)) {
            System.out.println("Voulez vous continuer ?");
        }

        // Lisez l'explication "While et Do While" sur la feuille de TP

        // Décommentez le programme ci-dessous puis utilisez une boucle
        // while (ou do while) pour que le programme devine le nombre
        // auquel vous pensez

        /*
        System.out.println("Pensez à un nombre entre 1 et 100");
        */


    }

}