import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                //on creer un batiment polytech
                Batiment polytech = new Batiment("polytech");

                //on creer un concierge puis on creer un second batiment polytech
                Concierge francois = new Concierge("francois");
                Batiment iae = new Batiment(francois, "iae");

                //on creer un gestionnaire de batiment
                GestionnaireBatiments gestionnaireBatiments = new GestionnaireBatiments();


                //on ajoute les deux batiments
                gestionnaireBatiments.ajouterBatiment(polytech);
                gestionnaireBatiments.ajouterBatiment(iae);

                //on creer et on rend visible un fenètre batimentGUI
                BatimentGUI window = new BatimentGUI(gestionnaireBatiments);
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }}
