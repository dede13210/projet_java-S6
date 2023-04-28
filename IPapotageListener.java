import java.util.EventListener;

public interface IPapotageListener extends EventListener {
    void newMessageRecu(PapotageEvent message);
}
