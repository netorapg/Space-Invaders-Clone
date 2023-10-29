package ifpr.paranavai.jogo.modelo;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import ifpr.paranavai.jogo.servico.PersonagemServico;

//import org.lwjgl.glfw.GLFW;
//import org.lwjgl.glfw.GLFWGamepadState;
@Entity
@Table (name = "tb_fase")
public class FaseUm extends Fase{
    private  Personagem personagem;
    private Timer timer;
    //private static final int ALTURA_DA_JANELA = 640;
    private boolean podeAtirar = true;
    private List<Star> stars;
    private ArrayList<Inimigo> inimigos;
    private int temporizador = 0;
    private int QUANTIDADE_INIMIGOS = 20;
    private boolean emJogo = false;
    private boolean menu = true;
    private int pontuacao = 0;
    private boolean vivo = true;



    public FaseUm() {
        super();
        ImageIcon loading = new ImageIcon(getClass().getResource("/Imagens/background.png"));
        this.background = loading.getImage();
        personagem = new Personagem();
        personagem.carregar();
        PersonagemServico.inserir(personagem);
        personagem = PersonagemServico.buscarPorId(personagem.getIdElementoGrafico());
        if(personagem == null) {
            personagem = new Personagem();
            PersonagemServico.inserir(personagem);
        }
        this.inicializaInimigos();
        //int present = glfwJoystickPresent(GLFW_JOYSTICK_1);
        timer = new Timer(DELAY, this);
        timer.start();

        stars = new ArrayList<Star>();
        preencherEstrelas();

        tocarMusicaDeFundo("musicaDeFundo.wav");
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
    @Override
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
            int tamanhoAleatorio = 1 + (int) (Math.random() * 5);
            Star star = new Star(x, y, tamanhoAleatorio);
            stars.add(star);
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.drawImage(background, 0, 0, null);
        for (Star star: stars) {
            if (star.getPosicaoEmY() >= getHeight()) {
                star.setPosicaoEmY((int) (Math.random() * getHeight()));
                star.setPosicaoEmX((int) (Math.random() * getWidth()));
            }
            star.draw(graphics);
        }
        if (emJogo){
        graphics.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), this.personagem.getPosicaoEmY(), this);
        ArrayList<Tiro> tiros = personagem.getTiros();
        ArrayList<SuperTiro> superTiros = personagem.getSuperTiros();
        
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

        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        graphics.drawString("Pontuação: " + personagem.getPontuacao(), 10, 20);
        
        
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        graphics.drawString("Vidas: " + personagem.getVidas(), 10, 60);
       


