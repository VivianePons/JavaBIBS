
public class BouclesFor {

    /**
     * Construit une ligne contenant des etoiles séparées par des espaces
     * @param taille le nombre d'étoiles
     * @return une chaine de caractères "* * * ... *" terminée par un retour à la ligne
     */
    public static String ligneEtoiles(int taille) {
        String s = "";
        for(int i = 0; i < taille; i++) {
            s += "* ";
        }
        s += "\n";
        return s;
    }

    /**
     * Construit une chaine caractère représentant un rectangle plein en *
     * @param largeur le nombre d'étoiles par ligne
     * @param hauteur le nombre d'étoiles par colonnes
     * @return la chaine de caractères du rectangle
     */
    public static String rectanglePlein(int largeur, int hauteur) {
        String s = "";
        return s;
    }

    /**
     * Construit une chaine caractère représentant un rectangle vide plein en *
     * @param largeur le nombre d'étoiles sur la première et la dernière ligne
     * @param hauteur le nombre d'étoiles sur la première et la dernière colonne
     * @return la chaine de caractères du rectangle
     */
    public static String rectangleVide(int largeur, int hauteur) {
        String s = "";
        return s;
    }

    /**
     * Construit une chaine caractère représentant un triangle en étoile de ce type là : (taille 3)
     *
     * *
     * * *
     * * * *
     * @param taille le nombre d'étoiles sur la première colonne et la dernière ligne
     * @return la chaine de caractères du triangle
     */
    public static String triangleIsoceleRectangle(int taille) {
        String s = "";
        return s;
    }

    /**
     * Construit une chaine caractère représentant un triangle en étoile de ce type là : (taille 3)
     *
     *       *
     *     * * *
     *   * * * * *
     * * * * * * * *
     * @param taille la taille telle qu'il y ait 2*taille+1 étoiles sur la dernière ligne
     * @return la chaine de caractères du triangle
     */
    public static String triangleIsoceleMilieu(int taille) {
        String s = "";
        return s;
    }

    /**
     * Construit une chaine caractère représentant un triangle rectangle de hauteur et largeur donnée
     * par exemple pour largeur 3 et hauteur 4
     * *
     * *
     * * *
     * * * *
     * @param largeur le nombre d'étoiles sur la première colonne
     * @param hauteur le nombre d'étoiles sur la dernière ligne
     * @return la chaine de caractères du triangle
     */
    public static String triangleRectangle(int largeur, int hauteur) {
        String s = "";
        return s;
    }

    /**
     * Construit une chaine caractère représentant un disque en étoiles
     * @param rayon le rayon du disque (il y a 2* rayon + 1 étoiles sur le diamètre)
     * @return la chaine de caractères du disque
     */
    public static String disque(int rayon) {
        String s = "";
        return s;
    }

    public static void main(String args[]) {
        // Voici un exemple très simple de boucle for

        for(int i =0; i < 10; i++) {
            System.out.println(i);
        }

        // Lisez l'explication sur la boucle for dans la feuille de TP
        // En vous inspirant de l'exemple de la fonction "ligneEtoiles"
        // (et en l'utilisant si vous voulez), complétez les autres
        // méthodes ci-dessus

        System.out.println(ligneEtoiles(10));
        System.out.println();
        System.out.println(rectanglePlein(5,6));
        System.out.println();
        System.out.println(rectangleVide(5,6));
        System.out.println();
        System.out.println(triangleIsoceleRectangle(5));
        System.out.println();
        System.out.println(triangleIsoceleMilieu(5));
        System.out.println();
        System.out.println(triangleRectangle(5, 7));
        System.out.println();
        System.out.println(disque(10));
    }
}