import java.util.EventObject;

public class OnLineBavardEvent extends EventObject {
    private String connectMessage = "vient de se conecter";
    private String bavard ;

    public OnLineBavardEvent(Object source,String bavard) {
        super(source);
        this.bavard = bavard;
    }

    @Override
    public String toString() {
        return bavard +' '+ connectMessage;
    }
}
