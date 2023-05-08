import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BatimentGUI {
    JFrame frame;
    private JTextField textFieldNomBavard;
    private JTextField textFieldNomConcierge;

    private GestionnaireBatiments gestionnaireBatiments;
    private JComboBox<String> comboBoxBatiments;



    private void updateBatimentList() {
        comboBoxBatiments.removeAllItems();
        for (Batiment batiment : gestionnaireBatiments.getBatiments()) {
            comboBoxBatiments.addItem(batiment.getNom());
        }
    }

    public BatimentGUI(GestionnaireBatiments gestionnaireBatiments) {
        this.gestionnaireBatiments = gestionnaireBatiments;
        initialize();
    }



    private void initialize() {
        frame = new JFrame();
        frame.setBackground(Color.magenta); // Ajout de la couleur de fond
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        frame.getContentPane().setBackground(Color.magenta);


        JLabel lblNomBavard = new JLabel("Nom du Bavard :");
        frame.getContentPane().add(lblNomBavard);

        textFieldNomBavard = new JTextField();
        frame.getContentPane().add(textFieldNomBavard);
        textFieldNomBavard.setColumns(10);
        textFieldNomBavard.setBackground(Color.pink); // Ajout de la couleur de fond du champ de texte

        JLabel lblSelectionBatiment = new JLabel("Sélectionner un bâtiment :");
        frame.getContentPane().add(lblSelectionBatiment);

        comboBoxBatiments = new JComboBox<>();
        frame.getContentPane().add(comboBoxBatiments);
        comboBoxBatiments.setBackground(Color.pink); // Ajout de la couleur de fond du menu déroulant

        updateBatimentList();

        JButton btnCreerBavard = new JButton("Créer et connecter Bavard");
        frame.getContentPane().add(btnCreerBavard);
        btnCreerBavard.setBackground(Color.pink); // Ajout de la couleur de fond du bouton
        btnCreerBavard.setForeground(Color.red); // Ajout de la couleur du texte du bouton


        btnCreerBavard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomBavard = textFieldNomBavard.getText();
                Batiment selectedBatiment = gestionnaireBatiments.getBatimentByName(comboBoxBatiments.getSelectedItem().toString());
                Boolean checkNom = true;
                for(Bavard bavard:selectedBatiment.concierge.getListBavard()){
                    if (bavard.getNom().equals(nomBavard)){
                        checkNom = false;
                    }
                }
                if (selectedBatiment != null && checkNom) {
                    OnLineBavardEvent online = new OnLineBavardEvent(this,nomBavard);
                    selectedBatiment.concierge.getConciergeGUI().show();
                    selectedBatiment.creerBavard(nomBavard);
                    BavardGUI bavardGUI = new BavardGUI(selectedBatiment.getBavardByName(nomBavard), selectedBatiment.getConcierge());
                    bavardGUI.show();
                    for(Bavard bavard : selectedBatiment.concierge.getListBavard()){
                        bavard.newUserConnected(online);
                    }
                    textFieldNomBavard.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Veuillez utiliser un autre nom.", "nom déjà éxistant", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
