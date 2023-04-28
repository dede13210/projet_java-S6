import java.util.ArrayList;

public class Bavard implements IPapotageListener {
    private String nom;
    private ArrayList<Concierge> listConcierge;

    public Bavard(String nom) {
        this.nom = nom;
        this.listConcierge=new ArrayList<Concierge>();
    }

    public String getNom() {
        return nom;
    }

    public void ajouterConcierge(Concierge concierge) {
        this.listConcierge.add(concierge);
    }

    @Override
    public String toString() {
        return "Bavard{" +
                "nom='" + nom + '\'' +
                '}';
    }


    @Override
    public void newMessageRecu(PapotageEvent message) {
        System.out.println(message.toString());

    }

    @Override
    public void createPapotage(String sujet, String corps) {
        PapotageEvent papotage=new PapotageEvent(this,sujet,corps);
        for(Concierge concierge:this.listConcierge){
            concierge.newMessageRecu(papotage);
        }


    }
}
