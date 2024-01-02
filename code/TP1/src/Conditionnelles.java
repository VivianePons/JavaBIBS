/**
 *   programme de demonstration du Scanner et des conditionnelles
 *   en Java avec exercices
 *
 *   @author Viviane Pons
 */

import java.util.Scanner;
public class Conditionnelles {

    public static void main(String[] args) {
        // La ligne suivante crée un objet de type Scanner qui nous
        // servira à communiquer avec l'utilisateur / utilisatrice
        // Nous reviendrons plus tard en détail sur les variables objets

        Scanner scanner = new Scanner(System.in);

        // Observez (et exécutez) le morceau de code suivant qui vous
        // montre un exemple d'utilsation du scanner et de conditionnelle

        System.out.println("Quel age avez-vous ?");
        int age = scanner.nextInt();

        if (age >= 21) {
            System.out.println("Vous êtes majeur(e) !");
        } else if (age >= 18) {
            System.out.println("Vous êtes majeur(e) en France mais pas partout aux Etats-Unis");
        } else {
            System.out.println("Vous n'êtes pas encore majeur(e)");
        }

        // lisez les explications sur les tests et connecteurs logiques dans le TP puis complétez les
        // exercices

        // On a le droit à une réduction sur des billets si on a 25 ans ou moins, ou, 60 ans et +
        // Affichez un message "Vous avez le droit à une réduction" si c'est le cas

        /// BEGIN SOLUTION
        if (age <= 25 || age >= 60) {
            System.out.println("Vous avez le droit à une réduction");
        }
        /// END SOLUTION

        // Complétez le programme pour afficher la réduction à laquelle on a le droit :
        // 4 ans et moins  gratuit
        // entre 5 et 12 ans : réduction enfant
        // entre 13 et 25 ans : réduction jeune
        // 60 ans et + : réduction sénior
        // sinon pas de réduction

        /// BEGIN SOLUTION
        if (age <= 4) {
            System.out.println("Gratuit !");
        } else if (age <= 12) {
            System.out.println("Réduction enfant");
        } else if (age <= 25) {
            System.out.println("Réduction jeune");
        } else if (age >= 60) {
            System.out.println("Réduction sénior");
        } else {
            System.out.println("Pas de réduction !");
        }
        /// END SOLUTION
    }
}
