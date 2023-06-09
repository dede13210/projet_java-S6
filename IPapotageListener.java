import java.awt.event.ActionListener;
import java.util.EventListener;

public interface IPapotageListener  {
    void newMessageRecu(PapotageEvent message);
    void createPapotage(String sujet, String corps);

    void newUserConnected(OnLineBavardEvent bavard);
    void newUserDisconnected(OfflineBavardEvent bavard);

}
