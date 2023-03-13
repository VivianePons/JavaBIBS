---
title: "Projet Programmation orientée objet"
---

<section class="content">

## Pour récupérer le projet

### Etape 1 : se connecter sur gitlab

Se connecter sur [https://gitlab.dsi.universite-paris-saclay.fr/](https://gitlab.dsi.universite-paris-saclay.fr/) en utilisant vos identifiant de l'université.


### Etape 2 : fork

**Une personne par groupe**

[Le sujet sur gitlab](https://gitlab.dsi.universite-paris-saclay.fr/javabibs/Projet_Tetris)

Cliquez en haut à droite sur "Fork" 

### Etape 3 : Ajouter les membres du groupes

Project Informations >> Membres

Ajouter : les autres membres du groupe ainsi que les deux enseignants 

* Viviane Pons
* Balthazar Charles

### Etape 4 : configuration de git

Si vous n'avez pas git, [installer git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)

Vous n'avez jamais utilisé git ? Confirguration avant la première utilisation [Plus d'explications ici](https://git-scm.com/book/fr/v2/D%C3%A9marrage-rapide-Param%C3%A9trage-%C3%A0-la-premi%C3%A8re-utilisation-de-Git)

    $ git config --global user.name "John Doe"
    $ git config --global user.email johndoe@example.com
    $ git config --global core.editor nano
    
La dernière ligne est optionnelle : elle permet de configurer votre éditeur par défaut. Par défaut, c'est souvent `vim`. Si vous ne savez pas ce que c'est que `vim`, c'est que vous voulez le changer !

### Etape 5 : cloner votre projet

Ouvrir un terminal et se placer dans votre répertoire de travail. Puis lancer la commande suivante avec la bonne adresse

    git clone http://l'adresse.git
    
L'adresse à cloner se trouve dans `gitlab` dans le nouveau projet créé après le `fork`
    
### Etape 6 :

**A faire par tous les membres du groupe, chacun son tour**

* Un membre modifie le fichier `README.md` en rajoutant son nom dans la liste des membres
* Puis lance les commandes `git` suivantes
  - `git add README.md`
  - `git commit -m "ajout de mon nom"`
  - `git push`
* Tous les autres membres lancent `git pull` et doivent ainsi récuperer le fichier modifié

## Les principales commandes git dont on a besoin

### Pour récupérer un nouveau projet

(à faire là où on veut copier le projet)

`git clone projet` : va récupérer un projet sur un serveur distant et en fait une copie locale. Vous Récupérer l'ensemble de projet avec tout son histrique et ses versions.

### Avant chaque séance de travail : récupérer les dernières modification

(A faire dans le repo git)

`git pull` : va récupérer les dernières mises à jour sur le projet. Attention, si vous avez des modifications locales, ça ne va pas vouloir le faire !

### Voir les modifications en cours

`git status` : vous indique quels sont les fichiers que vous avez modifiés localement et si vous êtes à jour

### Après chaque séance : enregistrer et pusher votre travail

`git add src` : ajoute toutes les modifications faites dans le repertoire `src` (à adapter si les modifications sont ailleurs !)

`git commit` ou `git commit -m "mon message"` : enregistre vos modifications dans `git` de façon locale. Un "commit" est toujours accompagné d'un message décrivant les modifications

`git push` : envoie le ou les commits au serveur principal. 

### Si le `pull` ne marche pas ?

Peut-être avez-vous des modifications locales ? Que dit `git status` ? 

* OUI et vous voulez GARDER vos modifs : ajouter vos modifications (avec `git add`) et faites un commit, puis essayez de nouveau de faire un pull : `git` va tenter de fusionner les différentes modifications

* OUI et vous ne voulez PAS les garder : faites `git stash` 

* Non ? Que dit le message d'erreur ? Vous êtes bloqués ? On peut vous aider !

### Si le `push` ne marche pas 

Peut-être qu'il y a eu des modifications sur le repo que vous n'avez pas récupérées, essayez de faire `git pull`

### Pour éviter les conflits, que faire ?

Répartissez vous les tâches et ne travaillez pas en même temps sur le même fichier.

### Il y a tout de même des conflits ! On est Perdu !

On est là pour vous aider !!
    
   








</section>

