package ifpr.paranavai.jogo.modelo;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Fase extends JPanel{
    private Image background;
    private  Personagem personagem;
    public Fase() {
            this.personagem = new Personagem();
            ImageIcon loading = new ImageIcon("src/ifpr/paranavai/jogo/recursos/Imagens/background.jpg");
            this.background = loading.getImage();
            personagem.carregar();
    }

    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.drawImage(this.background, 0, 0, null);
        graphics.drawImage(personagem.getImagem(), this.personagem.getPositionX(), this.personagem.getPositionY(), this);
        g.dispose();
    }
}