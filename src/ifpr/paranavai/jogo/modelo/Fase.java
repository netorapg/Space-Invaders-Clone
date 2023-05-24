package ifpr.paranavai.jogo.modelo;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Fase extends JPanel{
    private Image background;
    public Fase() {
            ImageIcon loading = new ImageIcon("src/ifpr/paranavai/jogo/recursos/Imagens/background.jpg");
            this.background = loading.getImage();
    }

    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.drawImage(this.background, 0, 0, null);
    }
}