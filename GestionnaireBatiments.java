import java.util.ArrayList;

public class GestionnaireBatiments {
    private ArrayList<Batiment> batiments;

    public GestionnaireBatiments() {
        batiments = new ArrayList<>();
    }

    public void ajouterBatiment(Batiment batiment) {
        batiments.add(batiment);
    }

    public Batiment getBatimentByName(String nom) {
        for (Batiment batiment : batiments) {
            if (batiment.getNom().equals(nom)) {
                return batiment;
            }
        }
        return null;
    }

    public ArrayList<Batiment> getBatiments() {
        return batiments;
    }
}
