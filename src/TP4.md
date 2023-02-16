---
title: "TP4 POO: Héritage -- Création d'un système dynamique"
---

<section class="content">
## Objectif

L'objectif de ce TP est d'implanter un **système dynamique**, c'est-à-dire un système dont l'état évolue par étape successive. Nous allons programmer une simulation d'un champ contenant des moutons et des loups.

![Une image du système dynamique : un grand rectangle blanc avec des petits carrés vers et des petits ronds blancs et noirs](media/illustration_TP4.png){width=300} \

## Récupération du code

L'ensemble du code est disponible dans un [repo indépendant](https://gitlab.dsi.universite-paris-saclay.fr/javabibs/TP4_Heritage). Vous pouvez le récupérer en cliquant sur le bouton de téléchargement à côté de "Clone" (on en effectuant un clone avec *git* si vous savez faire). 

Ensuite, avec Eclipse, vous pouvez créer un nouveau projet en utilisant le dossier téléchargé comme source. Ainsi, l'ensmeble de l'architecture de classes et packages seront déjà créés.

## Architecture générale

L'architecture du projet est déjà fournie et de nombreuses classes et interfaces sont écrites. Votre travail sera de compléter le code fourni. 

### Organisation des packages

* le package `fr.upsaclay.bibs.fieldsystem.view` concerne la partie graphique de l'application. Nous n'avons pas encore abordé cet aspect : vous n'aurez rien à modifier.
* le package `fr.upsaclay.bibs.fieldsystem.test` contient l'ensemble des tests que les classes devront satisfaire.
* la classe `FieldSystem` dans le package `fr.upsaclay.bibs.fieldsystem` contient une fonction `main` déjà écrite (et très simple à comprendre) que vous pourrez utiliser quand vous aurez complété l'application.
* Le travail à faire réside dans le package `fr.upsaclay.bibs.fieldsystem.sheepfield` qui correspond au modèle du système dynamique. Le système lui-même est déjà écrit dans la classe `Field` : cette une grille rectangulaire où chaque case contient une liste de d'objets de type `FieldElement`, ce sont ces objets que vous allez écrire (l'herbe, les moutons et les loups)

### L'interface `FieldElement`

Cette interface vous ait donnée : elle permet de représenter tous les éléments évoluant dans le champ. Vous allez compléter les différentes classes implémentant l'interface en utilisant l'héritage et les classes abstraites. 

Il y a 3 types de `FieldElement` : l'herbe (classe `Grass`), les moutons (classe `Sheep`) et les loups (classe `Wolf`). Les classes suivent la structure d'héritage suivante :

