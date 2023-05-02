import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BavardGUI {
    private JFrame frame;
    private JTextField textFieldSujet;
    private JTextField textFieldCorps;
    private JList<String> listMessages;
    private DefaultListModel<String> listModel;

    private Bavard bavard;

    public BavardGUI(Bavard bavard) {
        this.bavard = bavard;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Bavard - " + bavard.getNom());
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);

        JLabel lblSujet = new JLabel("Sujet:");
        panel.add(lblSujet);

        textFieldSujet = new JTextField();
        panel.add(textFieldSujet);
        textFieldSujet.setColumns(10);

        JLabel lblCorps = new JLabel("Corps:");
        panel.add(lblCorps);

        textFieldCorps = new JTextField();
        panel.add(textFieldCorps);
        textFieldCorps.setColumns(10);

        JButton btnEnvoyer = new JButton("Envoyer");
        panel.add(btnEnvoyer);

        listModel = new DefaultListModel<>();
        listMessages = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listMessages);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        btnEnvoyer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sujet = textFieldSujet.getText();
                String corps = textFieldCorps.getText();
                bavard.createPapotage(sujet, corps);
                listModel.addElement(sujet + " - " + corps.substring(0, Math.min(corps.length(), 20)) + "...");
                textFieldSujet.setText("");
                textFieldCorps.setText("");
            }
        });
    }

    public void show() {
        frame.setVisible(true);
    }
}

