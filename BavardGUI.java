import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BavardGUI {
    private JFrame frame;
    private JTextField textFieldSujet;
    private JTextField textFieldCorps;
    private JList<String> listMessages;
    private DefaultListModel<String> listModel;

    private JList<String> listConnectedUsers;
    private DefaultListModel<String> connectedUsersModel;

    private JTextField textFieldIgnore;

    private Bavard bavard;

    //constructeur prend en parametre un bavard et un concierge
    public BavardGUI(Bavard bavard, Concierge concierge) {
        this.bavard = bavard;
        initialize();
    }

    //initialise la fenetre
    private void initialize() {
        //ajoute la fenetre en attribut du bavard
        bavard.addBavardGUI(this);

        //creer une nouvelle Jframe
        frame = new JFrame("Bavard - " + bavard.getNom());
        frame.setBounds(100, 100, 800, 400);

        //on fait en sorte que la fenetre ne se ferme pas lorsqu'on appuie sur la croix
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        //on creer un panel que l'on met de couleur magenta puis on l'ajoute à la frame
        JPanel panel = new JPanel();
        panel.setBackground(Color.magenta);
        frame.getContentPane().add(panel, BorderLayout.NORTH);

        //on met le fond de la frame en magenta
        frame.setBackground(Color.magenta);

        //on creer un jlabel pour le sujet et on l'ajoute au panel
        JLabel lblSujet = new JLabel("Sujet:");
        panel.add(lblSujet);

        //on creer un text field pour le sujet et on l'ajoute au panel
        textFieldSujet = new JTextField();
        textFieldSujet.setBackground(Color.pink);
        panel.add(textFieldSujet);
        textFieldSujet.setColumns(10);


        //on creer un jlabel pour le corps et on l'ajoute au panel
        JLabel lblCorps = new JLabel("Corps:");
        panel.add(lblCorps);

        //on creer un text field pour le corps et on l'ajoute au panel
        textFieldCorps = new JTextField();
        textFieldCorps.setBackground(Color.pink);
        panel.add(textFieldCorps);
        textFieldCorps.setColumns(10);

        //on creer le bouton envoyer et on l'ajoute au panel
        JButton btnEnvoyer = new JButton("Envoyer");
        btnEnvoyer.setBackground(Color.pink);
        btnEnvoyer.setForeground(Color.red);
        panel.add(btnEnvoyer);

        //on créer un jlabel pour le champ ignorer
        JLabel lblIgnore = new JLabel("Bavard à ignorer :");
        panel.add(lblIgnore);

        //on créer un champ de texte afin d'écrire le nom du bavard à ignorer
        textFieldIgnore = new JTextField();
        textFieldIgnore.setBackground(Color.pink);
        panel.add(textFieldIgnore);
        textFieldIgnore.setColumns(10);

        //on creer un bouton ignorer qui permet d'ignorer les message de quelqu'un
        JButton btnIgnorer = new JButton("Ignorer");
        btnIgnorer.setBackground(Color.pink);
        btnIgnorer.setForeground(Color.blue);
        btnIgnorer.setBorder(new RoundBtn(5));
        panel.add(btnIgnorer);

        //on creer un bouton ignorer qui permet d'écouter les message de quelqu'un
        JButton btnEcouter = new JButton("Ecouter");
        btnEcouter.setBackground(Color.pink);
        btnEcouter.setForeground(Color.green);
        btnEcouter.setBorder(new RoundBtn(5));
        panel.add(btnEcouter);

        //on creer un modèle pour affiche les papotages
        listModel = new DefaultListModel<>();
        listMessages = new JList<>(listModel);
        listMessages.setBackground(Color.pink);
        JScrollPane scrollPane = new JScrollPane(listMessages);
        scrollPane.setBackground(Color.magenta);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Créez un modèle pour stocker les utilisateurs connectés
        connectedUsersModel = new DefaultListModel<>();
        //ajoute les bavard au modele
        connectedUsersModel.addElement("Liste des bavards connectés");
        for(Bavard bavard1:this.bavard.getListConcierge().get(0).getListBavard()){
            if(!bavard1.equals(this.bavard)){
                connectedUsersModel.addElement(bavard1.getNom());
            }

        }
        //creer une jlist du modele des bavard connecte
        listConnectedUsers = new JList<>(connectedUsersModel);
        listConnectedUsers.setBackground(Color.pink);
        JScrollPane connectedUsersScrollPane = new JScrollPane(listConnectedUsers);
        connectedUsersScrollPane.setBackground(Color.magenta);
        connectedUsersScrollPane.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        frame.getContentPane().add(connectedUsersScrollPane, BorderLayout.EAST);

        //récupere l'évènement du bouton envoyer, ce qui creer un papotage
        btnEnvoyer.addActionListener(new ActionListener() {
                                         public void actionPerformed(ActionEvent e) {
                                             String sujet = textFieldSujet.getText();
                                             String corps = textFieldCorps.getText();
                                             bavard. createPapotage(sujet, corps);
                                             textFieldSujet.setText("");
                                             textFieldCorps.setText("");
                                         }

                                     }

        );

        //ajoute le bavard à la liste des personnes ignorer
        btnIgnorer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(textFieldIgnore.getText() != null){
                    bavard.addListBavardIgnorer(bavard.getListConcierge().get(0).getBavardByName(textFieldIgnore.getText()));
                }

            }
        });
        btnEcouter.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(textFieldIgnore.getText() != null){
                bavard.removeListBavardIgnorer(bavard.getListConcierge().get(0).getBavardByName(textFieldIgnore.getText()));
            }

        }
    });

        //recupere l'évènement de la fermeture de la fenetre et envoie l'evenement d'un bavard deconnecte
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                bavard.getListConcierge().get(0).newUserDisconnected(new OfflineBavardEvent(BavardGUI.this, bavard.getNom()));
                frame.dispose();
            }
        });

    }

    //reçois un papotageEvent et l'affiche
    public void messageListener(PapotageEvent message){
        if(bavard.isNotInListIgnore(message.getNomBavard())){
            listModel.addElement(message.getNomBavard().toUpperCase()+'-'+message.getSujet() + " - " + message.getCorps()+ "...");
    }}

    //reçois un onlineBavardEvent
    public void connectListener(OnLineBavardEvent connect){
        listModel.addElement(connect.toString());
        connectedUsersModel.addElement(connect.getBavard()); // Ajoutez le nom d'utilisateur à la liste des utilisateurs connectés
    }

    //reçois un onlineBavardEvent
    public void disconnectListener(OfflineBavardEvent disconnect){
        listModel.addElement(disconnect.toString());
        connectedUsersModel.removeElement(disconnect.getBavard()); // Supprimez le nom d'utilisateur de la liste des utilisateurs connectés
    }

    //fonction qui rend la fenetre visible
    public void show() {
        frame.setVisible(true);
    }
}