import java.util.ArrayList;

public class Bavard implements IPapotageListener {
    private String nom;
    private ArrayList<Concierge> listConcierge;
    private BavardGUI bavardGUI;
    private ArrayList<Bavard> listBavardConnecte;

    //constructeur
    public Bavard(String nom) {
        this.nom = nom;
        this.listConcierge=new ArrayList<Concierge>();
    }

    //fonction qui lie une fenetre bavard gui au bavard
    public void addBavardGUI(BavardGUI bavardGUI){
        this.bavardGUI = bavardGUI;
    }

    //les getters
    public String getNom() {
        return nom;
    }

    public ArrayList<Concierge> getListConcierge() {
        return listConcierge;
    }

    //ajoute un concierge à la liste de concierge
    public void ajouterConcierge(Concierge concierge) {
        this.listConcierge.add(concierge);
    }


    // donne les attribut sous forme de string
    @Override
    public String toString() {
        return "Bavard{" +
                "nom='" + nom + '\'' +
                '}';
    }

    //affiche le nouveau message reçu
    @Override
    public void newMessageRecu(PapotageEvent message) {
        bavardGUI.messageListener(message);

    }

    //creer un papotage en prennant comme argument un sujet et un corps
    @Override
    public void createPapotage(String sujet, String corps) {
        PapotageEvent papotage=new PapotageEvent(this,this.nom,sujet,corps);
        for(Concierge concierge:this.listConcierge){
            concierge.newMessageRecu(papotage);
        }

    }

    //affiche un nouvelle utilisateur connecte
    @Override
    public void newUserConnected(OnLineBavardEvent connect) {
        bavardGUI.connectListener(connect);

    }
    //affiche un nouvelle utilisateur deconnecte
    @Override
    public void newUserDisconnected(OfflineBavardEvent disconnect) {
        bavardGUI.disconnectListener(disconnect);

    }
}
