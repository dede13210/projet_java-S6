public class Batiment {
    Concierge concierge;
    private String nom;

    public Batiment(String nom){
        this.nom=nom;
        creerConcierge("manuel");
    }
    public Batiment(Concierge concierge, String nom){

        this.concierge= concierge;
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

    public String getNom() {
        return this.nom;
    }
    public Concierge getConcierge() {
        return concierge;
    }

    public Bavard getBavardByName(String nomBavard) {
        for(Bavard bavard:concierge.getListBavard()){
            if(bavard.getNom().equals(nomBavard)){
                return bavard;
            }
        }
        return null;
    }
}


