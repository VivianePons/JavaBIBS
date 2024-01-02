import java.util.Scanner;

public class Fonctions {

    /**
     * Affiche bonjour
     * @param name le nom à afficher
     */
    public static void sayHello(String name) {
        System.out.println("Bonjour " + name);
    }

    /**
     * Teste si une personne est majeure
     * @param age l'age de la personne
     * @return true si la personne est majeure, false sinon
     */
    public static boolean isLegalAge(int age) {
        return age >= 18;
    }

    // Ajoutez ici vos propres méthodes


    public static void main(String[] args) {
        // Lisez et exécutez le code qui suit qui vous
        // montre un exemple d'utilisation des deux méthodes
        // écrites ci-dessus
        // Lisez ensuite l'explication "Méthode de classe" de la feuille de TP
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quel est votre nom ?");
        String name = scanner.next();

        sayHello(name);

        System.out.println("Quel est votre age ?");
        int age = scanner.nextInt();

        System.out.println("Majorité : " + isLegalAge(age));

        // Lisez l'explciation "Type de retour vide et affichage"
        // En vous inspirant de l'exemple "renvoieChaine" ainsi que
        // de la méthode sayHello,  écrivez une méthode
        // helloString qui envoie la chaine de caractères à afficher
        // pour que le code ci-dessous fonctionne

        // (supprimer les /* et */ pour supprimer le commentaire)
        /*
        System.out.println(helloString(name));
        */

        // Ajouter les nécessaires pour que le code ci-dessous fonctionne
        // et affiche la réduction (comme dans l'exercice précédent

        /*
        if (hasReduction(age)) {
            System.out.println("Réduction : " + reduction(age));
        } else {
            System.out.println("Pas de réduction !");
        }
        */

    }

}