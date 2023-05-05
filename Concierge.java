import java.util.ArrayList;

public class Concierge implements IPapotageListener{
    private String nom;
    private ArrayList<Bavard> listBavard;
    private ConciergeGUI conciergeGUI;

    public Concierge(String nom) {
        this.nom = nom;
        this.listBavard=new ArrayList<Bavard>();
        this.conciergeGUI = new ConciergeGUI(this);
    }

    public Bavard getBavardByName(String nom) {
        for (Bavard bavard : listBavard) {
            if (bavard.getNom().equals(nom)) {
                return bavard;
            }
        }
        return null;
    }

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

    @Override
    public void newUserConnected(OnLineBavardEvent bavard) {

    }

    @Override
    public void newUserDisconnected(OfflineBavardEvent offlineBavardEvent) {
        for (Bavard bavard : listBavard) {
            bavard.newUserDisconnected(offlineBavardEvent);
        }
        this.listBavard.remove(getBavardByName(offlineBavardEvent.getBavard()));
        System.out.println(this.listBavard);
    }

    public ConciergeGUI getConciergeGUI() {
        return conciergeGUI;
    }

    public String getNom() {
        return this.nom;
    }

    public ArrayList<Bavard> getListBavard() {
        return listBavard;
    }
}
