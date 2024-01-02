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

        /// BEGIN COMMENTEE
        System.out.println("Pensez à un nombre entre 1 et 100");
        /// END COMMENTEE

        /// BEGIN SOLUTION
        int min = 1;
        int max = 100;
        while(min != max) {
            int m = (min + max) / 2;
            System.out.println("Votre nombre est-il inférieur ou égal à " + m + " ?");
            if (readOui(scanner)) {
                max = m;
            } else {
                min = m+1;
            }
        }
        System.out.println("Votre nombre est : " + min);
        /// END SOLUTION

    }

}