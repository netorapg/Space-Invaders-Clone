package ifpr.paranavai.jogo.modelo;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener, KeyListener{
    private static final int DELAY = 5;
    private Image background;
    private  Personagem personagem;
    private Timer timer;
    private static final int LARGURA_DA_JANELA = 938;
    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);
        ImageIcon loading = new ImageIcon("src/ifpr/paranavai/jogo/recursos/Imagens/background.png");
        this.background = loading.getImage();
        this.personagem = new Personagem();
        personagem.carregar();
        addKeyListener(this);
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.drawImage(this.background, 0, 0, null);
        graphics.drawImage(personagem.getImagem(), this.personagem.getPositionX(), this.personagem.getPositionY(), this);
        ArrayList<Tiro> tiros = personagem.getTiros();
        for (Tiro tiro : tiros) {
            tiro.carregar();
            graphics.drawImage(tiro.getImagem(), tiro.getPositionInX(), tiro.getPositionInY(), this);
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        personagem.atualizar();
        ArrayList<Tiro> tiros = personagem.getTiros();
        for (Tiro tiro : tiros) {
            if (tiro.getPositionInX() > LARGURA_DA_JANELA) {
                tiros.remove(tiro);
            }
            else if (tiros.indexOf(tiro) < tiros.size())
                tiro.atualizar(); 
        }
        personagem.setTiros(tiros);
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            personagem.atirar();
        }
        else
            personagem.mover(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        personagem.parar(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}