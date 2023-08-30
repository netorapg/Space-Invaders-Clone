package ifpr.paranavai.jogo.modelo;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Fase extends JPanel implements ActionListener, KeyListener{
    public static final int DELAY = 5;
    public static final int ALTURA_DA_JANELA = 700;
    public static final int QUANTIDADE_INIMIGOS = 40;

    protected Image background;
    protected Personagem personagem;
    protected Timer timer;
    protected boolean podeAtirar = true;
    protected ArrayList<Inimigo> inimigos;
    protected int temporizador = 0;
    protected ArrayList<Star> stars;
    private boolean emJogo = false;
    private boolean menu = true;
    
    
    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(this);
        this.emJogo = true;
    }

    public abstract void inicializaInimigos();
    public abstract void preencherEstrelas();
    public abstract void verificarColisoes();

    public void salvarPontuacao(int pontuacao) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/ifpr/paranavai/jogo/modelo/pontuacao.txt", true))){
            writer.write(String.valueOf(pontuacao));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public abstract void keyTyped(KeyEvent e);

    @Override
    public abstract void keyPressed(KeyEvent e);
    
    @Override
    public abstract void keyReleased(KeyEvent e);

    @Override
    public abstract void actionPerformed(ActionEvent e);

}
