

public class Main {
    public static void main(String[] args) {
        Bavard arthur = new Bavard("arthur");
        Bavard adam = new Bavard("adam");
        Concierge jean = new Concierge("jean");
        arthur.ajouterConcierge(jean);
        adam.ajouterConcierge(jean);
        jean.ajouterBavard(arthur);
        jean.ajouterBavard(adam);
        arthur.createPapotage("bouton","j'ai des bouton au visage");
}}
