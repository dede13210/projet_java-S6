import java.util.EventObject;

public class PapotageEvent extends EventObject {
    private String nomBavard;
    private String sujet;
    private String corps;



    public PapotageEvent(Bavard source,String nomBavard, String sujet, String corps) {
        super(source);
        this.nomBavard=nomBavard;
        this.sujet = sujet;
        this.corps = corps;
    }

    public String getNomBavard() {
        return nomBavard;
    }

    public String getSujet() {
        return sujet;
    }

    public String getCorps() {
        return corps;
    }


    @Override
    public String toString() {
        return "Message de "+this.nomBavard+" "+"sujet :"+ sujet + '\n' +
                ", corps : " + corps ;
    }
}
