# Gestion de Fournisseurs

## Description

L'application **Gestion de Fournisseurs** est une application Java qui permet à l'entreprise "TestLogistique" de gérer les informations de ses fournisseurs. L'application supporte jusqu'à 50 fournisseurs, en respectant certaines contraintes sur l'ajout, la modification, et la suppression des fournisseurs. 

Les coordonnées d'un fournisseur comprennent :
- Un identifiant unique généré automatiquement
- Un nom (qui doit être unique)
- Un numéro de téléphone
- Une adresse e-mail
- Un statut indiquant si une commande est en cours

<img src="Sans titre.png" alt="Description de l'image" />

## Fonctionnalités

L'application propose plusieurs fonctionnalités :
1. **Ajouter un fournisseur**
   - Permet d'ajouter un nouveau fournisseur si le nombre total ne dépasse pas 50 et que le nom est unique.
   
2. **Lister les fournisseurs**
   - Affiche le numéro et le nom de tous les fournisseurs enregistrés.

3. **Rechercher un fournisseur par numéro**
   - Recherche un fournisseur à partir de son identifiant et affiche ses coordonnées.

4. **Rechercher un fournisseur par nom**
   - Recherche un fournisseur à partir de son nom et affiche ses coordonnées.

5. **Modifier les coordonnées d'un fournisseur**
   - Permet de modifier le nom, téléphone, e-mail ou le statut de commande d'un fournisseur existant.

6. **Supprimer un fournisseur**
   - Supprime un fournisseur à condition qu'il n'ait pas de commande en cours.

## Utilisation

1. Clonez le projet depuis GitHub :

   ```bash
   git clone https://github.com/votre-utilisateur/gestion-fournisseurs.git
   ```

2. Compilez et exécutez le projet :

   - Vous pouvez utiliser un IDE comme IntelliJ IDEA ou Eclipse, ou bien compiler manuellement avec \`javac\`.

   ```bash
   javac Main.java
   java Main
   ```

3. Suivez les instructions à l'écran pour interagir avec l'application via le menu principal.

## Exemple de Menu

```
--- Menu Gestion des Fournisseurs ---
1. Ajouter un fournisseur
2. Lister les fournisseurs
3. Rechercher un fournisseur par numéro
4. Rechercher un fournisseur par nom
5. Modifier un fournisseur
6. Supprimer un fournisseur
0. Quitter
Votre choix :
```

## Pré-requis

- **Java 8** ou plus récent.
- Un environnement de développement pour compiler et exécuter le programme (IntelliJ, Eclipse, ou en ligne de commande).

## Structure du projet

```
src/
├── Fournisseur.java        # Classe définissant la structure d'un fournisseur
└── Main.java               # Classe principale contenant la logique de l'application
```

## Contributeurs

- **Sébastien De Laet** - Développeur

## Licence

Ce projet est sous licence ISC. Voir le fichier [LICENSE](LICENSE) pour plus d’informations.

