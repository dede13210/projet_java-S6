import java.util.EventObject;

public class PapotageEvent extends EventObject {
    private String nomBavard;
    private String sujet;
    private String corps;


    //constructeur prend en paramètre la source sous forme de Bavard et de String, le sujet et le corps sous forme de string
    public PapotageEvent(Bavard source,String nomBavard, String sujet, String corps) {
        super(source);
        this.nomBavard=nomBavard;
        this.sujet = sujet;
        this.corps = corps;
    }

    //les getters
    public String getNomBavard() {
        return nomBavard;
    }

    public String getSujet() {
        return sujet;
    }

    public String getCorps() {
        return corps;
    }

    //renvoie le message sous forme de string
    @Override
    public String toString() {
        return "Message de "+this.nomBavard+" "+"sujet :"+ sujet + '\n' +
                ", corps : " + corps ;
    }
}
