package ifpr.paranavai.jogo.modelo;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener, KeyListener{
    private static final int DELAY = 5;
    private Image background;
    private  Personagem personagem;
    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);
            this.personagem = new Personagem();
            ImageIcon loading = new ImageIcon("src/ifpr/paranavai/jogo/recursos/Imagens/background.jpg");
            this.background = loading.getImage();
            personagem.carregar();
            addKeyListener(this);
            Timer timer = new Timer(DELAY, this);
            timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.drawImage(this.background, 0, 0, null);
        graphics.drawImage(personagem.getImagem(), this.personagem.getPositionX(), this.personagem.getPositionY(), this);
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        personagem.atualizar();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        personagem.mover(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        personagem.parar(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }
}