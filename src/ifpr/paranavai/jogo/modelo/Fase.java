package ifpr.paranavai.jogo.modelo;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public abstract class Fase extends JPanel implements ActionListener, KeyListener{
    public static final int DELAY = 5;
    public static final int ALTURA_DA_JANELA = 700;
    public static final int QUANTIDADE_INIMIGOS = 10;

    protected Image background;
    protected Personagem personagem;
    protected Timer timer;
    protected boolean podeAtirar = true;
    protected ArrayList<Inimigo> inimigos;
    protected int temporizador = 0;
    protected ArrayList<Star> stars;
    

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(this);
    }

    public abstract void inicializaInimigos();
    public abstract void preencherEstrelas();

    @Override
    public abstract void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public abstract void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public abstract void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public abstract void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }
}
