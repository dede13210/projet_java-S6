import java.util.EventObject;

public class OfflineBavardEvent extends EventObject {
    private String connectMessage = "vient de se déconnecter";
    private String bavard ;

    public OfflineBavardEvent(Object source,String bavard) {
        super(source);
        this.bavard = bavard;
    }

    public String getBavard() {
        return bavard;
    }

    @Override
    public String toString() {
        return bavard +' '+ connectMessage;
    }
}