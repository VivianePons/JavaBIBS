---
title: 'Programmation Orientée Objet -- Java'
subtitle:  'Cours 1 : Intro et bases du langage'
author: Viviane Pons
date: Master BIBS Université Paris-Saclay 
---

# Introduction : la programmation objet

Quoi, comment, pourquoi

## Que signifie "Programmation Orientée Objet" ?

. . .

C'est un **paradigme de programmation** c'est à dire une façon d'approcher la programmation informatique et la résolution de problèmes 

. . .

Exemples d'autres paradigme : programmation impérative (C, Pascal, Basic, ...) , programmation fonctionnelle (Lisp, Haskell, OCaml, ...)


----

## Quand ?

Vers la fin des années 60, sur les travaux d'[Alan Kay](https://fr.wikipedia.org/wiki/Alan_Kay) et avec l'apparition du langage [Smalltalk](https://fr.wikipedia.org/wiki/Smalltalk) rendu public en 1980


![Picture of Alan Kay](media/Alan_Kay.jpg){.image height=50} ![Picture of Dan Ingalls](media/Dan_Ingalls.jpg){.image height=50} ![Picture of Adele Goldberg](media/Adele_Goldberg.jpg){.image height=50} 

3 des créateurs-trices du langage Smalltalk : Alan Kay, Dan Ingalls et Adele Goldberg

:::::{.small}
*[Alan Kay receiving the Kyoto Prize](https://commons.wikimedia.org/wiki/File:Alan_Kay_-_Receiving_the_Kyoto_Prize.jpg) by Ryan Johnson under [CC-BY-SA-4.0](https://creativecommons.org/licenses/by-sa/4.0/deed.en) -- [Dan Ingalls](https://commons.wikimedia.org/wiki/File:Dan_Ingalls.jpg) under [CC-BY-SA-3.0](https://creativecommons.org/licenses/by-sa/3.0/deed.en) -- [Adele Goldberg at PyCon 2007](https://commons.wikimedia.org/wiki/File:Adele_Goldberg_at_PyCon_2007.jpg) by Terry Hancock under [CC-BY-SA-2.5](https://creativecommons.org/licenses/by-sa/2.5/deed.en)*
:::::

----

## Pourquoi ?

* Organisation de l'information
* Une conception proche de l'humain (plus haut niveau)
* **encapsulation** : séparée la structure interne de la donnée et sa manipulation externe

----

## Exemple

Problème : manipuler des nombres rationnels

    ?? add_rat(int n1, int d1, int n2, int d2) {
        int n = n1 * d2 + n2 * d1
        int d = d1 * d2
            
        return ??
    }
    
Quel type de valeur renvoyer ? Comment s'assurer de la cohérence du programme ? Où faire la simplification ?

----

## Exemple

Première idée : utilisée un type structuré.

    rat add_rat(rat r1, rat r2) {
    	rat r;
        r.n = r1.n * r2.d + r2.n * r1.d
        r.d = r1.d * r2.d; 
            
        return r;
    }

Mais ça ne résout pas tous les problèmes !

----

La solution objet

[le fichier code ici](code/Rational.java)

![](media/rational.png){height=50%}

----

## Quels Langages ?

Smalltalk (1980), Common List (1984), C++ (1985), Object Pascal (1986), Python (1991),  PHP (1994), **Java (1995)**, JavaScript (1996), C# (2001), ...

# Java

## Petit historique

:::::::::::::: {.columns}
::: {.column width="30%"}
![Logo Java](media/java.png){width=50%} \
:::
::: {.column width="70%"}
* Développé en 1995 par Sun pour répondre à des questions de **portabilité** et **sécurité** puis racheté par Oracle (2009) 
* Langage de programmation libre depuis 2006
* Syntaxe inspirée du C
* Un des langages les plus utilisés d'après le [classement RedMonk](https://redmonk.com/sogrady/2022/10/20/language-rankings-6-22/)
:::
::::::::::::::

----

## Comment ça marche ?

Rappel : un processeur ne sait exécuter que des **fichiers binaires** qui correspondent à des instructions machines

**Comment passer d'un programme à des instructions machines ?**

. . .

* En compilant ! (Cobol, Fortran, C, C++, Pascal, OCaml)
* En interprétant ! (Langages de scripts tels que : bash, pearl, python, PHP)

. . .

**Et Java alors ?**

----

## Java est un langage intermédiaire : 

* le code (écrit dans un fichier .java) est d'abord **compilé** par le compilateur *javac* et transformé en un fichier binaire **Byte Code** (fichier .class) qui n'est cependant PAS un exécutable. 
* Ensuite, la **machine Java** va **interpréter** le byte code et exécuter le programme

| Code Source     |                |
|-----------------|----------------|
| fichier.java    | $\rightarrow$  |
|-----------------|



