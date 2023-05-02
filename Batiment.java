public class Batiment {
    private Concierge concierge;
    private String nom;

    public Batiment(String nom){
        this.nom=nom;
    }
    public void creerConcierge(String nom){
        Concierge concierge1=new Concierge(nom);
        this.concierge=concierge1;
    }
    public void creerBavard(String nom){
        Bavard bavard = new Bavard(nom);
        bavard.ajouterConcierge(this.concierge);
        this.concierge.ajouterBavard(bavard);
    }
    public void associeBavard(Bavard bavard){
        this.concierge.ajouterBavard(bavard);
    }
}


