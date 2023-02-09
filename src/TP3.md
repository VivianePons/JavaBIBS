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
   - la classe `Grade` implémente deux interfaces, l'interface `Gradable` et l'interface `Comparable` : on voud fournit la méthode `compareTo` qui utilise une méthode statique définie dans l'interface.
   
   Complétez toutes les méthodes de la classe `Grade` de telle sorte que les tests définis dans `GradeTest` passent tous.
   
:::: {.explication}
### Code dans les interfaces

Au départ, le rôle d'une interface n'est pas d'implémenter du code (ça c'est le rôle des classes). Deux exceptions cependant :

* les méthdoes statiques offrent des fonctions utilitaires sur les objets implantant l'interface
* des méthodes `default` : cela sert à définir des méthodes plus ou moins "optionnelles". On veut que toutes les notes aient un coefficient mais ça ne sera pas forcément pertinents pour toutes les classes `Gradable` donc on fournit un comportement par défaut. Cela a été introduit dans la version 8 de Java et est très utile pour la "Backward compatibility" (compatibilité des applications avec les versions antérieures) : on peut étendre une interface sans devoir réimplanter toutes les classes qui l'implémentent.

::::

## Exercice 3 : La classe `GradeList`

26. Dans le package `fr.upsaclay.bibs.etudiants`, créez une nouvelle classe `GradeList` et copiez le [code présent ici](https://raw.githubusercontent.com/VivianePons/JavaBIBS/main/exercices/TP3_BaseEtudiants/src/fr/upsaclay/bibs/students/GradeList.java). Dans le package `fr.upsaclay.bibs.etudiants.test`, créez une unité de test `GradeListTest` et copiez le [code présent ici](https://raw.githubusercontent.com/VivianePons/JavaBIBS/main/exercices/TP3_BaseEtudiants/src/fr/upsaclay/bibs/students/test/GradeListTest.java) et vérifiez que les tests passent

:::: {.explication}
### Classes génériques

Cette classe sert à stocker une liste de notes et est elle-même une implantation de `Gradable` et de `Iterable`. Surtout, c'est elle-même une classe générique : elle stocke une liste d'éléments de type "E" où E doit être une implantation de `Gradable`. Cela vous donne un exemple d’implantation générique mais on ne vous demande pas de maîtriser ce système à ce stade.
::::

27. La plupart des méthodes de `GradeList` sont déjà implantées (regardez par exemple les constructeurs et le champ privé pour stocker la liste). Il ne reste que la méthode `gradeOver` à implanter : cette méthode renvoie la moyenne des notes stockées. Ne vous laissez pas impressionner par le type générique `E`, vous avez un exemple juste au dessus de parcours de la liste avec la méthode `max`. Implantez la méthode `gradeOver` de telle sorte que les tests à décommenter passent. 

28. On va maintenant éditer la classe `Student` pour qu'on puisse stocker pour chaque étudiant une liste de note. Ajoutez un champ privé final dans la classe `Student` :
        
        private final GradeList<Gradable> gradeList = new GradeList<Gradable>();

29. Ajoutez une méthode dans la classe `Student`

        public GradeList<Gradable> getGradeList() {
            return gradeList;
	    }
         
   pour récupérer la liste de note. Vérifiez que les tests de `testGetGradeList` fonctionnent.
   
30. Modifiez votre déclaration de classe `Student` pour qu'elle implémente l'interface `Gradable` en plus de `Comparable` (on écrit les différentes interfaces implémentées en séparant par des virgules). La seule méthode à implanter est `gradeOver` qui doit renvoyer la note calculée par `gradeList` (c'est-à-dire la moyenne de l'étudiant). Vérifiez que les tests de `testDefaultCoefficient` et `testGradeOver` fonctionnent.

:::: {.explication}
### Polymorphisme

La classe `Student` est maintenant une implantation de `Gradable` : cela signifie que tout ce qui peut s'appliquer aux objets `Gradable` peut s'appliquer aux étudiants. En particulier, on peut créer une `GradeList` avec une liste d'étudiants ! C'est ce qu'on va faire juste après pour calculer la moyenne d'un groupe d'étudiants.

En fonction du type déclaré `Student` ou `Gradable` on aura accès à plus ou moins de fonctionnalités : c'est le polymorphisme.
:::: 

## Exercice 4 : une fonction `main`

28. Récupérez le nouveau fichier `grades.txt` [présent ici](https://raw.githubusercontent.com/VivianePons/JavaBIBS/main/exercices/TP3_BaseEtudiants/resources/grades.txt). Chaque ligne suit le format suivant

        NOM, Prénom, note, note, note
        
   On considère que la première note **a coefficient 2** tandis que les 2 autres notes ont chacune **coefficient 1**. 
   
   Écrivez une fonction main qui crée à partir du fichier une liste d'étudiants de type `GradeList<Student>` : chaque étudiant contiendra à son tour la liste de notes qui lui ait associée avec les bons coefficients. 
   
 29. Affichez : 
    
     - la liste des étudiants avec leur moyenne
     - la moyenne générale de la classe (la moyenne des moyennes des étudiants) (Vous devez trouver 15.079)
     - le nom et la note de l'étudiant-e ayant eu la meilleure moyenne (Vous devez trouver TSOGBEKAN Emeline avec la note 19.5)
</section>

