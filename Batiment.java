public class Batiment {
    private Concierge concierge;
    private String nom;

    public Batiment(String nom){
        this.nom=nom;
    }
    public void creerConcierge(Concierge concierge){
        this.concierge=concierge;
    }
}


