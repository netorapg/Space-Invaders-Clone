package ifpr.paranavai.jogo.modelo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener, KeyListener{
    private static final int DELAY = 5;
    private Image background;
    private  Personagem personagem;
    private Timer timer;
    private static final int ALTURA_DA_JANELA = 700;
    private boolean podeAtirar = true;
    private List<Star> stars;

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);
        ImageIcon loading = new ImageIcon("src/ifpr/paranavai/jogo/recursos/Imagens/background.png");
        this.background = loading.getImage();
        personagem = new Personagem();
        personagem.carregar();
        addKeyListener(this);
        timer = new Timer(DELAY, this);
        timer.start();

        stars = new ArrayList<Star>();
        preencherEstrelas();
    }
    public void preencherEstrelas() {
        int quantidadeEstrelas = 100;
        int centroX = getWidth() / 2;
        int centroY = getHeight() / 2;
        int distanciaMaxima = 1000;
        for (int i = 0; i < quantidadeEstrelas; i++) {
            double angulo = Math.random() * 2 * Math.PI;
            int distancia = (int) (Math.random() * distanciaMaxima);
            int x = (int) (centroX + distancia * Math.cos(angulo));
            int y = (int) (centroY + distancia * Math.sin(angulo));
            Star star = new Star(x, y);
            stars.add(star);
        }
    }

    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.drawImage(background, 0, 0, null);
        graphics.drawImage(personagem.getImagem(), this.personagem.getPositionX(), this.personagem.getPositionY(), this);
        ArrayList<Tiro> tiros = personagem.getTiros();
        for (Star star: stars) {
            graphics.setColor(Color.WHITE);
            graphics.fillRect(star.getPosicaoX(), star.getPosicaoY(), 2, 2);
        }
        for (Tiro tiro : tiros) {
            tiro.carregar();
            graphics.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), this);
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        personagem.atualizar();

        for (Star star : stars) {
            star.setPosicaoY(star.getPosicaoY() + 1);
            if (star.getPosicaoY() >= getHeight()) {
                star.setPosicaoY(0);
            }
        }
        ArrayList<Tiro> tiros = personagem.getTiros();
        for (int i = 0; i < tiros.size(); i++) {
            if (tiros.get(i).getPosicaoEmY() > ALTURA_DA_JANELA) {
                tiros.remove(i);
            } else {
                tiros.get(i).atualizar();
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && podeAtirar) {
            personagem.dispararTiro();
            podeAtirar = false;
        } else {
            personagem.mover(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            podeAtirar = true;
        }
        personagem.parar(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
