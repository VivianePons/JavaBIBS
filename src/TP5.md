---
title: "TP5 POO: MVC et interface graphqiue"
---

<section class="content">
## Objectif

Dans ce TP, nous allons reprendre le modèle développé lors du TP4 (avec les moutons) et créer une interface graphique permettant contrôler les différents paramètres de la simulation. On se basera sur le principe de "Modèle -- Vue -- Contrôleur" vu en cours.

## Récupération du code

L'ensemble du code est disponible dans un [repo indépendant](https://gitlab.dsi.universite-paris-saclay.fr/javabibs/TP5_MVC). Vous pouvez le récupérer en cliquant sur le bouton de téléchargement à côté de "Clone" (où en effectuant un clone avec *git* si vous savez faire). 

Ensuite, avec Eclipse, vous pouvez créer un nouveau projet en utilisant le dossier téléchargé comme source. Ainsi, l'ensmeble de l'architecture de classes et packages seront déjà créés.

## Architecture générale

L'architecture du projet est déjà fournie. Le package `fr.upsaclay.bibs.fieldsystem.sheepfield` est globalement la correction du TP4. (On a très légèrement changé la classe `Grass` pour utiliser la même probabilié pour toutes les instances). 

### Nouveaux packages

L'application contient deux nouveaux packages :

* `fr.upsaclay.bibs.fieldsystem.control` : ce package va contenir le contrôleur de notre système, qui fera le lien entre le modèle et la vue.
* `fr.upsacal.bibs.fieldsystem.view` : ce package était déjà présent dans le TP4, mais nous l'avons complètement remanié. C'est là que sera implantée la partie graphique de l'application

### Le contrôleur

La classe principale est `FieldController` qui sera en charge de créer la vue, de l'initialiser, de créer et de paramétrer le modèle en fonction des actions reçues par l’utilisateur.

Le package contient aussi deux autres classes qui sont en fait des enum :

* `FieldAction` contient la liste des actions possibles. Comme de nombreuses actions consistent à mettre à jour des paramètres, les actions possèdent une valeur par défaut (sous forme de `String`) ainsi qu'un type de paramètres.

* `FieldParameterType` : les différents type de paramètres possibles

### La Vue

* L'interface principale est `FieldView` qui est implémentée dans `SwingFieldView` : elle contient différentes méthodes que nous allons implémenter au fur et à mesure en développant en parallèle notre contrôleur.
* `DrawPanel` est le panneau qui sert à dessiner le champs. Il est déjà implémenté et fonctionnel
* `FieldActionComponent` est l'interface spécifique pour les composants chargés d'envoyer des actions au contrôleur (les boutons)
* `FieldButton` est l'implantation de `FieldActionComponent`qui hérite de `JButton` : tout est déjà fait, mais vous pouvez jeter un oeil (c'est assez simple) car vous pourriez utilsier un système similaire pour votre projet.
* `FieldParameter` est un composant personnalisé que je vous fournis pour gérer la mise à jour d'un paramètre.
* `ParameterVerifier` est une interface utilisée pour vérifier le texte entré dans les champs des paramètres

## Démarrage et affichage

### Premier lancement

Lancez l'application : vous devez voir apparaître la fenêtre principale avec un panneau de contrôle sur la droite.

Le panneau contient un bouton "Start" ainsi que des champs pour mettre à jour le nombre initial de moutons et de loups. Essayez le changer les valeurs et voyez ce qui se passe.

Enfin, si vous cliquez sur "Start" rien ne se passe.

### Comprendre

Notre but pour l'instant est de faire disparaître le panneau de management initial quand on clique sur "Start". Pour cela, il faut déjà comprendre qui crée les éléments et où.

Regardez la classe `SwingFieldView` et répondez à ces questions (pour vous, dans votre tête, sur un papier, comme vous voulez) :
* où est déclarés le panneau `initialPanel` ?
* où est-il créé ?
* où est-il ajouté à la fenêtre principale ? (Est-il ajouté directement ? Est-il ajouté à u autre panneau ?)
* Même question pour `initialStartButton` et pour les paramètres du nombre de moutons et nombre de loup.

Enfin, regardez la méthode `drawSimulationInitView` : par qui est-elle appelée et quand ? Que se passe-t-il si vous changez la visibilité de `initialPanel` à `false` ? (Remettez à `true` après)

### Agir

Faîtes disparaître le panneau quand on appui sur "Start", pour cela il faut :

* Dans `SwingFieldView` : implanter la méthode `setFieldActionListener` pour ajouter un listener au bouton `initialStartButton`
* Dans `SwingFieldView` : implanter la méthode `drawSimulationPlayView` de sorte que `initialPanel` soit rendu non visible
* Dans `FieldController` : dans la méthode `initiliaze` après l'initialisation de la vue, appeler la méthode `setFieldActionListener` en s'ajoutant soi-même (`this`) comme listener.
* Dans `FieldController` : dans la méthode `actionPerformed`, appeler `view.drawSimulationPlayView()` en cas d'action `INITIAL_START`.

## Gestion des affichages

En vous inspirant ce qui a été fait :

* créez un panneau `playPanel` qui contient uniquement un bouton "Pause"
* créez un panneau `pausePanel` qui contient deux boutons : "Start" (mais utilisez une instance différente même si le texte est le même) et "Quit"

Le fonctionnement est le suivant :

Au départ on voit le `initPanel`. Quand on clique sur "Start", le `playPanel` s'affiche. Quand on clique sur "Pause", le `pausePanel` s'affiche. Si on clique sur "Start", on revient au `playPanel`, si on clique sur "Quit", on revient à `initPanel`. 

