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
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblNomBavard = new JLabel("Nom du Bavard :");
        frame.getContentPane().add(lblNomBavard);

        textFieldNomBavard = new JTextField();
        frame.getContentPane().add(textFieldNomBavard);
        textFieldNomBavard.setColumns(10);

        JLabel lblSelectionBatiment = new JLabel("Sélectionner un bâtiment :");
        frame.getContentPane().add(lblSelectionBatiment);

        comboBoxBatiments = new JComboBox<>();
        frame.getContentPane().add(comboBoxBatiments);

        updateBatimentList();

        JButton btnCreerBavard = new JButton("Créer et connecter Bavard");
        frame.getContentPane().add(btnCreerBavard);

        btnCreerBavard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomBavard = textFieldNomBavard.getText();
                Batiment selectedBatiment = gestionnaireBatiments.getBatimentByName(comboBoxBatiments.getSelectedItem().toString());
                if (selectedBatiment != null) {
                    OnLineBavardEvent online = new OnLineBavardEvent(this,nomBavard);
                    selectedBatiment.concierge.getConciergeGUI().show();
                    selectedBatiment.creerBavard(nomBavard,selectedBatiment.concierge.getListBavardConnecte());
                    BavardGUI bavardGUI = new BavardGUI(selectedBatiment.getBavardByName(nomBavard), selectedBatiment.getConcierge());
                    bavardGUI.show();
                    for(Bavard bavard : selectedBatiment.concierge.getListBavard()){
                        bavard.newUserConnected(online);
                    }
                    textFieldNomBavard.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Veuillez sélectionner un bâtiment.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
