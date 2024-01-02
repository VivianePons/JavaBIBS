---
title: 'TP1 POO: premiers programmes en Java'
---

<section class="content">
## Exercice 1 : les variables

1. Dans IntelliJ, créez un nouveau Projet appelé "TP1". Puis faites bouton droit sur le répertoire "src" à gauche puis cliquez sur "New >> Class". Créez une classe du nom de "Variables" (attention à la majuscule et au s)
2. Copiez le code présent [ici](https://raw.githubusercontent.com/VivianePons/JavaBIBS/main/exercices/TP1/src/Variables.java) dans votre classe `Variables` : c'est un code java très simple avec des commentaires. Suivez les instructions et faites l'exercice. Vous trouverez ci-dessous des petits blocs d'explications à lire au fur et à mesure.

::::: {.explication}
### Variables de type simples

En Java, les variables doivent être déclarées. Il existe des types *simples* ou *pimitifs*  inspirés du C. Nous verrons plus tard les variables de type *objet*. Les types simples les plus courants sont : `int` (valeurs entières), `double` (valeurs flottantes = nombres à virgule), `float` (pareil avec moins de précision), `boolean` (booléens True / False).

A noter : les types simples sont représentés sur un **nombre de bits fixés**. Les différents types permettent une plus large gamme de valeurs (`byte`, `short` `int`, `long` pour les entiers, `float` et `double` pour les flotants).
:::::

::::: {.explication}
### Concaténation et transformation en chaîne de caractères

Nous verrons plus tard en détail le type `String` représentant les chaînes de caractères. Mais en testant les affichages, nous pouvons déjà remarquer que le symbole "+" sert à concaténer les chaînes et que Java transforme automatiquement les entiers en chaîne de caractères lorsque c'est nécessaire. 

Dans la ligne

~~~~{ .java}
System.out.println("a + b =" + a + b);
~~~~

les variables `a` et `b` sont ajoutées une à une à la chaîne de départ et donc transformées en chaînes puis concaténées, d'où le résultat ("32" = "3" + "2"). 

:::::

::::: {.explication}
### Types entier

Si l'on divise deux variables de type entier, le résultat reste un type entier : Java (tout comme C) ne fait pas conversion automatique et effectue donc une division entière. Pour effectuer une division "à virgule", il faut qu'au moins une des deux variables soit de type flottant,  par exemple

~~~~{ .java}
z = (double) a / b;
System.out.println(a + " / " + b  + " = " + z);
~~~~

:::::

## Exercice 2 : Conditionnelles

Créez une nouvelle classe appelée "Conditionnelles" dans le projet TP1 et copiez le code présent [ici](https://raw.githubusercontent.com/VivianePons/JavaBIBS/main/exercices/TP1/src/Conditionnelles.java). Suivez ensuite les instructions écrites en commentaire.

::::: {.explication}
### Tests et connecteurs logiques

Les conditionnelles (if ... else) permettent d'effectuer des actions différentes en fonction de l'évaluation d'une expression booléenne. On retrouve la syntaxe du C pour les expressions booléennes.

Comparaisons de valeurs :

* `==` égalité
* `!=` différent
* `<` inférieur sctrict, `>` supérieur strict
* `<=` inférieur ou égal, `>=` supérieur ou égal

Opérarateurs logiques :

* `expression || expression` : ou logique
* `expression && expression` : et logique

:::::

## Exercice 3 : Fonctions

Créez une nouvelle classe appelée "Fonctions" dans le projet TP1 et copiez le code présent [ici](https://raw.githubusercontent.com/VivianePons/JavaBIBS/main/exercices/TP1/src/Fonctions.java). Lisez les explications dans la méthode `main` et suivez les instructions.


::::: {.explication}
### Méthodes de classe

Ce qu'on appelle "fonction" en Java est en réalité une **méthode de classe**. Nous verrons cette notion plus en détail plus tard. Pour l'instant, nous écrirons toutes nos fonctions à l'intérieur de notre unique classe en suivant le modèle suivant

~~~~{ .java}
public static TYPE NomFonction(TYPE arg1, TYPE arg2,...) {
	...
}
~~~~

Nous expliquerons plus tard la signification des mots clés `public static`. 

* le type situé avant le nom de la fonction est le **type de retour**, c'est-à-dire le type (`int`, `double`, `String`, etc.) de l'élément renvoyé par la fonction avec `return`.
* les types et noms entre parenthèse sont les **arguments de la fonction**

:::::

::::: {.explication}
### Type de retour vide et affichage

Certaines fonctions n'ont pas de valeur de retour, c'est le cas de `main` : dans ce cas, le type est `void`.

**Attention** effectuer un affichage (avec `System.out.println`) n'est PAS la même chose que de renvoyer une valeur. Ainsi la fonction `sayHello` donnée en exemple est de type `void` . Ci-dessous, un exemple d'une fonction qui renvoie au contraire une chaîne de caractères.

~~~~{ .java}
public static String renvoieChaine() {
	return "Ma nouvelle chaine"
}
~~~~
:::::

## Exercice 4 : Boucles while

Créez une nouvelle classe appelée "BouclesWhile" dans le projet TP1 et copiez le code présent [ici](https://raw.githubusercontent.com/VivianePons/JavaBIBS/main/exercices/TP1/src/BouclesWhile.java). Lisez les explications dans la méthode `main` et suivez les instructions.


::::: {.explication}
### While et do while
Java (tout comme C mais pas comme python) accepte deux structures de boucle while.

Les instructions d'un bloc `do...while` (faire ... tant que) sont exécutées au moins une fois. Si la condition finale est vraie, on recommence !

~~~~{ .java}
do {
    instructionsr
} while(condition);
~~~~

Au contraire, la condition d'un bloc `while` (tant que) est testée dès le départ. Si c'est vrai, les instructions sont exécutées, puis on remonte au début et on teste de nouveau la condition et on continue tant que c'est vrai.

~~~~{ .java}
while(condition) {
    instructions
}
~~~~
:::::

## Exercice 5 : Boucles for

Créez une nouvelle classe appelée "BouclesFor" dans le projet TP1 et copiez le code présent [ici](https://raw.githubusercontent.com/VivianePons/JavaBIBS/main/exercices/TP1/src/BouclesFor.java). Lisez les explications dans la méthode `main` et suivez les instructions.

::::: {.explication}
### Boucle for

La syntaxe de la boucle for est la même que celle du C

~~~~{ .java}
for(initialisation; test; incrementation) {
	instructions
}
~~~~

**L'initialisation** est réalisée **une fois** en début de boucle. Il est possible d'y déclarer une variable qui ne pourra alors être utilisée **que dans la boucle** `for(int i = 0`. On peut aussi utiliser une variable déclarée auparavant ou même laisser l'initialisation vide (en laissant juste le point virgule).

Les **instructions** à l'intérieur du bloc ne sont exécutées que si la **condition** est vraie après l'initialisation.

A la fin du bloc d'instruction, on effectue **l'incrémentation** puis on teste de nouveau la **condition**. Si elle est vraie, on exécute de nouveau le bloc, sinon la boucle s'arrête.
::::: 


</section>



