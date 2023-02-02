---
title: 'TP3 POO: Création de classes'
---

<section class="content">
## Exercice 1 : La classe Student

Le but de l'exercice est de reproduire ce que nous avions fait au TP précédent avec les listes d'étudiants, mais cette fois en utilisant une structure de classe. 

### Création du package et de la classe

1. Créez un nouveau projet Java intitulé "BaseEtudiants"
2. Dans ce projet, créez les classes (avec les bons packages)

        fr.upsaclay.bibs.students.Student
        fr.upsaclay.bibs.BaseEtudiants
    
   La classe `BaseEtudiants` contiendra la fonction `main`
3. Dans la classe `Student`, créez deux champs `firstName` et `lastName` pour stocker le prénom et le nom de l'étudiant. Les champs doivent être publics et finaux.
4. Créez un constructeur qui prend en paramètre un prénom et un nom et initialise les champs de telle sorte à ce que
   - les espaces de début et de fin soient supprimés (méthode `strip` des objets de type `String`)
   - le nom de famille `lastName` soit enregistré en majuscule
5. Créez une fonction `main` dans la classe `BaseEtudiants` et créez un objet de type étudiant. Vérifiez que vous arrivez bien à exécuter

### Premiers tests

6. Créez un package 

        fr.upsaclay.bibs.students.test

   Dans ce package, créez une une unité de test JUnit avec "New >> JUnit test Case" intitulée `StudentTest` pour tester la classe `Student`
7. Supprimez la méthode `test` ajoutée automatiquement et ajoutez une méthde `testCreation` contenant le test suivant

        assertEquals(new Student("Ada","Lovelace").firstName, "Ada");
  
   Puis lancez l'exécution de l'unité de test
8. Complétez votre méthode et votre classe avec le contenu présent [ici](https://raw.githubusercontent.com/VivianePons/JavaBIBS/main/exercices/TP3_BaseEtudiants/src/fr/upsaclay/bibs/students/test/StudentTest.java). Décommentez les tests de la méthode `testCreation` et vérifiez que cela passe bien.

### `Equals`, `toString` et `hashCode`

9. On considère (sans doute à tort) que deux étudiants sont "égaux" s'ils ont les mêmes noms et prénoms. Ajoutez et complétez les 3 méthodes suivantes. La méhode `toString` doit renvoyer le nom (en majuscule) suivi du prénom séparé par un espace.

~~~~{.java}
	@Override
	public String toString() {

	}
	
	@Override
	public boolean equals(Object obj) {

	}
	
	@Override
	public int hashCode() {

	}
~~~~

10. Vérfier la méthode `toString` en faisant un affichage dans la fonction `main`
11. Décommentez les tests correspondants aux 3 méthodes et lancez les.

### Test `validName`

12. Implantez la méthode suivante dans la classe `Student`. Puis décommentez les tests correspondants dans `StudentTest` et lancez-les.

~~~~{.java}
	/**
	 * Checks that a given String is a valid name
	 * ie it contains only: letters, spaces, or "-" 
	 * @param name a String representing a name 
	 * @return true if it's a valid name, false otherwise
	 */
	public static boolean validName(String name)
~~~~

13. Dans le constructeur de la classe `Student`, rajoutez un test pour la validité du prénom et du nom. En cas de nom invalide, vous léverez une exception avec la ligne suivante :

        throw new IllegalArgumentException("Not a valid name");
   Décommentez et lancez les tests de la méthode `testExceptionCreation` dans `StudentTest`
   
### Nouveau champ : getter et setter

14. Créez un nouveau champ de type `String`: `email` avec visibilité `private`
15. Créez deux méthodes `public void setEmail(String email)` et `public String getEmail()` qui permette de donner une valeur / récupérer la valeur du champ `email`.

::::: {.explication}
Il est très courant d'utiliser la visibilité `private` pour des champs et d'en controler l'accès par des méthodes appellées "getter" et "setter". D'ailleurs, lorsqu'on crée le champs, Eclipse affiche un petit "warning" à gauche et on peut créer ces méthodes automatiquement.
:::::

16. Ajoutez un constructeur `public Student(String firstName, String lastName, String email)` qui fait appel au constructeur principal pour le prénom / nom puis initialise l'email.
17. Décommentez et lancez les tests de la méthode `testEmail` dans `StudentTest`
18. On peut tester la validité d'un email grâce à une "expression régulière" en utilisant la classe `java.util.regex.Pattern`. Ajoutez la méthode suivante à votre classe `Student` (il faut aussi importer la classe `Pattern`). (C'est une vérification très basique qui cherche simplement le caractère "@")

