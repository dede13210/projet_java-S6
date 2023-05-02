import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionnaireBatimentsGUI {
    JFrame frame;
    private JTextField textFieldNomBatiment;

    private GestionnaireBatiments gestionnaireBatiments;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GestionnaireBatiments gestionnaire = new GestionnaireBatiments();
                GestionnaireBatimentsGUI window = new GestionnaireBatimentsGUI(gestionnaire);
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public GestionnaireBatimentsGUI(GestionnaireBatiments gestionnaireBatiments) {
        this.gestionnaireBatiments = gestionnaireBatiments;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblNomBatiment = new JLabel("Nom du Bâtiment :");
        frame.getContentPane().add(lblNomBatiment);

        textFieldNomBatiment = new JTextField();
        frame.getContentPane().add(textFieldNomBatiment);
        textFieldNomBatiment.setColumns(10);

        JButton btnCreerBatiment = new JButton("Créer Bâtiment");
        frame.getContentPane().add(btnCreerBatiment);

        btnCreerBatiment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomBatiment = textFieldNomBatiment.getText();
                Batiment batiment = new Batiment(nomBatiment);
                gestionnaireBatiments.ajouterBatiment(batiment);
                BatimentGUI batimentGUI = new BatimentGUI(gestionnaireBatiments);
                batimentGUI.frame.setVisible(true);
                textFieldNomBatiment.setText("");
            }
        });
    }
}
