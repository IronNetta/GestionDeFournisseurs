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

    @Override
    public String toString() {
        return "ID: " + id + ", Nom: " + nom + ", Téléphone: " + telephone + ", Email: " + email + ", Commande en cours: " + commandeEnCours;
    }
}

