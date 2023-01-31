---
title: 'TP2 POO: premiers objets et Collections'
---

<section class="content">
## Exercice 1 : Une liste de chaîne de caractères

Créez un nouveau projet appelé `TP2_ListString` puis créez une nouvelle classe 'ListString' et copiez et complétez le code présent [ici](https://raw.githubusercontent.com/VivianePons/JavaBIBS/main/exercices/TP2_ListString/src/ListString.java).

* Pensez à regardez les [exemples du cours](https://github.com/VivianePons/JavaBIBS/tree/main/code) comme [l'exemple d'utilisation des listes](https://github.com/VivianePons/JavaBIBS/blob/main/code/ExempleCollections/src/ExempleCollections.java)
* Ainsi que [la Javadoc pour la class String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html)

::::: {.explication}
### Exception Java

Lors de l'exécution d'un programme Java, les erreurs rencontrées sont gérées grâce à un système spécial de classes appellées `Exception`. Nous verrons ce système plus en détail plus tard. Ici voyez simplement que je "lève une exception" car la fonction n'est pas encore implantée. 
:::::

::::: {.explication}
### char et String

`char` est le type simple Java pour les caractères. Un objet chaînes de caractères de type `String` ressemble à un tableau de `char` mais n'est cependant ni un tableau, ni une collection. On peut parcourir les caractères de la chaîne grâce à une boucle for en utilisant :

* la méthode `length()` de la chaîne de caractère
* la méthode `charAt(i)` pour récupérer un caractère particulier.
:::::

::::: {.explication}
### Chacracter et char : les wrappers

Comme on l'a vu en cours, tous les types simples (`int`, `char`, `double`, etc.) ont une classe correspondante qu'on appelle "Wrapper" (to wrap = englober, car un objet Wrapper englobe son type simple). Ces classes contiennent aussi de **nombreuses méthodes statiques** sur les types simples **très utiles**. Par exemple, dans cet exercice, vous voulez utiliser la méthode `isLetter` à votre disposition dans la [classe Character](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Character.html), le wrapper de `char`.

:::::

## Exercice 2 : 

1. Créez un nouveau projet appelé `TP2_Fichiers` puis créez une nouvelle classe `Fichiers` et copiez et complétez le code présent [ici](https://raw.githubusercontent.com/VivianePons/JavaBIBS/main/exercices/TP2_Fichiers/src/Fichiers.java).
2. Avec Eclipse, faites bouton droit sur votre projet puis "New >> Folder" et créez un répertoire appelé `resources`. Dans ce repertoire, faites "New >> File" et créez un fichier `names.txt` et copiez le contenu présent [ici](https://raw.githubusercontent.com/VivianePons/JavaBIBS/main/exercices/TP2_Fichiers/resources/names.txt).
3. Vérifiez que que vous arrivez à exécuter la classe principale et que le contenu du fichier s'affiche sur la sortie standard.

Le but de l'exercice est de traiter un fichier contenant une liste d'étudiants. Le fichier contenant la liste est celui que vous avez créé dans le dossier `resources`. Regardez en détail le code des méthodes `main` et `printFile` et lisez les explications ci-dessous

::::: {.explication}
### Lecture de fichier

L'API Java offre différentes façons de manipuler des fichiers extérieurs. Nous voyons ici la méthode la plus basique. Les classes qui servent à ménipuler les fichiers sont dans le package `java.io` qui s'occupe de la gestion des entrées (input) et sorties (output). 

Quelque soit le langage, la lecture d'un fichier se déroule plus ou moins de la même façon : un fichier est un *stream* (courant) dont on *tire* les informations une par une. Imaginez un petit tube qui va de votre fichier à votre programme et où vous pouvez aspirer les mots les uns après les autres.

En Java, on crée d'abord un objet de type `File` (avec le chemin d'accès au fichier) puis on se sert de cet objet pour créer un objet de type`Scanner`. Le `Scanner` que l'on récupère fonctionne de la même façon que les scanners que nous avions créés à partir de `System.in`.  Ici, dans `printFile` on utilise les méthodes `hasNextLine` et `nextLine` pour parcourir le fichier ligne à ligne. Regardez la [documentation de la classe `Scanner`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Scanner.html) pour voir les différentes méthodes disponibles.
:::::

:::: {.explication}
### try ... catch et `FileNotFoundException`

Voilà une nouvelle utilisation des Exceptions Java ! Si on s'est trompé dans le chemin d'accès du fichier ou si le fichier qu'on souhaite lire n'est pas accessible, on va rencontrer une erreur au moment de l'exécution et une exception de type `FileNotFoundException` sera *levée*. Java oblige le ou la programmeuse à *gérer les exceptions*. Nous verrons cela en détail un peu plus tard. Pour l'instant, il suffit de comprendre le fonctionnement suivant :

~~~~{ .java}
try {
    // code exécuté normalement
} catch(FileNotFoundException e) {
	// code exécuté au moment où une erreur de type `FileNotFoundException` est rencontrée
	// s'il n'y a pas d'erreur, ce code n'est pas exécuté
}
~~~~

::::

Le fichier `names.txt` contient la liste des étudiants avec sur chaque ligne le prénom suivi du nom. Parfois le nom est en majuscule mais pas toujours et l'ordre est aléatoire. On souhaite créer un fichier tel que le nom soit écrit **en premier en majuscule** suivi du prénom et où les étudiants sont par ordre alphabétique des noms.

### Création de la liste des étudiants

1. Complétez la méthode `formatNOMPrenom` en lisant la documentation fournie. Cette méthode s'occupe simplement de manipuler une chaîne de caractère, on n'utilise pas le fichier. Vous pouvez tester votre fonction dans le main avec la ligne suivante :

        System.out.println(formatNOMPrenom("Godefroy de Montmirail"));
   
   qui doit vous afficher "DE MONTMIRAIL Godefroy". Utiliser la [documentation de la classe `String`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) pour trouver les méthodes dont vous avez besoin, en particulier `indexOf` et `substring`.
   
2. Complétez la méthode `listeEtudiants` en vous inspirant de `printFile` sauf qu'ici, vous devez créez un objet de type `List` qui contient la liste des étudiant **au bon format** (en utilisant la méthode précédente). La liste doit être **triée par ordre alphabétique**, pour cela vous pouvez utiliser [la méthode `sort`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html#sort(java.util.Comparator)) de l'interface `List`. Lisez l'explication ci-dessous.

 
:::: {.explication}
### Méthode `sort` et interface `Comparable`

La documentation de la méthode `sort` est difficile à comprendre car elle prend en paramètre un objet de type `Comparator`. Cependant, il est possible de laisser ce paramètre à `null` : dans ce cas, la méthode utilisera la comparaison implantée pour les objets de la liste. La classe `String` implémente l'interface `Comparable` : cela signifie qu'il y a bien une comparaison par défaut qui est l'ordre lexicographique, justement celui dont on a besoin ici. Pour obtenir la liste triée, il suffit donc d'exécuter cette ligne après avoir ajouté tous les éléments dans la liste (ici appelée `etudiants`)

    etudiants.sort(null);
::::

3. Vérfiez que votre méthode fonctionne en appelant 

        List<String> etudiants = listeEtudiants(scan);
		System.out.println(etudiants);

   depuis la fonction `main`.
   
### Création d'un nouveau fichier

1. Complétez la méthode `afficheEtudiants` : cette méthode prend en paramètre un objet de type `PrintStream`, c'est un *flux de sortie*. Par exemple `System.out` est un objet de type `PrintStream` (c'est la sortie standard). Vous pouvez regarder [la documentation de `PrintStream`](https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/io/PrintStream.html) pour voir les méthodes disponibles tel que `print` ou `println`. 

   Une fois la méthode écrite, l'appel suivant doit afficher la liste des étudiants (ligne par ligne) sur la sortie standard.
   
        afficheEtudiants(System.out, etudiants);

2. Avec Eclipse, créez un dossier `output` dans votre projet, puis rajouter les lignes suivantes dans la fonction `main`

        File output = new File("output/names.txt");
		PrintStream out = new PrintStream(output);
		afficheEtudiants(out, etudiants);
		out.close();

   L'exécution du projet doit maintenant créer un nouveau fichier dans le dossier `output` avec la liste des étudiants. (Pour actualiser l'affichage d'Eclipse, sélectionner le dossier et appuyez sur F5).
   
### Aller plus loin

1. Utilisez [l'interface `Set`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Set.html) qui représente un ensemble où chaque élément apparaît une unique fois et son [implémentation `HashSet`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/HashSet.html) pour compter le nombre de prénoms distincs dans la liste.

   Vous pouvez rajouter des méthodes supplémentaire, par exemple une méthode `extractPrenoms` qui prend en paramètre la liste des étudiants et renvoie le `Set` des prénoms.
   
2. Ajoutez un fichier `grades.txt` dans le dossier `resources` en copiant le contenu présent [ici](https://raw.githubusercontent.com/VivianePons/JavaBIBS/main/exercices/TP2_Fichiers/resources/grades.txt). Cette fois le fichier contient une note en plus du nom. Effectuez le même traitement que précédemment (création d'un nouveau fichier `output/grades.txt` au format "NOM prénom note"). Vous pouvez utilisez [l'interface `Map`](https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/util/Map.html) et son [implantation `TreeMap`](https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/util/TreeMap.html). Ici, les "clés" seront les chaînes "NOM prénom" et les valeurs les notes en type `Double`. L'utilisation de `TreeMap` permet que les clés soient triées au fur et à mesure qu'elles sont ajoutées.

3. Calculez la moyenne des notes ainsi que la liste des étudiant et étudiantes ayant obtenu la meilleure note. Pour calculer la note maximale, vous pouvez utiliser la méthode statique `Collections.max` disponible dans [la bibliothèque `Collections`](https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/util/Collections.html) avec de nombreuses autres méthodes.

</section>
