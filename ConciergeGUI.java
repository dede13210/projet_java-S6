import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConciergeGUI {
    private JFrame frame;
    private JList<String> listMessages;
    private DefaultListModel<String> listModel;

    private Concierge concierge;

    public ConciergeGUI(Concierge concierge) {
        this.concierge = concierge;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Concierge - " + concierge.getNom());
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        listModel = new DefaultListModel<>();
        listMessages = new JList<>(listModel);
        listMessages.setBackground(Color.pink);
        JScrollPane scrollPane = new JScrollPane(listMessages);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public void show() {
        frame.setVisible(true);
    }

    public void addMessage(PapotageEvent message) {
        listModel.addElement(message.getNomBavard().toString()+'-'+message.getSujet() + " - " + message.getCorps()+ "...");
    }
}
