package ifpr.paranavai.jogo.modelo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
//import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
//import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

//import org.lwjgl.glfw.GLFW;
//import org.lwjgl.glfw.GLFWGamepadState;

public class Fase extends JPanel implements ActionListener, KeyListener{
    private static final long serialVersionUID = 1L;
    private static final int DELAY = 5;
    private Image background;
    private  Personagem personagem;
    private Timer timer;
    private static final int ALTURA_DA_JANELA = 700;
    private boolean podeAtirar = true;
    private List<Star> stars;
    private ArrayList<Inimigo> inimigos;
    private int temporizador = 0;
    private int QUANTIDADE_INIMIGOS = 10;

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);
        ImageIcon loading = new ImageIcon("src/ifpr/paranavai/jogo/recursos/Imagens/background.png");
        this.background = loading.getImage();
        personagem = new Personagem();
        personagem.carregar();
        //int present = glfwJoystickPresent(GLFW_JOYSTICK_1);

        inicializaInimigos();
        
        addKeyListener(this);
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
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
        /* for (Tiro tiro : tiros) {
            Rectangle tiroBounds = new Rectangle(tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), tiro.getImagem().getWidth(null), tiro.getImagem().getHeight(null));
            Iterator<Inimigo> iterator = inimigos.iterator();
            while (iterator.hasNext()) {
                Inimigo inimigo = iterator.next();
                Rectangle inimigoBounds = new Rectangle(inimigo.getPosicaoEmX(), inimigo.getPosicaoEmY(), inimigo.getImagem().getWidth(null), inimigo.getImagem().getHeight(null));
                if (tiroBounds.intersects(inimigoBounds)) {
                    iterator.remove();
                    tiros.remove(tiro);
                    inimigo.setVivo(false);
                    break;
                }
            }
        }*/
        

        for (SuperTiro superTiro : superTiros) {
            superTiro.carregar();
            graphics.drawImage(superTiro.getImagem(), superTiro.getPosicaoEmX(), superTiro.getPosicaoEmY(), this);
        }

        for (Inimigo inimigo : inimigos) {
            if (inimigo.isVivo()) {
                inimigo.carregar();
                graphics.drawImage(inimigo.getImagem(), inimigo.getPosicaoEmX(), inimigo.getPosicaoEmY(), this);
            }
            
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
        if (tiros.get(i).getPosicaoEmY() >= ALTURA_DA_JANELA) {
            tiros.remove(i);
        } else {
            tiros.get(i).atualizar();
        }
    }

    ArrayList<SuperTiro> superTiros = personagem.getSuperTiros();
    for (int i = superTiros.size() - 1; i >= 0; i--) {
        if (superTiros.get(i).getPosicaoEmY() > ALTURA_DA_JANELA) {
            superTiros.remove(i);
        } else {
            superTiros.get(i).atualizar();
        }
    }
    /* for (int i = inimigos.size() - 1; i >= 0; i--) {
        Inimigo inimigo = inimigos.get(i);
        Rectangle inimigoBounds = inimigo.getBounds();
        Tiro tiro = tiros.get(i);
        if (tiro != null) {
            Rectangle tiroBounds = new Rectangle(tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), tiro.getImagem().getWidth(null), tiro.getImagem().getHeight(null));

            if (tiroBounds.intersects(inimigoBounds)) {
                inimigo.setVivo(false);
                tiros.remove(i);
                break;
            }
        }
    }
*/
    
    for (int i = inimigos.size() - 1; i >= 0; i--) {
        Inimigo inimigo = inimigos.get(i);
        if (!inimigo.isVivo()) {
            inimigos.remove(i);
        }
    }

    for (int i = 0; i < inimigos.size(); i++) {
        if (inimigos.get(i).getPosicaoEmY() > 800) {
            inimigos.remove(i);
            int y = (int) (Math.random() * 800 - 1024);
            int x = (int) (Math.random() * 650 + 30);
            Inimigo inimigo = new Inimigo(x, y);
            inimigos.add(inimigo);
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
}