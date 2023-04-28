import java.util.ArrayList;

public class Concierge implements IPapotageListener{
    private String nom;
    private ArrayList<Bavard> listBavard;

    public Concierge(String nom) {
        this.nom = nom;
        this.listBavard=new ArrayList<Bavard>();
    }



    @Override
    public void newMessageRecu(PapotageEvent message) {
        for(Bavard bavard:this.listBavard){
            newMessageRecu(message);
        }

    }

    @Override
    public void createPapotage(String sujet, String corps) {

    }
}
