import java.util.ArrayList;

public class GestionnaireBatiments {
    private ArrayList<Batiment> batiments;

    //constructeur prend en parametre une liste de batiments
    public GestionnaireBatiments() {
        batiments = new ArrayList<>();
    }

    //ajoute un batiment à la liste de batyiment
    public void ajouterBatiment(Batiment batiment) {
        batiments.add(batiment);
    }

    //retrouve un batiment par son nom
    public Batiment getBatimentByName(String nom) {
        for (Batiment batiment : batiments) {
            if (batiment.getNom().equals(nom)) {
                return batiment;
            }
        }
        return null;
    }
    //getter de batiments: retourne un liste de batiment
    public ArrayList<Batiment> getBatiments() {
        return batiments;
    }
}
