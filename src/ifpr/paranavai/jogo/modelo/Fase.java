package ifpr.paranavai.jogo.modelo;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;


public class Fase{
    private Image background;
    public Fase() {
            ImageIcon loading = new ImageIcon("src/ifpr/paranavai/jogo/recursos/Imagens/background.jpg");
            this.background = loading.getImage();
    }
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;

        
    }

}
