import java.util.EventObject;

public class OnLineBavardEvent extends EventObject {
    private String connectMessage = "vient de se connecter";
    private String bavard ;

    //constructeur prend en paramètre la source de l'event sous forme d'object et de string
    public OnLineBavardEvent(Object source,String bavard) {
        super(source);
        this.bavard = bavard;
    }

    //renvoie l'attribut bavard
    public String getBavard() {
        return bavard;
    }

    //renvoie l'event sous forme de string
    @Override
    public String toString() {
        return bavard +' '+ connectMessage;
    }
}