![La classe abstraite AbstractFieldElement implémente l'interface. Grass et AbstractAnimal héritent de AbstractFieldElement. Sheep et Wolf héritent de AbstractAnimal.](media/diagrams/FieldElement.png) 

La classe `AbstractFieldElement` est déjà implantée, on reviendra sur son rôle et son fonctionnement. Vous implanterez (dans cet ordre) : la classe `Grass`, puis les classes `Sheep` et `AbstractAnimal` et enfin la classe `Wolf`. Le TP vous indiquera les étapes et vous donnera les explications nécessaires.


## Avant de commencer : quelques nouveauté

:::: {.explication}
### Les classes enum

La classe `FieldElementType` est une classe particulière de type `enum` : cela reproduit le type enum du C. Les éléments de cette classes sont des objets comme les autres mais les seules instances possibles sont celles énumérées `GRASS` `SHEEP` et `WOLF` qui sont en réalité des constantes statiques. Pour les utiliser, on écrit par exemple

    FieldElementType type = FieldElementType.SHEEP
    
La variable `type` est ensuite un objet de type `FieldElementType`. Elle possède la méthode `getLevel` qui pour le mouton renvoie 1.

Le type enum permet de facilement tester l'ensemble des posibilités avec une conditions de type `switch`. Un exemple vous est donné dans la classe `DrawPanel` du package `fr.upsaclay.bibs.fieldsystem.view` :

~~~~{.java}
switch(element.getType()) {
    case GRASS:
        paintGrass(g, i, j);
        break;
    case SHEEP:
        paintAnimal(g, i, j, Color.WHITE, element.isWeak());
        break;
    case WOLF:
        paintAnimal(g, i, j, Color.BLACK, element.isWeak());
        break;
}
~~~~
::::

:::: {.explication}
### Les classes record

Les classes record sont des classes particulières qui correspondent plus ou moins aux structures du C (sauf qu'on ne peut pas les modifier). L'objectif de ces classes est simplement de stocker un ensemble de variables dans une structure. C'est le cas ici de la classe `Position` et de la classe `FieldMove` : les paramètres déclarés du record sont stockés comme des champs privé et finaux. Le constructeur et les méthodes d'accès sont créées automatiquement. Par exemple, dans le cas de la classe `Position`, on pourra créer un objet de cette façon :

    Position pos = new Position(1,2);
    
puis accéder aux champs `x` et `y`
 
    pos.x();
    pos.y();

::::

### Du Java un peu avancé

La classe `Field` contient des éléments de Java avancés : interface fonctionnels et lambda fonctions, classes internes, implémentation d'itérateur, etc. Vous pouvez regarder son fonctionnement par curiosité et intérêt mais on ne vous demande pas de tout comprendre !

## Exercice 1 : La classe `Grass`

### La classe `AbstractFieldElement`

La classe `Grass` hérite de la classe `AbstractFieldElement` et implémente donc les méthodes déclarées dans l'interface `FieldElement`. Ces méthodes représentent les actions, évolution et état des différents éléments du champ qui vont permettre à la classe `Field` de faire évoluer le système. 

La classe `AbstractFieldElement` donne une implémentation par défaut de toutes ces méthodes ce qui va permettre à chaque élément de réécrire les méthodes qui lui sont propres. Par défaut, les éléments sont vivants, inactifs, ne mangent pas, ne se reproduisent pas et ne se déplacent pas.

Par ailleurs, la classe implémente le fonctionnement général des éléments. Son constructeur prend en paramètre une variable `FieldElementType type` qui va stocker le type d'élément (dans notre cas, chaque implémentation d’élément correspond à un type différent mais ça n'est pas forcément le cas en général).

On trouve aussi une première implémentation des méthodes `activate` et `evolve` : au moment de leur activation, les éléments reçoivent un certain `lifespan` qui diminue à chaque évolution. Lorsque le `lifespan` arrive à 0, la classe appelle la méthode `actionZeroLifeSpan` qui va dépendre de chaque élément


:::: {.explication}
### Méthodes abstraites

Les méthodes `actionZeroLifeSpan` et `getInitialLifeSpan` sont déclarées dans la classe abstraite comme `abstract` mais le code sera écrit dans les classes filles.
::::

:::: {.explication}
### Méthodes protected

La classe `AbstractFieldElement` déclare des méthodes de visibilité `protected` comme `setLifeSpan` et `increaseLifeSpan`: ces méthodes n'appartiennent pas à l'interface `FieldElement` mais peuvent être utilisées par les classes filles pour interagir avec la super classe. En effet, le champ `lifespan` étant privé, il ne pourra pas être modifié par les classes filles.
::::

### Implémentation des constructeurs 

On a implanté pour vous le constructeur suivant

    public Grass(boolean active, double grassProba)
  
Ce constructeur appelle le constructeur de la super classe en lui passant le bon type puis mets à jour les paramètres de la classe `Grass` avec les méthodes `setGrassProba` et `activate`.

1. Implantez la méthode `setGrassProba` : cette méthode doit lever une exception

        throw new IllegalArgumentException();

   si la probabilité est inférieure stricte à 0 ou supérieure stricte à 1. Ensuite, le champ `grassProba` est mis à jour avec la valeur envoyée.
   
2. Implantez la méthode `activate` : elle doit appelée la méthode de la super classe avec `super.activate()` puis passer le champ `active` à `true`.

3. Implantez la méthode `getInitialLifeSpan` qui renvoie la valeur statique par défaut `Grass.defaultLifeSpan` (ce qui signifie que si l'on modifie la valeur statique, on modifie en temps réel toutes les valeurs pour tous les objets `Grass`)

4. Ouvrez le fichier `GrassTest` et lancez les tests. Les premiers tests de `testCreation` doivent passer.

5. Implantez les autres constructeurs de la classe `Grass` : ils appellent le constructeur principal avec les bons paramètres puis effectuent les actions nécessaires :
   
   * Si `grassProba` n'est pas envoyé, on doit utiliser la valeur statique par défaut `Grass.defaultProba`
   * Si l'état de départ (`active`) n'est pas spécifié, on doit lancer la méthode `randomActivate` (déjà implantée) pour activer l'hervbe de façon aléatoire.
   
6. Lancez les tests : tous les tests de `testCreation`doivent passer.

### Autres méthodes

Le fonctionnement des éléments `Grass` est le suivant : les éléments ne meurent jamais (on garde la méthode par défaut `isDead` de `AbstractFieldElement` qui renvoie toujours `false`). L'herbe peut cependant être active ou inactive. Lorsqu'elle est activée, elle reste active un certains nombre de tour puis devient inactive. Lorsqu'elle est inactive, elle peut être activée aléatoirement à chaque tour (en utilisant `grassProba`)

7. Implantez la méthode `actionZeroLifeSpan` : cette méthode est appelée par la super classe quand le `lifespan` d'un élément actif arrive à 0. Pour l'herbe, il s'agit juste de passer le champ `active` à `false`.

8. Implantez la méthode `evolve` : cette méthode doit appelée la méthode `evolve` de la super classe puis, si l'herbe est inactive, appeler la méthode `randomActivate` pour l'activer de façon aléatoire.

9. Lancez les tests : tout doit passer sauf `testEaten`

10. La méthode `eaten` représente ce qui doit être fait quand l'herbe est "mangée" : dans ce cas l'herbe devient inactive

11. Lancez les tests : tout doit maintenant passer !

12. Lancez la fonction main de la classe `FieldSystem` : les éléments herbes sont ajoutés automatiquement à la création de champ (avec une probabilité aléatoire d'activation). Vous devriez voir aparaitre un grand rectangle avec des petits carrés verts qui apparaissent et disparaissent. C'est notre champ !

## Exercice 2 : Les moutons

On va maintenant implantez les animaux qui héritent tous de la classe `AbstractAnimal`. L'objectif est de compléter les classes `AbstractAnimal` et `Sheep` de telle sorte à ce que tous les tests de `SheepTest` passent.

### Fonctionnement des animaux

Contrairement aux éléments `Grass`, les animaux peuvent être vivants ou morts (les animaux morts sont en fait retirés du champ, alors que les éléments inactifs comme l'herbe restent présents mais invisibles). La classe `AbstractAnimal` stocke donc un champ `dead` : les animaux sont considérés comme actif s'ils ne sont pas morts.

Par ailleurs, ils ont des fonctionnalités en plus comme le fait de pourvoir manger, se reproduire et se déplacer. La plupart des actions peuvent être traitées de façon uniforme pour tous les animaux et sont donc implémentées dans `AbstractAnimal` avec parfois l'utilisation de certaines méthodes abstraites qui correspondent au comportement spécifique de chaque type d'animal.

### Constructeurs

13. Le constructeur de la classe `AbstractAnimal` est déjà implanté. Implantez le constructeur de la classe `Sheep` : il y a seulement à appeler le super constructeur avec le bon type.

14. Implantez les méthodes `isActive` et `isDead` de `AbstractAnimal` (en utilisant la valeur de `dead`)

15. Vérifiez les que les tests de `testCreate` dans `SheepTest` fonctionnent

### État

16.  Implantez la méthode `getWeakLevel` dans `Sheep` (elle renvoie la valeur statique par défaut de la classe `Sheep`). Vérifiez que les tests de `testGetWeakLevel` fonctionnent.

17. Implantez la méthode `isWeak` de abstract animal qui renvoie true si le `lifespan` de l'animal est inférieur ou égal à son `weakLevel`. Vérifiez que les tests de `testIsWeak` passent.

### Évolution

L'évolution des animaux est gérée par `AbstractElement`. La seule chose à implémenter est `actionZeroLifeSpan` : les animaux meurent (quand `lifespan` tombe à 0)

18. Implantez `actionZeroLifeSpan` dans `AbstractAnimal` et vérifiez que les tests `testEvolveDead` et `testEvolveWeak` passent bien.


### Nourriture

Les animaux peuvent manger mais seulement s'ils sont vivants et si le type de l'élément qu'ils souhaitent manger appartient au niveau juste en dessous du leur (le niveau est donné par le `getLevel` du type). Les moutons peuvent manger l'herbe pas ne peuvent pas manger les loups. Les loups peuvent manger les moutons mais pas l'herbe. 

Lorsqu'ils mangent, les animaux augmentent leur `lifespan` de la valeur donnée par `getIncreasePerEat`. 

Lorsqu'ils sont mangés, les animaux meurent.

Ces actions sont gérées par trois méthodes `canEat` `eat` et `eaten` et sont implantées en partie dans `AbstractFieldElement`, `AbstractAnimal` et enfin `Sheep` qui n'implémente que `getIncreasePerEat`. 

19. Implantez `getIncreasePerEat` dans `Sheep` ainsi que les méthodes nécessaires dans `AbstractAnimal` pour que les tests `testCanEat` `testEat` et `testEaten` fonctionnent. Pensez à regarder ce qui est implanté dans `AbstractFieldElement` et a appelé la méthode de la super classe quand c'est nécessaire.

### Reproduction

Chaque animal peut, à chaque tour, se "reproduire", c'est à dire se dédoubler. Cette action est gérée par la méthode `conditionnalReproduce`. La méthode par défaut de `AbstractFieldElement` renvoie simplement `null` ce qui signifie qu'il n'y a pas eu de reproduction.

20. Dans `Sheep` implantez la méthode `testNewInstance` : cette méthode est définie comme abstraite dans `AbstractAnimal` , elle doit simplement renvoyer une nouvelle instance de la classe `Sheep`.

21. Dans `AbstractAnimal`, implantez la méthode `conditionalReproduce` . Le fonctionnement est le suivant : si l'animal n'est pas faible (méthode `isWeak`) et si un nombre tiré au hasard (avec `Math.random`) est inférieur à la probabilité de reproduction, alors, on renvoie une nouvelle instance de l'animal. Sinon, on renvoie `null`.

22. Vérifiez que les tests de `testConditionalReproduce` passent.

### Mouvement

Les animaux avancent avec une certaine vitesse déterminée par la méthode `getSpeed` : c'est le nombre de pas qu'ils peuvent faire en un tour. Un pas est un déplacement horizontal ou vertical d'une unité. 

23. Implantez la méthode `getSpeed` de `Sheep` et vérifiez que les tests de `testGetSpeed` passent. 

24. Les animaux peuvent se dépcer s'ils sont vivants. Dans `AbstractAnimal`, implantez la méthode `canMove` et vérifiez que les tests de `testCanMove` passent.

25. Enfin, implantez la méthode `nextMove` dans `abstractAnimal` (la documentation se trouve au niveau de l'interface `FieldElement`). Cette méthode prend en paramètre une position de départ et une position cible. Elle doit renvoyer un mouvement `FieldMove` c'est-à-dire un objet structuré contenant l'animal lui-même, sa position actuelle et sa nouvelle position calculée en fonction de sa cible et de sa vitesse. Un exemple vous ai donné dans le cas où l'animal a déjà atteint sa cible.

   Pour implanter cette méthode, vous devez : 
   
   * récupérer la vitesse de l'animal avec `getSpeed`
   * calculer une position qui se rapproche de la cible en faisant le bon nombre de pas. Les animaux avancent de façon horizontale ou verticale en choisissant la direction où ils ont le plus à parcourir. De nombreux tests vous sont proposés qui peuvent vous servir d'exemple.
   
### Un champ de mouton

26. Vérifiez que l'ensemble des tests de `SheepTest` passent.

27. Dans la fonction `main` de la classe `FieldSystem`, décommentez la ligne qui permet d'ajouter les moutons au champ et exécutez le programme. Vous devriez voir les moutons sous forme de points blancs se déplacer pour manger les zones d'herbes. 

## Les Loups

Le même travaille est à effectuer pour la classe `Wolf` qui représente les loups. Mais une grande partie partie a déjà été faite dans la classe `AbstractAnimal` et il ne reste plus qu'à écrire les quelques méthodes spécifiques aux loups et nécessaires au bon fonctionnement de la classe.

On vous fourni une classe `Wolf` qui contient, comme les classes `Sheep` et `Grass`, une liste de paramètres statiques avec des méthodes statiques de modification. 

28. Modifiez la déclaration de la classe `Wolf` pour qu'elle hérite de la classe `AbstractAnimal` : des erreurs apparaissent car il faut maintenant implantez les méthdoes abstraites déclarées dans `AbstractAnimal`

29. En vous inspirant de la classe `Sheep` (c'est la même chose), implantez les méthodes de la classe `Wolf` . Décommentez les tests de `WolfTest` et faites en sorte que tous les tests passent.

30. Dans la fonction `main` de la classe `FieldSystem`, décommentez la ligne qui permet d'ajouter les loups au champ et exécutez le programme. Vous devriez voir maintenant à la fois des moutons (blancs) et des loups (noirs) se déplacer dans le champ et interagir.

## Aller plus loin 

On a maintenant un modèle complet de système dynamique qui fonctionne. Vous pouvez facilement l'étendre en ajoutant ou en modifiant certains éléments. Par exemple

* Ajoutez un prédateur pour les loups : il faut créer une nouvelle classe d'animaux ainsi que le type correspondant. Si vous ajoutez un type d'animal, il faut aussi modifier légèrement l'objet d'affichage pour que le nouvel animal puisse être dessiné.

* Créer des implantations différentes des moutons et des loups : d'autres classes peuvent implanter ces animaux, par exemple avec des paramètres différents. On pourrait avoir à la fois des moutons rapides et des moutons lents. 

* Laissez jouer votre imagination : que souhaitez vous faire ? Le système permet beaucoup de choses. Le comportement par défaut de `AbstractAnimal` peut être modifier simplement en *héritant* de la classe et en surchargeant (Override) les méthodes nécessaires.

   
   

</section>

