import java.util.ArrayList;

//classe concierge qui permet de recevoir et diffuser les messages des bavards qui lui sont assignés
public class Concierge implements IPapotageListener{
    private String nom;
    private ArrayList<Bavard> listBavard;
    private ConciergeGUI conciergeGUI;

    //contstructeur où l'on créer un concierge
    public Concierge(String nom) {
        this.nom = nom;
        this.listBavard=new ArrayList<Bavard>();
        this.conciergeGUI = new ConciergeGUI(this);
    }

    //on vérifie si le bavard appartient bien à la liste des bavards
    public Bavard getBavardByName(String nom) {
        for (Bavard bavard : listBavard) {
            if (bavard.getNom().equals(nom)) {
                return bavard;
            }
        }
        return null;
    }

    //on ajoute un bavard à la liste des bavards du concierge
    public void ajouterBavard(Bavard bavard) {
        this.listBavard.add(bavard);
    }

    //le concierge écoute les messages envoyés par les utilisateurs
    @Override
    public void newMessageRecu(PapotageEvent message) {
        if (conciergeGUI != null) {
            conciergeGUI.addMessage(message);
        }
        for (Bavard bavard : listBavard) {
            bavard.newMessageRecu(message);
        }
    }

    //deux fonctions de l'interface
    @Override
    public void createPapotage(String sujet, String corps) {

    }

    @Override
    public void newUserConnected(OnLineBavardEvent bavard) {

    }

    //
    @Override
    public void newUserDisconnected(OfflineBavardEvent offlineBavardEvent) {
        for (Bavard bavard : listBavard) {
            bavard.newUserDisconnected(offlineBavardEvent);
        }
        this.listBavard.remove(getBavardByName(offlineBavardEvent.getBavard()));
    }
    // des fonctions qui renvoient différents attributs
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
