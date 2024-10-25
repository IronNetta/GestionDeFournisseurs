import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
    static int idFournisseur = 1;

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

    static void listerFournisseurs() {
        if (fournisseurs.isEmpty()) {
            System.out.println("Aucun fournisseur enregistré.");
            return;
        }
        for (Fournisseur fournisseur : fournisseurs) {
            System.out.println("ID: " + fournisseur.id + ", Nom: " + fournisseur.nom);
        }
    }

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

    static void modifierFournisseur(Scanner scanner) {
        if (fournisseurs.isEmpty()) {
            System.out.println("Aucun fournisseur enregistré.");
            return;
        }

        System.out.print("Entrez le numéro du fournisseur à modifier: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        Fournisseur fournisseur = trouverFournisseurParId(numero);
        if (fournisseur != null) {
            System.out.println(fournisseur);
            System.out.println("Que souhaitez-vous modifier ?");
            System.out.println("1. Nom\n2. Téléphone\n3. Email\n4. Commande\n0. Annuler");

            int choix = scanner.nextInt();
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
            }
        } else {
            System.out.println("Aucun fournisseur trouvé avec ce numéro.");
        }
    }

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

    static Fournisseur trouverFournisseurParId(int id) {
        for (Fournisseur fournisseur : fournisseurs) {
            if (fournisseur.id == id) {
                return fournisseur;
            }
        }
        return null;
    }

    static Fournisseur trouverFournisseurParNom(String nom) {
        for (Fournisseur fournisseur : fournisseurs) {
            if (fournisseur.nom.equalsIgnoreCase(nom)) {
                return fournisseur;
            }
        }
        return null;
    }

    static boolean existeNom(String nom) {
        for (Fournisseur fournisseur : fournisseurs) {
            if (fournisseur.nom.equalsIgnoreCase(nom)) {
                return true;
            }
        }
        return false;
    }
}