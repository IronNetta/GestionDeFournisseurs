public class Fournisseur {
    int id;
    String nom;
    String telephone;
    String email;
    boolean commandeEnCours;

    public Fournisseur(int id, String nom, String telephone, String email, boolean commandeEnCours) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
        this.commandeEnCours = commandeEnCours;
    }

    /**
     * Retourne une chaine de caracteres decrivant le fournisseur.
     * La chaine contient l'identifiant, le nom, le numero de telephone,
     * l'adresse e-mail et le statut de la commande en cours.
     * 
     * @return une cha ne de caracteres decrivant le fournisseur
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Nom: " + nom + ", Téléphone: " + telephone + ", Email: " + email
                + ", Commande en cours: " + commandeEnCours;
    }
}
