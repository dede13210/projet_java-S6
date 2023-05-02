import java.util.ArrayList;

public class Concierge implements IPapotageListener{
    private String nom;
    private ArrayList<Bavard> listBavard;

    public Concierge(String nom) {
        this.nom = nom;
        this.listBavard=new ArrayList<Bavard>();
    }
    public Bavard getBavardByName(String nom) {
        for (Bavard bavard : listBavard) {
            if (bavard.getNom().equals(nom)) {
                return bavard;
            }
        }
        return null;
    }
    private ConciergeGUI conciergeGUI;

    public void setConciergeGUI(ConciergeGUI conciergeGUI) {
        this.conciergeGUI = conciergeGUI;
    }


    public void ajouterBavard(Bavard bavard) {
        this.listBavard.add(bavard);
    }

    @Override
    public void newMessageRecu(PapotageEvent message) {
        if (conciergeGUI != null) {
            conciergeGUI.addMessage(message);
        }
        for (Bavard bavard : listBavard) {
            bavard.newMessageRecu(message);
        }
    }


    @Override
    public void createPapotage(String sujet, String corps) {

    }

    public String getNom() {
        return this.nom;
    }
}
