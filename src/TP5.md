---
title: "TP5 POO: MVC et interface graphqiue"
---

<section class="content">
## Objectif

Dans ce TP, nous allons reprendre le modèle développé lors du TP4 (avec les moutons) et créer une interface graphique permettant contrôler les différents paramètres de la simulation. On se basera sur le principe de "Modèle -- Vue -- Contrôleur" vu en cours.

## Récupération du code

L'ensemble du code est disponible dans un [repo indépendant](https://gitlab.dsi.universite-paris-saclay.fr/javabibs/TP5_MVC). Vous pouvez le récupérer directement avec IntelliJ avec "New Project from version control" ou en le téléchargeant depuis gitlab.


## Architecture générale

L'architecture du projet est déjà fournie. Le package `fr.upsaclay.bibs.fieldsystem.sheepfield` est globalement la correction du TP4. (On a très légèrement changé la classe `Grass` pour utiliser la même probabilité pour toutes les instances). 

### Nouveaux packages

L'application contient deux nouveaux packages :

* `fr.upsaclay.bibs.fieldsystem.control` : ce package va contenir le contrôleur de notre système, qui fera le lien entre le modèle et la vue.
* `fr.upsacal.bibs.fieldsystem.view` : ce package était déjà présent dans le TP4, mais nous l'avons complètement remanié. C'est là que sera implantée la partie graphique de l'application

### Le contrôleur

La classe principale est `FieldController` qui sera en charge de créer la vue, de l'initialiser, de créer et de paramétrer le modèle en fonction des actions reçues par l’utilisateur.

Le package contient aussi quelques autres classes :

* `FieldAction` est un `enum` qui contient la liste des actions possibles. Comme de nombreuses actions consistent à mettre à jour des paramètres, les actions possèdent une valeur par défaut (sous forme de `String`) ainsi qu'un type de paramètres.
* `FieldParameterType` : `enum` des différents type de paramètres possibles 
* L'interface `ParamaterVerfier` et deux implémentations `NonNegativeIntVerfier` et `ProbaVerifier` : ce sont des contrôleur de paramètre. Le but de ces classes est de vérifier qu'une valeur de paramètre sous forme de `String` correspond à une valeur correcte. On verra plus tard où sont utilisées ces classes.

### La Vue

* L'interface principale est `FieldView` qui est implémentée dans `SwingFieldView` : elle contient différentes méthodes que nous allons implémenter au fur et à mesure en développant en parallèle notre contrôleur.
* `ViewState` la liste des états que peut prendre l'interface, correspondant à différentes phases de l'application
* `DrawPanel` est le panneau qui sert à dessiner le champs. Il est déjà implémenté et fonctionnel
* `FieldParameter` est un composant personnalisé que je vous fournis pour les paramètres à mettre à jour avec un champs texte relié à un bouton.
* `ButtonListener` et `ParameterListener` des "écouteurs" pour nos composants que nous allons compléter au fur et à mesure.


## Démarrage et affichage

### Premier lancement

Lancez l'application : vous devez voir apparaître la fenêtre principale avec un panneau de contrôle sur la droite.

Le panneau contient un bouton "Start". Si vous cliquez sur "Start" rien ne se passe.

### Comprendre

Notre but pour l'instant est de faire disparaître le panneau de management initial quand on clique sur "Start". Pour cela, il faut déjà comprendre qui crée les éléments et où.

Regardez la classe `SwingFieldView` et répondez à ces questions (pour vous, dans votre tête, sur un papier, comme vous voulez) :
* où est déclarés le panneau `initialPanel` ?
* où est-il créé ?
* où est-il ajouté à la fenêtre principale ? (Est-il ajouté directement ? Est-il ajouté à u autre panneau ?)
* Même question pour `initialStartButton`

Enfin, regardez la méthode `drawSimulationInitView` : par qui est-elle appelée et quand ? Que se passe-t-il si vous changez la visibilité de `initialPanel` à `false` ? (Remettez à `true` après)

