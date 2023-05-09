import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class BatimentGUI {
    //On créer les objets de bases de notre code
    JFrame frame;
    private JTextField textFieldNomBavard;
    private GestionnaireBatiments gestionnaireBatiments;
    private JComboBox<String> comboBoxBatiments;


    //fonction qui permet de mettre à jour la liste des batiments dans la liste déroulante
    private void updateBatimentList() {
        comboBoxBatiments.removeAllItems();
        for (Batiment batiment : gestionnaireBatiments.getBatiments()) {
            comboBoxBatiments.addItem(batiment.getNom());
        }
    }

    //constructeur qui permet d'initialiser le gestionnaire de batiments
    public BatimentGUI(GestionnaireBatiments gestionnaireBatiments) {
        this.gestionnaireBatiments = gestionnaireBatiments;
        initialize();
    }


    //fonction pour initialiser l'interface graphique
    private void initialize() {
        //je créer quelque thème pour les bavards
        ArrayList<String> themes = new ArrayList<>(Arrays.asList("Sport", "Devoirs", "Nature"));


        //création de la fenêtre principale
        frame = new JFrame();
        frame.setBackground(Color.magenta);
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        frame.getContentPane().setBackground(Color.magenta);

        // création de la partie liée à la création du bavard

        //on demande le nom du bavard
        JLabel lblNomBavard = new JLabel("Nom du Bavard :");
        frame.getContentPane().add(lblNomBavard);

        //on créer le champ où l'on peut écrire le nom
        textFieldNomBavard = new JTextField();
        frame.getContentPane().add(textFieldNomBavard);
        textFieldNomBavard.setColumns(10);
        textFieldNomBavard.setBackground(Color.pink);

        //on créer le texte qui demande le batiment sélectionné
        JLabel lblSelectionBatiment = new JLabel("Sélectionner un bâtiment :");
        frame.getContentPane().add(lblSelectionBatiment);

        // on créer la liste déroulante des batiments
        comboBoxBatiments = new JComboBox<>();
        frame.getContentPane().add(comboBoxBatiments);
        comboBoxBatiments.setBackground(Color.pink);

        //on initialise la liste
        updateBatimentList();


        // Créer une liste de JCheckBox pour chaque thème
        ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
        for (String theme : themes) {
            JCheckBox checkBox = new JCheckBox(theme);
            checkBoxes.add(checkBox);
            frame.getContentPane().add(checkBox);
            checkBox.setBackground(Color.pink);
        }


        //on créer le bouton qui permet de valider l'action de création
        JButton btnCreerBavard = new JButton("Créer et connecter Bavard");
        frame.getContentPane().add(btnCreerBavard);
        btnCreerBavard.setBackground(Color.pink);
        btnCreerBavard.setForeground(Color.red);

        //on créer l'actionListener qui réalise l'action voulue en créant un bavard
        btnCreerBavard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //on récupère le nom du batiment
                String nomBavard = textFieldNomBavard.getText();
                Batiment selectedBatiment = gestionnaireBatiments.getBatimentByName(comboBoxBatiments.getSelectedItem().toString());

                //remplie une list les thèmes sélectionnés
                ArrayList<String> selectedThemes = new ArrayList<>();
                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        selectedThemes.add(checkBox.getText());
                    }
                }

                //on vérifie que le nom du bavard créé n'existe pas
                Boolean checkNom = true;
                for (Bavard bavard : selectedBatiment.concierge.getListBavard()) {
                    if (bavard.getNom().equals(nomBavard)) {
                        checkNom = false;
                    }
                }
                //on créer le bavard
                if (selectedBatiment != null && checkNom) {
                    OnLineBavardEvent online = new OnLineBavardEvent(this, nomBavard);
                    selectedBatiment.concierge.getConciergeGUI().show();
                    selectedBatiment.creerBavard(nomBavard,selectedThemes);
                    selectedBatiment.getBavardByName(nomBavard).themeEnCommun(selectedBatiment.getConcierge().getListBavard());
                    BavardGUI bavardGUI = new BavardGUI(selectedBatiment.getBavardByName(nomBavard), selectedBatiment.getConcierge());
                    bavardGUI.show();
                    for (Bavard bavard : selectedBatiment.concierge.getListBavard()) {
                        bavard.newUserConnected(online);
                    }
                    textFieldNomBavard.setText("");

                }
                //on ne créer pas le bavard car il existe déjà
                else {
                    JOptionPane.showMessageDialog(frame, "Veuillez utiliser un autre nom.", "nom déjà éxistant", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

}
}
