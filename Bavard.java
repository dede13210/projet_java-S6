import java.util.ArrayList;

public class Bavard implements IPapotageListener {
    private String nom;
    private ArrayList<Concierge> listConcierge;

    public Bavard(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
    public PapotageEvent createPapotageEvent(String sujet, String corps){
        return new PapotageEvent(this,sujet,corps);
    }


    @Override
    public void newMessageRecu(PapotageEvent message) {

    }
}
