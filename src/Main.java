import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
    static int idFournisseur = 1;

    /**
     * Point d'entree principal de l'application.
     * Affiche le menu principal et permet l'utilisateur de choisir une action.
     * Les actions possibles sont :
     * - Ajouter un fournisseur
     * - Lister les fournisseurs
     * - Rechercher un fournisseur par son num ro unique
     * - Rechercher un fournisseur par son nom
     * - Modifier un fournisseur
     * - Supprimer un fournisseur
     * - Quitter l'application
     * Le programme boucle jusqua' ce que l'utilisateur choisisse de quitter.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            afficherMenu();
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    ajouterFournisseur(scanner);
                    break;
                case 2:
                    listerFournisseurs();
                    break;
                case 3:
                    rechercherFournisseurParNumero(scanner);
                    break;
                case 4:
                    rechercherFournisseurParNom(scanner);
                    break;
                case 5:
                    modifierFournisseur(scanner);
                    break;
                case 6:
                    supprimerFournisseur(scanner);
                    break;
                case 0:
                    System.out.println("Fermeture de l'application.");
                    break;
                default:
                    System.out.println("Choix invalide. Réessayez.");
            }
        } while (choix != 0);

        scanner.close();
    }

    /**
     * Affiche le menu principal de l'application.
     * Le menu permet de choisir une action parmi les suivantes :
     * - Ajouter un fournisseur
     * - Lister les fournisseurs
     * - Rechercher un fournisseur par numéro
     * - Rechercher un fournisseur par nom
     * - Modifier un fournisseur
     * - Supprimer un fournisseur
     * - Quitter l'application
     */
    static void afficherMenu() {
        System.out.println("\n--- Menu Gestion des Fournisseurs ---");
        System.out.println("1. Ajouter un fournisseur");
        System.out.println("2. Lister les fournisseurs");
        System.out.println("3. Rechercher un fournisseur par numéro");
        System.out.println("4. Rechercher un fournisseur par nom");
        System.out.println("5. Modifier un fournisseur");
        System.out.println("6. Supprimer un fournisseur");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    /**
     * Ajoute un fournisseur.
     * Demande les informations du fournisseur (nom, téléphone, email, commande en
     * cours),
     * puis confirme si l'ajout doit être effectué.
     * Affiche un message d'erreur si le nombre maximum de fournisseurs est atteint
     * (50),
     * si le nom est vide ou déjà utilisé, ou si l'utilisateur annule l'ajout.
     * 
     * @param scanner le scanner pour lire les entrées utilisateur
     */
    static void ajouterFournisseur(Scanner scanner) {
        if (fournisseurs.size() >= 50) {
            System.out.println("Impossible d'ajouter plus de 50 fournisseurs.");
            return;
        }

        System.out.print("Entrez le nom du fournisseur: ");
        String nom = scanner.nextLine().trim();
        if (nom.isEmpty() || existeNom(nom)) {
            System.out.println("Nom invalide ou déjà utilisé.");
            return;
        }

        System.out.print("Entrez le numéro de téléphone: ");
        String telephone = scanner.nextLine();

        System.out.print("Entrez l'adresse email: ");
        String email = scanner.nextLine();

        System.out.print("Attendez-vous une commande de ce fournisseur ? (oui/non): ");
        boolean commande = scanner.nextLine().equalsIgnoreCase("oui");

        Fournisseur nouveauFournisseur = new Fournisseur(idFournisseur++, nom, telephone, email, commande);

        System.out.println("Coordonnées du fournisseur:");
        System.out.println(nouveauFournisseur);
        System.out.print("Confirmer l'ajout ? (oui/non): ");
        if (scanner.nextLine().equalsIgnoreCase("oui")) {
            fournisseurs.add(nouveauFournisseur);
            System.out.println("Fournisseur ajouté avec succès.");
        } else {
            System.out.println("Ajout annulé.");
        }
    }

    /**
     * Affiche la liste des fournisseurs enregistr s.
     * Si la liste est vide, affiche un message l'indiquant.
     * Sinon, affiche les ID et noms des fournisseurs.
     */
    static void listerFournisseurs() {
        if (fournisseurs.isEmpty()) {
            System.out.println("Aucun fournisseur enregistré.");
            return;
        }
        for (Fournisseur fournisseur : fournisseurs) {
            System.out.println("ID: " + fournisseur.id + ", Nom: " + fournisseur.nom);
        }
    }

    /**
     * Recherche et affiche un fournisseur par son numéro unique.
     * Demande à l'utilisateur d'entrer le numéro du fournisseur à rechercher.
     * Affiche les détails du fournisseur trouvé ou un message si aucun fournisseur
     * n'est trouvé avec ce numéro.
     * 
     * @param scanner le scanner pour lire les entrées utilisateur
     */
    static void rechercherFournisseurParNumero(Scanner scanner) {
        if (fournisseurs.isEmpty()) {
            System.out.println("Aucun fournisseur enregistré.");
            return;
        }

        System.out.print("Entrez le numéro du fournisseur: ");
        int numero = scanner.nextInt();

        Fournisseur fournisseur = trouverFournisseurParId(numero);
        if (fournisseur != null) {
            System.out.println(fournisseur);
        } else {
            System.out.println("Aucun fournisseur trouvé avec ce numéro.");
        }
    }

    /**
     * Cherche un fournisseur par son nom dans la liste des fournisseurs.
     * 
     * @param scanner le scanner pour lire les entrées utilisateur
     */
    static void rechercherFournisseurParNom(Scanner scanner) {
        if (fournisseurs.isEmpty()) {
            System.out.println("Aucun fournisseur enregistré.");
            return;
        }

        System.out.print("Entrez le nom du fournisseur: ");
        String nom = scanner.nextLine().trim();

        Fournisseur fournisseur = trouverFournisseurParNom(nom);
        if (fournisseur != null) {
            System.out.println(fournisseur);
        } else {
            System.out.println("Aucun fournisseur trouvé avec ce nom.");
        }
    }

    /**
     * Modifie les informations d'un fournisseur existant.
     * Demande à l'utilisateur l'identifiant du fournisseur à modifier, puis
     * autorise
     * les modifications sur le nom, le téléphone, l'email, et le statut de commande
     * en cours.
     * Affiche un message d'erreur si aucun fournisseur n'est trouvé avec
     * l'identifiant fourni.
     * Annule la modification si l'utilisateur choisit de ne rien modifier.
     * 
     * @param scanner le scanner pour lire les entrées utilisateur
     */
    static void modifierFournisseur(Scanner scanner) {
        if (fournisseurs.isEmpty()) {
            System.out.println("Aucun fournisseur enregistré.");
            return;
        }

        System.out.print("Entrez l'id du fournisseur à modifier: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        Fournisseur fournisseur = trouverFournisseurParId(numero);
        if (fournisseur != null) {
            System.out.println(fournisseur);

            int choix = -1;
            while (choix != 0) {
                System.out.println("Que souhaitez-vous modifier ?");
                System.out.println("1. Nom\n2. Téléphone\n3. Email\n4. Commande\n0. Annuler");

                if (scanner.hasNextInt()) {
                    choix = scanner.nextInt();
                    scanner.nextLine();

                    switch (choix) {
                        case 1:
                            System.out.print("Entrez le nouveau nom: ");
                            String nouveauNom = scanner.nextLine().trim();
                            if (!existeNom(nouveauNom)) {
                                fournisseur.nom = nouveauNom;
                                System.out.println("Nom mis à jour.");
                            } else {
                                System.out.println("Nom déjà utilisé.");
                            }
                            break;
                        case 2:
                            System.out.print("Entrez le nouveau téléphone: ");
                            fournisseur.telephone = scanner.nextLine();
                            System.out.println("Téléphone mis à jour.");
                            break;
                        case 3:
                            System.out.print("Entrez le nouvel email: ");
                            fournisseur.email = scanner.nextLine();
                            System.out.println("Email mis à jour.");
                            break;
                        case 4:
                            System.out.print("Attendez-vous une commande ? (oui/non): ");
                            fournisseur.commandeEnCours = scanner.nextLine().equalsIgnoreCase("oui");
                            System.out.println("Statut de la commande mis à jour.");
                            break;
                        case 0:
                            System.out.println("Modification annulée.");
                            return;
                        default:
                            System.out.println("Choix invalide, veuillez réessayer.");
                    }
                } else {
                    System.out.println("Entrée non valide, veuillez entrer un nombre.");
                    scanner.nextLine();
                }
            }
        } else {
            System.out.println("Aucun fournisseur trouvé avec ce numéro.");
        }
    }

    /**
     * Supprime un fournisseur.
     * Demande le numéro du fournisseur à supprimer, puis confirme si la suppression
     * doit être effectuée.
     * Si le fournisseur a une commande en cours, la suppression est impossible.
     * 
     * @param scanner le scanner pour lire les entrées utilisateur
     */
    static void supprimerFournisseur(Scanner scanner) {
        if (fournisseurs.isEmpty()) {
            System.out.println("Aucun fournisseur enregistré.");
            return;
        }

        System.out.print("Entrez le numéro du fournisseur à supprimer: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        Fournisseur fournisseur = trouverFournisseurParId(numero);
        if (fournisseur != null) {
            if (fournisseur.commandeEnCours) {
                System.out.println("Impossible de supprimer un fournisseur avec une commande en cours.");
            } else {
                System.out.println(fournisseur);
                System.out.print("Confirmer la suppression ? (oui/non): ");
                if (scanner.nextLine().equalsIgnoreCase("oui")) {
                    fournisseurs.remove(fournisseur);
                    System.out.println("Fournisseur supprimé.");
                } else {
                    System.out.println("Suppression annulée.");
                }
            }
        } else {
            System.out.println("Aucun fournisseur trouvé avec ce numéro.");
        }
    }

    /**
     * Recherche un fournisseur par son identifiant unique.
     *
     * @param id l'identifiant du fournisseur à rechercher
     * @return le fournisseur correspondant à l'identifiant, ou null s'il n'est pas
     *         trouvé
     */
    static Fournisseur trouverFournisseurParId(int id) {
        for (Fournisseur fournisseur : fournisseurs) {
            if (fournisseur.id == id) {
                return fournisseur;
            }
        }
        return null;
    }

    /**
     * Cherche un fournisseur par son nom dans la liste des fournisseurs.
     * 
     * @param nom le nom du fournisseur à chercher
     * @return le fournisseur trouvé ou null si aucun fournisseur n'a été trouvé
     */
    static Fournisseur trouverFournisseurParNom(String nom) {
        for (Fournisseur fournisseur : fournisseurs) {
            if (fournisseur.nom.equalsIgnoreCase(nom)) {
                return fournisseur;
            }
        }
        return null;
    }

    /**
     * Cherche si un fournisseur existe déjà avec le nom donné.
     *
     * @param nom le nom du fournisseur à chercher
     * @return true si le nom existe déjà, false sinon
     */
    static boolean existeNom(String nom) {
        for (Fournisseur fournisseur : fournisseurs) {
            if (fournisseur.nom.equalsIgnoreCase(nom)) {
                return true;
            }
        }
        return false;
    }
}