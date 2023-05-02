import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BatimentGUI {
    JFrame frame;
    private JTextField textFieldNomBavard;

    private Batiment batiment;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BatimentGUI window = new BatimentGUI(new Batiment("Mon Batiment"));
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public BatimentGUI(Batiment batiment) {
        this.batiment = batiment;
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

        JButton btnCreerBavard = new JButton("Créer et connecter Bavard");
        frame.getContentPane().add(btnCreerBavard);

        btnCreerBavard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomBavard = textFieldNomBavard.getText();
                batiment.creerBavard(nomBavard);
                BavardGUI bavardGUI = new BavardGUI(batiment.concierge.getBavardByName(nomBavard));
                bavardGUI.show();
            }
        });
    }
}