~~~~{.java}
	/**
	 * Checks that a given String is a valid email using regular expression
	 * @param email a String representing an email 
	 * @return true if it's a valid email, false otherwise
	 */
	public static boolean validEmail(String email) {
		String regexPattern = "^(.+)@(\\S+)$";
	    return Pattern.compile(regexPattern)
	      .matcher(email)
	      .matches();
	}
~~~~
19. Utilisez cette méthode pour vérifier la validité d'un email avant de mettre à jour le champ. Si l'email est invalid, on lèvera une exception :

        throw new IllegalArgumentException("Not a valid email");

  Décommentez et lancez les tests des méthodes `testValidEmail` et `testExceptionEmail`
  
### Implémenter `Comparable`

20. Changez la déclaration de la classe `Student` pour qu'elle implémente l'interface `Comparable` :

        public class Student implements Comparable<Student>

21. Eclispe affiche maintenant une erreur : il faut ajouter la [méthode `compareTo(Student st)`](https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/lang/Comparable.html#compareTo(T)) : implémentez la méthode en utilisant la méthode `compareTo` des chaînes de caracères.
22. Décommentez et lancez les tests de la méthode `testCompareTo`

### Une variable statique

Comme nous avons codé l'égalité avec la comparaison des noms, il se peut que nous ayons des instances avec des informations différentes (l'email) mais considérées comme égales. On voudrait savoir laquelle est la plus récente. Pour cela, on va rajouter un champ `instanceId` : chaque instance créée reçoit un nouvel id. Plus l'id est grand, plus l'instance est récente.

22. Créer deux champs dans la classe `Student`

        private int instanceId;
        private static int count = 0;

23. A chaque création, augmenter la variable statique `count` et utiliser la valeur courante comme id du nouvel objet. Le champ `count` est une variable statique qui compte le nombre d'instances crées.
24. Ajoutez une méthode `getInstanceId` puis décommentez et lancez les tests de la méthode `testInstanceId`.

## Exercice 2 : la classe `Grade`

25. Dans le package `fr.upsaclay.bibs.students`, créez une nouvelle interface `Gradable` ainsi qu'une classe `Grade` et copiez les codes [de l'interface ici](https://raw.githubusercontent.com/VivianePons/JavaBIBS/main/exercices/TP3_BaseEtudiants/src/fr/upsaclay/bibs/students/Gradable.java) et [de la classe ici](https://raw.githubusercontent.com/VivianePons/JavaBIBS/main/exercices/TP3_BaseEtudiants/src/fr/upsaclay/bibs/students/Grade.java)
26. Dans le package `fr.upsaclay.bibs.students.test` créez une nouvelle unité JUnit et copiez le [code présent ici](https://raw.githubusercontent.com/VivianePons/JavaBIBS/main/exercices/TP3_BaseEtudiants/src/fr/upsaclay/bibs/students/test/GradeTest.java)
27. La classe `Grade` représente une note que l'on peut calculer sur la valeur souhaitée et qui peut aussi stocker un coefficient

   - la représentation en chaine écrit la note sur la valeur maximale par défaut "10/20"
   - le test d'égalité s'effectue sur la valeur de la note tel que 10/20 soit égal à 50/100
   - le hashcode doit être cohérent par rapport à ça (on ne peut donc pas utiliser la représentation sous forme de chaîne de caractère !)
   - la classe implémente l'interface `Gradable` qui contient en particulier une méthode pour renvoyer la note avec la note maximale que l'on souhaite (note /20, /10, /100, etc)
   - l'interface `Gradable` étend l'interface `Comparable` : il faut donc une méthode de comparaison qui s'effectue sur la valeur de la note
   
   Complétez toutes les méthodes de la classe `Grade` de telle sorte que les tests définis dans `GradeTest` passent tous.

## TO BE CONTINUED
</section>

