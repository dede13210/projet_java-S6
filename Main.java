import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Batiment polytech = new Batiment("polytech");
                Batiment iae = new Batiment("iae");
                GestionnaireBatiments gestionnaireBatiments = new GestionnaireBatiments();
                // You can add Batiments to the GestionnaireBatiments here, if needed.
                // For example: gestionnaireBatiments.ajouterBatiment(new Batiment("Mon Batiment"));
                gestionnaireBatiments.ajouterBatiment(polytech);
                gestionnaireBatiments.ajouterBatiment(iae);
                BatimentGUI window = new BatimentGUI(gestionnaireBatiments);
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }}