        if (temporizador >= 500) {
        graphics.setColor(Color.RED);
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        graphics.drawString("SUPER TIRO PRONTO!", 500, 20);
        }
        } if (menu) {

            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Arial", Font.BOLD, 40));
            graphics.drawString("ANOTHER SPACE INVADERS CLONE", 50, 100);
            graphics.setFont(new Font("Arial", Font.BOLD, 20));
            graphics.drawString("DEVELOPED BY RENATO AUGUSTO", 400, 150);

            ImageIcon wasd = new ImageIcon(getClass().getResource("/Imagens/wasd.png"));
            ImageIcon enter = new ImageIcon(getClass().getResource("/Imagens/enter.png"));
            ImageIcon arrow = new ImageIcon(getClass().getResource("/Imagens/arrows.png"));
            ImageIcon space = new ImageIcon(getClass().getResource("/Imagens/space.png"));
            ImageIcon shift = new ImageIcon(getClass().getResource("/Imagens/shift.png"));
            enter.paintIcon(this, graphics, 550, 440);
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Arial", Font.BOLD, 50));
            graphics.drawString("PRESS ENTER", 200, 500);
            graphics.drawString("TO START", 200, 550);

            wasd.paintIcon(this, graphics, 100, 260);
            arrow.paintIcon(this, graphics, 170, 270);
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Arial", Font.BOLD, 20));
            graphics.drawString("USE WASD OR ARROW KEYS TO MOVE", 240, 310);

            space.paintIcon(this, graphics, 100, 320);
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Arial", Font.BOLD, 20));
            graphics.drawString("PRESS SPACE TO SHOOT", 170, 360);

            shift.paintIcon(this, graphics, 100, 370);
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Arial", Font.BOLD, 20));
            graphics.drawString("PRESS SHIFT TO SHOOT SUPER BULLET", 170, 420);



        }
        
        if (!vivo && !menu) {
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Arial", Font.BOLD, 50));
            graphics.drawString("GAME OVER", 20, 100);
            graphics.drawString("PONTUAÇÃO: " + personagem.getPontuacao(), 20, 150);
            graphics.drawString("PRESSIONE R PARA REINICIAR", 20, 200);
        }
       
        
       
        graphics.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    temporizador++;
    personagem.atualizar();

    for (Star star : stars) {
        star.updatePosition();
        if (star.getPosicaoEmY() > getHeight()) {
            star.setPosicaoEmY(-star.getTamanho());
        }
        }

    ArrayList<Tiro> tiros = personagem.getTiros();
    for (int i = tiros.size() - 1; i >= 0; i--) {
        Tiro tiro = tiros.get(i);
        if (tiro.getPosicaoEmY() < 0 || !tiro.getVisivel()) {
            tiros.remove(tiro);
        } else {
            tiro.atualizar();
        }
    }

    ArrayList<SuperTiro> superTiros = personagem.getSuperTiros();
    for (int i = superTiros.size() - 1; i >= 0; i--) {
        SuperTiro superTiro = superTiros.get(i);
        if (superTiro.getPosicaoEmY() < 0 || !superTiro.getVisivel()) {
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

           // int larguraPersonagem = personagem.getImagem().getWidth(null);
           // int alturaPersonagem = personagem.getImagem().getHeight(null);


            personagem.setPosicaoEmX(posX);
            personagem.setPosicaoEmY(posY);
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            menu = false;
            emJogo = true;
            playSound("NaveEntrando.wav");
            vivo = true;
            personagem.setVisivel(true);
            inicializaInimigos();
        }

        if (e.getKeyCode() == KeyEvent.VK_P) {
            menu = true;
            emJogo = false;
            personagem.setVisivel(false);
        }

        if (e.getKeyCode() == KeyEvent.VK_R) {
            emJogo = true;
            playSound("NaveEntrando.wav");
            vivo = true;
            //pontuacao = 0;
            personagem.setPontuacao(0);
            personagem.setVidas(3);
            personagem.setVisivel(true);
            inicializaInimigos();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            podeAtirar = true;
            playSound("tiro.wav");
        }
        personagem.parar(e);

        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            podeAtirar = true;
            playSound("super.wav");
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
                personagem.perderVida();
                inimigo.setVisivel(false);

                if (personagem.getVidas() <= 0) {
                emJogo = false;
                vivo = false;
              //  salvarPontuacao(pontuacao);
                this.personagem.setVisivel(false);
             
                }
                
            }

            ArrayList<Tiro> tiros = personagem.getTiros();
            for (int j  = 0; j < tiros.size(); j++) {
                Tiro tiro = tiros.get(j);
                Rectangle formaTiro = tiro.getRectangle();
                if (formaInimigo.intersects(formaTiro)) {
                    inimigo.setVisivel(false);
                    tiro.setVisivel(false);
                    pontuacao += 10;
                    personagem.setPontuacao(pontuacao);
                    
                }

                if (formaInimigo.intersects(formaPersonagem)) {
                    tiro.setVisivel(false);
                }
            }
            ArrayList<SuperTiro> superTiros = personagem.getSuperTiros();
            for (int k = 0; k < superTiros.size(); k++) {
                SuperTiro superTiro = superTiros.get(k);
                Rectangle formaSuperTiro = superTiro.getRectangle();
                if (formaInimigo.intersects(formaSuperTiro)) {
                    inimigo.setVisivel(false);
                    pontuacao += 20;
                    personagem.setPontuacao(pontuacao);
                }
                if (formaInimigo.intersects(formaPersonagem)) {
                    superTiro.setVisivel(false);
                }
            }

        }
    }
}