Pour cela, il faut implanter les méthodes `drawSimulationPlayView` et `drawSimulationPauseView`, les appeler depuis le contrôleur et s'assurer que le contrôleur a bien été ajouté aux boutons et que les boutons sont liés à la bonne action. 

(Pour l'instant, on ne fait rien pour `drawManagementView` et `eraseMAnagementView`)

## Lancement de la simulation

### Création du champ

Le modèle, c'est à dire l'instance de  `Field`, n'est pas créé par défaut à la création du contrôleur : en effet, on veut se laisser la possibilité de modifier certains paramètres avant la création de l'objet.

En fait, on veut créer le modèle au moment du lancement quand on appuie sur "Start". Pour cela, dans le contrôleur, on va rajouter des actions au moment de l'évènement `INITIAL_START` :

* fabriquer une instance de `Field` dans le cham `field` du contrôleur (en utilisant la largeur et la hauteur définie par le contrôleur)
* associer le modèle à la vue avec `setField`
* ajouter les moutons et les loups au modèle. Rappel du dernier TP, voici la commande pour rajouter 10 moutons
   
        field.addRandomFieldElements(10, () -> new Sheep());
        
  Le nombre de moutons (i.e. nombre de loups) doit utiliser le nombre `initialNumberOfSheeps` définit dans le contrôleur.

* mettre à jour la vue

Si vous faites tout ça, vous devez voir apparaître votre champ quand vous appuyez sur "Start" (notez que pour l'instant, malgré les paramètres qui s'affichent, la vue ne sait pas encore contrôler le nombre initial de moutons / loups)

### Suppression du champ

De la même façon, on veut que quand on clique sur "Quit" le système soit réinitialisé (supprimé) pour qu'on puisse relancer la simulation avec de nouveau paramètres.

Adaptez l'action correspondante pour que :

* `field` soit égal à `null`
* la vue reçoive une variable `field` nulle et soit mise à jour

Vous devez voir le champ disparaître quand vous cliquez sur "Quit" et de nouveau avoir un rectangle blanc.

### Lancer la simulation

Rappel : le modèle de simulation est un champ contenant des éléments (l'herbe, les moutons, les loups) qui évoluent par étape. Pour observer les évolutions, il faut donc pouvoir appeler la méthode d'évolution du système à intervalle régulier.

Pour ce faire, la vue utilise un objet `Timer` défini par `Swing` L'interface de la vue définit plusieurs méthodes de gestion de cet objet : en particulier les méthodes `startActionLoop` et `stopActionLoop`

L'action exécutée par le timer est lancée avec un `ActionListner`. Pour différencier cette action du lancement de celui d'un bouton / paramètre, on utilise une classe interne spécifique pour écouter le timer : la classe `UpdateActionListener` qui à chaque appel fait évoluer le système et met à jour la vue.

* Dans la méthode d'initialisation du contrôleur, ajouter l'appel suivant :

        view.setLoopAction(new UpdateActionListener());
        
* De même, rajouter un appel à `view.setLoopDelay` pour lui donner le délai initial défini par le contrôleur (500 ms)

* Enfin, ajoutez les appells nécessaires à `view.startLoopAction()` et `view.stopLoopAction()` au niveau des action `INITIAL_START`, `PAUSE`, `START`, et `QUIT` .

## Mise à jour des paramètres

Comme on l'a remarqué, pour l'instant le nombre de moutons et loups initiaux n'est pas contrôlé par les nombres entrés par l'utilisateur. En effet : les éléments graphiques ont été créés mais n'ont pas été reliés au contrôleur.

### Dans la vue

Les paramètres font partie d'une liste "initialParamters" créés au moment de la création de la vue et ajouter au panneau initial. Ce sont des éléments de type `FieldParameter` qui est en fait un panneau customisé prenant en charge l'affichage et la sauvegarde d'un paramètre. Comme pour un bouton, on peut lui ajouter un `ActionListener`

* dans la méthode `setFieldActionListener` de la vue, ajouter le `listener` à tous les `FieldParameter` de la liste

### Dans le contrôleur

* Ajoutez deux cas au `switch case` de la méthode `actionPerformed` qui correspondent aux deux actions `INITIAL_SHEEPS` et `INITIAL_WOLVES`
* récupérer la valeur du paramètre avec `comp.getActionCommand()` (qui vous renvoie une chaîne), transformez la en `int` et mettez à jour le paramètre du contrôleur qui correspond (soit `numberOfInitialSheeps` , soit `numberOfInitialWolves`).

## Tout le reste

En vous inspirant de tout ce qui est déjà implanté, ajouter de quoi contrôler l'ensemble des paramètres de la simulation :

* En phase d'initialisation et en phase de pause : tous les paramètres statiques des classes `Grass` `Sheep` et `Wolf`. Comme ces paramètres sont nombreux, on les affichera à la place du plateau de jeu en appuyant sur un bouton. On utilisera pour ça les méthode `drawManagementView` et `eraseManagementView`.

* En phase de jeu : des boutons pour augmenter la vitesse, diminuer la vitesse (attention, le délai minimum est 1ms)

* En phase de pause : en plus des paramètres statiques, de quoi ajouter des loups / moutons dans la simulation

Pour cela, il vous faut ajouter les actions nécessaires dans `FieldAction`, les composants nécessaires dans la vue, les réactions nécessaires dans le contrôleur et relier tout ça.









</section>

