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

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BatimentGUI window = new BatimentGUI(new GestionnaireBatiments());
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

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
        frame.setBounds(100, 100, 300, 150);
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

        JLabel lblNomConcierge = new JLabel("Nom du Concierge :");
        frame.add(lblNomConcierge);

        textFieldNomConcierge = new JTextField();
        frame.add(textFieldNomConcierge);
        textFieldNomConcierge.setColumns(10);

        JButton btnAjouterConcierge = new JButton("Ajouter Concierge");
        frame.add(btnAjouterConcierge);

        btnAjouterConcierge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomConcierge = textFieldNomConcierge.getText();
                Batiment selectedBatiment = (Batiment) comboBoxBatiments.getSelectedItem();
                if (selectedBatiment != null) {
                    selectedBatiment.creerConcierge(nomConcierge);
                    ConciergeGUI conciergeGUI = new ConciergeGUI(selectedBatiment.getConcierge());
                    selectedBatiment.getConcierge().setConciergeGUI(conciergeGUI);
                    conciergeGUI.show();
                    textFieldNomConcierge.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Veuillez sélectionner un bâtiment.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCreerBavard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomBavard = textFieldNomBavard.getText();
                Batiment selectedBatiment = (Batiment) comboBoxBatiments.getSelectedItem();
                if (selectedBatiment != null) {
                    selectedBatiment.creerBavard(nomBavard);
                    BavardGUI bavardGUI = new BavardGUI(selectedBatiment.getBavardByName(nomBavard), selectedBatiment.getConcierge());
                    textFieldNomBavard.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Veuillez sélectionner un bâtiment.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