**Exercice** Implémenter une méthode `private void drawSimulationPlayView()` dans `SwingFieldView` qui ne contient qu'une seule ligne mettant la visibilité de `initialPanel` à `false`. Complétez la méthode `setViewState` pour appeler cette méthode lorsque l'on reçoit l'état `SIMULATION_PLAY`. Vérifiez que cela fonctionne en modifiant  l'état dessiné dans la méthode `initialize` du contrôleur. (Puis remettez l'ancienne initialisation)

### Agir

Faîtes en sorte que le panneau contenant "Start" disparaisse quand on appuie sur le bouton, pour cela il faut :

* Dans `SwingFieldView` : ajouter un `ButtonListener` au bouton `initialStartButton` lors de l'initialisation correspondant à l'action que l'on veut réaliser (`INITIAL_START`) 
* Dans la classe `ButtonListener` : implémenter la méthode `actionPerformed` pour envoyer l'action au contrôleur
* Dans la classe `FieldController` : implémenter la méthode `initial_start` qui doit demander à la vue d'afficher l'état `SIMULATION_PLAY`, (elle est appelée par `receiveAction` quand elle reçoit l'action correspondante).

## Gestion des affichages

En vous inspirant ce qui a été fait :

* créez un panneau `playPanel` qui contient uniquement un bouton "Pause"
* créez un panneau `pausePanel` qui contient deux boutons : "Start" et "Quit"

Le fonctionnement est le suivant :

Au départ on voit le `initPanel`. Quand on clique sur "Start", le `playPanel` s'affiche. Quand on clique sur "Pause", le `pausePanel` s'affiche. Si on clique sur "Start", on revient au `playPanel`, si on clique sur "Quit", on revient à `initPanel`. 

Pour cela, il faut 

* créer une nouvelle méthode `drawSimulationPauseView` et compléter les méthodes `draw...` existantes avec les bonnes visibilités pour les 3 panneaux. 
* appeler les méthodes correspondant aux états dans `setViewState`
* ajouter les écouteurs adéquats aux nouveaux boutons avec les bonnes actions de l'enum `FieldAction`
* compléter la méthode `receiveAction` du contrôleur pour réagir aux nouvelles actions et appeler les méthodes correspondantes dans la vue.


## Lancement de la simulation

### Création du champ

Le modèle, c'est à dire l'instance de  `Field`, n'est pas créée par défaut à la création du contrôleur : en effet, on veut se laisser la possibilité de modifier certains paramètres avant la création de l'objet.

En fait, on veut créer le modèle au moment du lancement quand on appuie sur "Start". Pour cela, dans le contrôleur, on va rajouter des actions au moment de l'évènement `INITIAL_START` :

* fabriquer une instance de `Field` dans le champs `field` du contrôleur (en utilisant la largeur et la hauteur définie par le contrôleur)
* associer le modèle à la vue avec `setField`
* ajouter les moutons et les loups au modèle. Rappel du dernier TP, voici la commande pour rajouter 10 moutons
   
        field.addRandomFieldElements(10, () -> new Sheep());
        
  Le nombre de moutons (i.e. nombre de loups) doit utiliser le nombre `initialNumberOfSheeps` défini dans le contrôleur.

Si vous faites tout ça, vous devez voir apparaître votre champ quand vous appuyez sur "Start"

### Suppression du champ

De la même façon, on veut que quand on clique sur "Quit" le système soit réinitialisé (supprimé) pour qu'on puisse relancer la simulation avec de nouveau paramètres.

Adaptez l'action correspondante pour que :

* `field` soit égal à `null`
* la vue reçoive une variable `field` nulle et soit mise à jour

(vous pouvez utiliser la méthode `quit` de `FieldController` et l'appeler depuis `receiveAction`

Vous devez voir le champ disparaître quand vous cliquez sur "Quit" et de nouveau avoir un rectangle blanc.

### Lancer la simulation

Rappel : le modèle de simulation est un champ contenant des éléments (l'herbe, les moutons, les loups) qui évoluent par étape. Pour observer les évolutions, il faut donc pouvoir appeler la méthode d'évolution du système à intervalle régulier.

Pour ce faire, la vue utilise un objet `Timer` défini par `Swing` . On a déjà déclaré l'objet `timer` dans `SwingFieldView` et on l'a instancié dans le constructeur.

Un `Timer` est en fait relié à un `ActionListener` (comme un bouton), l'action sera envoyée à intervalles régulier (comme si quelqu'un appuyait sur un bouton toutes les x millisecondes). On a défini l'action `EVOLVE` dans la liste des `FieldAction` qui correspond à ce que l'on veut. Pour que ça marche, il faut

Côté vue :

* dans le `initialize` de `SwingFieldView` : ajouter un `ButtonListner` au timer avec l'action `EVOLVE`
* implanter la méthode `setTimerDelay` dans `SwingFieldView` (qui appelle simplement `timer.setDelay` avec le bon paramètre)
* lancer le timer lorsqu'on lance la simulation (`drawSimulationPlayView`) avec `timer.start()` 
* arrêter le timer quand on met sur pause avec `timer.stop()`

Côté contrôleur :

* initialiser le délai initial du timer avec `INITIAL_DELAY` dans la fonction d'initialisation en appelant `view.setLoopDelay`
* agir quand on reçoit l'action `EVOLVE` en faisant évoluer le modèle (`field.evolve()`)


## Mise à jour des paramètres

On souhaite rajouter des paramètres à l'initialisation pour contrôler le nombre de moutons et de loups. Pour cela, on va utiliser la classe `FieldParameter` que j'ai écrite : c'est un `JPanel` un peu améliorer pour gérer l'interaction entre un champ de texte et un bouton.

### Ajouter les paramètres à la vue

**Etape 1** Implanter la méthode `createParameter` dans `SwingFieldView` : la méthode reçoit un texte à afficher (par exemple "Nombre de moutons") et une action correspondante. Il faut utiliser ces deux entrées pour créer les 3 paramètres du constructeur de `FieldParameter` : `label`,  `defaultValue`, et `verifier`

* `label` est la valeur reçue par `createParameter`
* `defaultValue` est obtenue avec `action.getDefaultValue()`
* `verifier` est un "vérifieur de paramètre" qui implémente l'interface `ParameterVerifier`. Cette interface contient par ailleurs une méthode statique pour créer des vérifieurs en fonction du type de paramètre action.getType()`


**Etape 2** : Ajouter deux `FieldParameter`, un pour les moutons, un pour les loups, à `initPanel` au moment de l'initialisation. Les deux actions correspondantes sont `INITIAL_SHEEPS` et `INITIAL_WOLVES`. 

Vous devriez voir des champs de texte avec des boutons "ok" apparaître au début de l'application. Si vous modifiez le texte, il passe en rouge. Le bouton ne devient actif que quand le texte a été modifié ET que la valeur est un entier correct. 

Cependant, rien ne se passe quand on clique sur "ok" (le nombre de moutons / loups au lancement de la simulation n'est pas modifié). On va corriger ça

### Implanter l'action correspondante

Côté contrôleur :

* implémenter la méthode `receiveAction(FieldAction action, String v)` qui prend à la fois une action et une chaîne de caractère correspondant à une valeur. Il faut effectuer les actions correspondant à `INITIAL_SHEEPS` et `INITIAL_WOLVES` (pour les autres actions, il ne se passe rien). La chaîne peut être directement transformée en entier avec `Integer.parseInt(v)` car la valeur de la chaîne a déjà été vérifiée.

Côté vue :

* Dans `createParameter`, il faut ajouter un écouteur de type `ParameterListener` au paramètre avec l'action correspondante (notez que l'écouteur prend aussi un pointeur vers le paramètre lui-même)
* implémenter la méthode `actionPerformed` de `ParameterListener` , elle doit appeler la méthode `receiveAction(FieldAction action, String v)` du contrôleur en utilisant la valeur du paramètre `param.getValue()`
* Par ailleurs, pour redonner au champs texte son aspect par défaut, il faut appeler la méthode `param.entrySaved()` dans l'écouteur après avoir signalé l'action au contrôleur.


## Tout le reste

En vous inspirant de tout ce qui est déjà implanté, ajouter de quoi contrôler l'ensemble des paramètres de la simulation :

* En phase d'initialisation et en phase de pause : tous les paramètres statiques des classes `Grass` `Sheep` et `Wolf`. Comme ces paramètres sont nombreux, on les affichera à la place du plateau de jeu en appuyant sur un bouton. 

* En phase de jeu : des boutons pour augmenter la vitesse, diminuer la vitesse (attention, le délai minimum est 1ms)

* En phase de pause : en plus des paramètres statiques, de quoi ajouter des loups / moutons dans la simulation

Pour cela, il vous faut ajouter les actions nécessaires dans `FieldAction`, les composants nécessaires dans la vue, un état supplémentaire de la vue pour le panneau de management, les réactions nécessaires dans le contrôleur et relier tout ça.









</section>

