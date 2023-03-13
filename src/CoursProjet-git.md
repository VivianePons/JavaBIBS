---
title: 'Programmation Orientée Objet -- Java'
subtitle:  'Projet : Minicours git'
author: Viviane Pons
date: Master BIBS Université Paris-Saclay 
---

## Qu'est-ce que `git` ?

Réponse : un système de gestion de versions. A chaque étape, ce qui est sauvegardé est la *différence* par rapport à la version précédente. On peut donc *revenir en arrière*.

## Qu'est-ce que permet `git` ?

* revenir à une ancienne version
* collaboration

## Comment on l'utilise ?

Schéma habituel d'une collaboration :

* chaque personne installe `git` sur sa machine
* une personne crée un "repo" (= *repository*) sur une machine distante (par exemple sur *github* ou *gitlab*) et donne accès aux autres 
* chaque membre effectue un "clone du repo" sur sa machine
* les membres font des modifications locales qu'ils et elles "poussent" ensuite sur le repo distant
* les membres récupèrent la dernière version depuis le repo distant

## Info importante

La copie locale d'un projet contient **les mêmes informations** que la copie "principale" sur le serveur distant : c'est-à-dire qu'il y a bien tout l'historique et l'ensemble des versions du projet.

On n'est donc pas **dépendant** du serveur distant : on peut en changer. 

## A quoi sert le serveur distant ?

Il faut se mettre d'accord sur un serveur pour que tout le monde pousse et récupère les infos au même endroit. En général, on utilise un service en ligne comme `github` ou `gitlab`. 

## C'est quoi `github` et `gitlab` ? C'est quoi la différence avec `git` ?

`git` est le logiciel de gestion de version qu'on installe sur sa machine. C'est un logiciel open source et il ne dépend d'aucune plateforme en particulier.

`github` est une plateforme privée (possédée par Microsoft) qui offre un *service* : des serveurs pour créer des repo `git` et d'autres choses liées à de la gestion de projet (les `issue`, les `pull request`, les groupes, la gestion des droits et des accès, les labels, etc.) 

`gitlab` est à la fois un logiciel open-source qui propose des fonctionnalités de gestion de serveurs `git` et gestion de projet "comme `github`" ET une plateforme `gitlab.com` avec un service `gitlab`. 

## Une plateforme `gitlab` à l'université

L'université Paris-Saclay propose un accès à un serveur `gitlab` à l'ensemble de ses personnels et étutiant-es. La plateforme utilise le logiciel open-source `gitlab` mais est hébergée et gérée par l'université.

[https://gitlab.dsi.universite-paris-saclay.fr](https://gitlab.dsi.universite-paris-saclay.fr)

Vous avez déjà utilisé cette plateforme pour récupérer certains TP. Nous allons l'utiliser pour le projet. [Pour les détails et les principales commandes git, voir ici](TP-Projet.html)






 






  
