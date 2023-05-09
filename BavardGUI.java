import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.EventObject;

public class BavardGUI {
    private JFrame frame;
    private JTextField textFieldSujet;
    private JTextField textFieldCorps;
    private JList<String> listMessages;
    private DefaultListModel<String> listModel;

    private JList<String> listConnectedUsers;
    private DefaultListModel<String> connectedUsersModel;

    private Bavard bavard;

    public BavardGUI(Bavard bavard, Concierge concierge) {
        this.bavard = bavard;
        initialize();
    }

    private void initialize() {
        bavard.addBavardGUI(this);
        frame = new JFrame("Bavard - " + bavard.getNom());
        frame.setBounds(100, 100, 550, 400);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBackground(Color.magenta);
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        frame.setBackground(Color.magenta);

        JLabel lblSujet = new JLabel("Sujet:");
        panel.add(lblSujet);

        textFieldSujet = new JTextField();
        textFieldSujet.setBackground(Color.pink);
        panel.add(textFieldSujet);
        textFieldSujet.setColumns(10);

        JLabel lblCorps = new JLabel("Corps:");
        panel.add(lblCorps);

        textFieldCorps = new JTextField();
        textFieldCorps.setBackground(Color.pink);
        panel.add(textFieldCorps);
        textFieldCorps.setColumns(10);

        JButton btnEnvoyer = new JButton("Envoyer");
        btnEnvoyer.setBackground(Color.pink);
        btnEnvoyer.setForeground(Color.red);
        panel.add(btnEnvoyer);


        listModel = new DefaultListModel<>();
        listMessages = new JList<>(listModel);
        listMessages.setBackground(Color.pink);
        JScrollPane scrollPane = new JScrollPane(listMessages);
        scrollPane.setBackground(Color.magenta);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Créez un modèle pour stocker les utilisateurs connectés
        connectedUsersModel = new DefaultListModel<>();
        connectedUsersModel.addElement("Liste des bavards connectés");
        for(Bavard bavard1:this.bavard.getListConcierge().get(0).getListBavard()){
            if(!bavard1.equals(this.bavard)){
                connectedUsersModel.addElement(bavard1.getNom());
            }

        }

        listConnectedUsers = new JList<>(connectedUsersModel);
        listConnectedUsers.setBackground(Color.pink);
        JScrollPane connectedUsersScrollPane = new JScrollPane(listConnectedUsers);
        connectedUsersScrollPane.setBackground(Color.magenta);
        connectedUsersScrollPane.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        frame.getContentPane().add(connectedUsersScrollPane, BorderLayout.EAST);
        btnEnvoyer.addActionListener(new ActionListener() {
                                         public void actionPerformed(ActionEvent e) {
                                             String sujet = textFieldSujet.getText();
                                             String corps = textFieldCorps.getText();
                                             bavard.createPapotage(sujet, corps);
                                             textFieldSujet.setText("");
                                             textFieldCorps.setText("");
                                         }

                                     }

        );
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                bavard.getListConcierge().get(0).newUserDisconnected(new OfflineBavardEvent(BavardGUI.this, bavard.getNom()));
                frame.dispose();
            }
        });

    }
    public void messageListener(PapotageEvent message){
        listModel.addElement(message.getNomBavard().toString().toUpperCase()+'-'+message.getSujet() + " - " + message.getCorps()+ "...");
    }
    public void connectListener(OnLineBavardEvent connect){
        listModel.addElement(connect.toString());
        connectedUsersModel.addElement(connect.getBavard()); // Ajoutez le nom d'utilisateur à la liste des utilisateurs connectés
    }

    public void disconnectListener(OfflineBavardEvent disconnect){
        listModel.addElement(disconnect.toString());
        connectedUsersModel.removeElement(disconnect.getBavard()); // Supprimez le nom d'utilisateur de la liste des utilisateurs connectés
    }

    public void show() {
        frame.setVisible(true);
    }
}