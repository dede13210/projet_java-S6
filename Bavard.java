import java.util.ArrayList;

public class Bavard implements IPapotageListener {
    private String nom;
    private ArrayList<Concierge> listConcierge;
    private BavardGUI bavardGUI;
    private ArrayList<Bavard> listBavardConnecte;

    public Bavard(String nom,ArrayList<Bavard> listBavardConnecte) {
        this.nom = nom;
        this.listConcierge=new ArrayList<Concierge>();
        this.listBavardConnecte=listBavardConnecte;
    }
    public void addBavardGUI(BavardGUI bavardGUI){
        this.bavardGUI = bavardGUI;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Concierge> getListConcierge() {
        return listConcierge;
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
        bavardGUI.messageListener(message);

    }

    @Override
    public void createPapotage(String sujet, String corps) {
        PapotageEvent papotage=new PapotageEvent(this,sujet,corps);
        for(Concierge concierge:this.listConcierge){
            concierge.newMessageRecu(papotage);
        }

    }

    @Override
    public void newUserConnected(OnLineBavardEvent connect) {
        bavardGUI.connectListener(connect);

    }
    @Override
    public void newUserDisconnected(OfflineBavardEvent disconnect) {
        bavardGUI.disconnectListener(disconnect);

    }
}
