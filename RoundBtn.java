
import java.awt.*;
import javax.swing.border.Border;

//classe qui permet de créer des boutons de forme ronde de rayon r
class RoundBtn implements Border
{
    private int r;
    RoundBtn(int r) {
        this.r = r;
    }
    //on spécifie les dimensions de l'objet
    public Insets getBorderInsets(Component c) {
        return new Insets(this.r+1, this.r+1, this.r+2, this.r);
    }
    //on rend les bordures opaques
    public boolean isBorderOpaque() {
        return true;
    }
    //on arrondit les bords
    public void paintBorder(Component c, Graphics g, int x, int y,
                            int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, r, r);
    }
}