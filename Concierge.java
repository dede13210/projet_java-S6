import java.util.ArrayList;

public class Concierge implements IPapotageListener{
    private String nom;
    private ArrayList<Bavard> listBavard;

    public Concierge(String nom) {
        this.nom = nom;

    }



    @Override
    public void newMessageRecu(PapotageEvent message) {

    }
}
