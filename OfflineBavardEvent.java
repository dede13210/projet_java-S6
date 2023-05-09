import java.util.EventObject;

public class OfflineBavardEvent extends EventObject {
    private String connectMessage = "vient de se déconnecter";
    private String bavard ;

    //Créer un évènement de déconnexion
    public OfflineBavardEvent(Object source,String bavard) {
        super(source);
        this.bavard = bavard;
    }

    //renvoie le nom du bavard qui se déconnecte
    public String getBavard() {
        return bavard;
    }
    //permet d'afficher le message de déconnexion
    @Override
    public String toString() {
        return bavard +' '+ connectMessage;
    }
}