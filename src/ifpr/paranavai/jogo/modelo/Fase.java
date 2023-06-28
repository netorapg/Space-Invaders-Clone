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
    private ArrayList<Inimigo> inimigos;
    private int temporizador = 0;
    private static final int QUANTIDADE_INIMIGOS = 40;

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);
        ImageIcon loading = new ImageIcon("src/ifpr/paranavai/jogo/recursos/Imagens/background.png");
        this.background = loading.getImage();
        personagem = new Personagem();
        personagem.carregar();

        this.inicializaInimigos();
        
        addKeyListener(this);
        timer = new Timer(DELAY, this);
        timer.start();

        stars = new ArrayList<Star>();
        preencherEstrelas();
    }
    public void inicializaInimigos(){
        inimigos = new ArrayList<Inimigo>();

        for (int i = 0; i < QUANTIDADE_INIMIGOS; i++) {
            int y = (int) (Math.random() * 8000 - 1024);
            int x = (int) (Math.random() * 650 + 30);
            Inimigo inimigo = new Inimigo(x, y);
            inimigos.add(inimigo);
        }
    }

    public void preencherEstrelas() {
        int quantidadeEstrelas = 100;
        int distanciaMaxima = 1000;
        int centroX = getWidth() / 2;
        int centroY = getHeight() / 2;
        
        for (int i = 0; i < quantidadeEstrelas; i++) {
            double angulo = Math.random() * 2 * Math.PI;
            int distancia = (int) (Math.random() * distanciaMaxima);
            int x = (int) (centroX + distancia * Math.cos(angulo));
            int y = (int) (centroY + distancia * Math.sin(angulo));
            Star star = new Star(x, y, 2);
            stars.add(star);
        }
    }

    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.drawImage(background, 0, 0, null);
        graphics.drawImage(personagem.getImagem(), this.personagem.getPositionX(), this.personagem.getPositionY(), this);
        ArrayList<Tiro> tiros = personagem.getTiros();
        ArrayList<SuperTiro> superTiros = personagem.getSuperTiros();
        for (Star star: stars) {
            if (star.getPosicaoY() >= getHeight()) {
                star.setPosicaoY((int) (Math.random() * getHeight()));
                star.setPosicaoX((int) (Math.random() * getWidth()));
            }
            graphics.setColor(Color.WHITE);
            graphics.fillOval(star.getPosicaoX(), star.getPosicaoY(), 2, 2);
        }
        for (Tiro tiro : tiros) {
            tiro.carregar();
            graphics.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), this);
        }

        for (SuperTiro superTiro : superTiros) {
            superTiro.carregar();
            graphics.drawImage(superTiro.getImagem(), superTiro.getPosicaoEmX(), superTiro.getPosicaoEmY(), this);
        }

        for (Inimigo inimigo : inimigos) {
            inimigo.carregar();
            graphics.drawImage(inimigo.getImagem(), inimigo.getPosicaoEmX(), inimigo.getPosicaoEmY(), this);
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        temporizador++;
        personagem.atualizar();

        for (Star star : stars) {
            star.setPosicaoY(star.getPosicaoY() + 2);
            if (star.getPosicaoY() > getHeight()) {
                star.setPosicaoY(-star.getTamanho());
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
        ArrayList<SuperTiro> superTiros = personagem.getSuperTiros();
        for (int i = 0; i < superTiros.size(); i++) {
            if (superTiros.get(i).getPosicaoEmY() > ALTURA_DA_JANELA) {
                superTiros.remove(i);
            } else {
                superTiros.get(i).atualizar();
            }
        }

        for (int i = 0; i < inimigos.size(); i++) {
            if (inimigos.get(i).getPosicaoEmY() > 800) {
                inimigos.remove(i);
            } else {
                inimigos.get(i).atualizar();
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
        if (e.getKeyCode() == KeyEvent.VK_SHIFT && podeAtirar && temporizador >= 500) {
            personagem.dispararSuperTiro();
            podeAtirar = false;
            temporizador = 0;
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

        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            podeAtirar = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}