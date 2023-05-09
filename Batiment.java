import java.util.ArrayList;

public class Batiment {
    Concierge concierge;
    private String nom;

    //constructeur ou l'on creer un concierge
    public Batiment(String nom){
        this.nom=nom;
        creerConcierge("manuel");
    }
    //constructeur ou on ajoute nous meme un concierge
    public Batiment(Concierge concierge, String nom){

        this.concierge= concierge;
        this.nom=nom;
    }

    //fonction qui creer un concierge à partir d'un nom
    public void creerConcierge(String nom){
        Concierge concierge1=new Concierge(nom);
        this.concierge=concierge1;
    }

    //fonction qui creer un bavard à partir d'un nom
    public void creerBavard(String nom, ArrayList<String> list){
        Bavard bavard = new Bavard(nom,list);
        bavard.ajouterConcierge(this.concierge);
        this.concierge.ajouterBavard(bavard);

    }
    //Fonction qui associe un bavard a un concierge
    public void associeBavard(Bavard bavard){
        this.concierge.ajouterBavard(bavard);
    }

    //les getters
    public String getNom() {
        return this.nom;
    }
    public Concierge getConcierge() {
        return concierge;
    }

    //trouve un bavard pour son nom dans la liste de bavard du concierge
    public Bavard getBavardByName(String nomBavard) {
        for(Bavard bavard:concierge.getListBavard()){
            if(bavard.getNom().equals(nomBavard)){
                return bavard;
            }
        }
        return null;
    }
}


