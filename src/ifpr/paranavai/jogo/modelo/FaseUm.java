package ifpr.paranavai.jogo.modelo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.Timer;

//import org.lwjgl.glfw.GLFW;
//import org.lwjgl.glfw.GLFWGamepadState;

public class FaseUm extends Fase{
    private  Personagem personagem;
    private Timer timer;
    private static final int ALTURA_DA_JANELA = 700;
    private boolean podeAtirar = true;
    private List<Star> stars;
    private ArrayList<Inimigo> inimigos;
    private int temporizador = 0;
    private int QUANTIDADE_INIMIGOS = 10;
    private boolean emJogo = true;

    public FaseUm() {
        super();
        ImageIcon loading = new ImageIcon("src/ifpr/paranavai/jogo/recursos/Imagens/background.png");
        this.background = loading.getImage();
        personagem = new Personagem();
        personagem.carregar();
        this.inicializaInimigos();
        //int present = glfwJoystickPresent(GLFW_JOYSTICK_1);
        timer = new Timer(DELAY, this);
        timer.start();

        stars = new ArrayList<Star>();
        preencherEstrelas();
    }
    public void inicializaInimigos(){
        inimigos = new ArrayList<Inimigo>();

        for (int i = 0; i < QUANTIDADE_INIMIGOS; i++) {
            int y = (int) (Math.random() * 800 - 1024);
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

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        if (emJogo){
             graphics.drawImage(background, 0, 0, null);
        graphics.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), this.personagem.getPosicaoEmY(), this);
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
        } else {
            ImageIcon fimDeJogo = new ImageIcon("/home/netorapg/Projetos/Space Invaders Clone/src/ifpr/paranavai/jogo/recursos/Imagens/game-over.png");
            graphics.drawImage(fimDeJogo.getImage(), 0, 0, null);
        }
       
        graphics.dispose();
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
    for (int i = tiros.size() - 1; i >= 0; i--) {
        Tiro tiro = tiros.get(i);
        if (tiro.getPosicaoEmY() >= ALTURA_DA_JANELA || !tiro.getVisivel()) {
            tiros.remove(tiro);
        } else {
            tiro.atualizar();
        }
    }

    ArrayList<SuperTiro> superTiros = personagem.getSuperTiros();
    for (int i = superTiros.size() - 1; i >= 0; i--) {
        SuperTiro superTiro = superTiros.get(i);
        if (superTiro.getPosicaoEmY() >= ALTURA_DA_JANELA || !superTiro.getVisivel()) {
            superTiros.remove(superTiro);
        } else {
            superTiro.atualizar();
        }
    }

    for (int i = 0; i < inimigos.size(); i++) {
        Inimigo inimigo = this.inimigos.get(i);
        if (inimigo.getPosicaoEmY() > 800 || !inimigo.getVisivel()) {
            inimigos.remove(inimigo);
            int y = (int) (Math.random() * 800 - 1024);
            int x = (int) (Math.random() * 650 + 30);
            Inimigo inimigos = new Inimigo(x, y);
            this.inimigos.add(inimigos);
        } else {

            inimigo.atualizar();
        }
    }

    this.verificarColisoes();
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

            int posX = personagem.getPosicaoEmX();
            int posY = personagem.getPosicaoEmY();

            int larguraPersonagem = personagem.getImagem().getWidth(null);
            int alturaPersonagem = personagem.getImagem().getHeight(null);

            if (posX < 0) {
                posX = 0;
            } else if (posX + larguraPersonagem > 700) {
                posX = getWidth() - larguraPersonagem;
            }

            if (posY < 0) {
                posY = 0;
            } else if (posY + alturaPersonagem > 640) {
                posY = getHeight() - alturaPersonagem;
            }

            personagem.setPosicaoEmX(posX);
            personagem.setPosicaoEmY(posY);
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


    @Override
    public void verificarColisoes() {
        Rectangle formaPersonagem = personagem.getRectangle();
        for (int i = 0; i < this.inimigos.size(); i++) {
            Inimigo inimigo = inimigos.get(i);
            Rectangle formaInimigo = inimigo.getRectangle();
            if (formaInimigo.intersects(formaPersonagem)) {
                emJogo = false;
                this.personagem.setVisivel(false);
                inimigo.setVisivel(false);
                
            }

            ArrayList<Tiro> tiros = personagem.getTiros();
            for (int j  = 0; j < tiros.size(); j++) {
                Tiro tiro = tiros.get(j);
                Rectangle formaTiro = tiro.getRectangle();
                if (formaInimigo.intersects(formaTiro)) {
                    inimigo.setVisivel(false);
                    tiro.setVisivel(false);
                }
            }
            ArrayList<SuperTiro> superTiros = personagem.getSuperTiros();
            for (int k = 0; k < superTiros.size(); k++) {
                SuperTiro superTiro = superTiros.get(k);
                Rectangle formaSuperTiro = superTiro.getRectangle();
                if (formaInimigo.intersects(formaSuperTiro)) {
                    inimigo.setVisivel(false);
                    superTiro.setVisivel(false);
                }
            }

        }
    }
}