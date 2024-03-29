package ifpr.paranavai.jogo.controle;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import ifpr.paranavai.jogo.modelo.Inimigo;
import ifpr.paranavai.jogo.modelo.Personagem;
import ifpr.paranavai.jogo.modelo.Star;
import ifpr.paranavai.jogo.modelo.SuperTiro;
import ifpr.paranavai.jogo.modelo.Tiro;
import ifpr.paranavai.jogo.servico.FaseEntidadeServico;

public class FaseUm extends Fase {
    private int qInimigos = 1;
    private boolean exibirMensagemSalvo = false;
    private Timer mensagemTimer;

    public FaseUm() {
        super();
        ImageIcon loading = new ImageIcon(getClass().getResource("/Imagens/background.png"));
        this.background = loading.getImage();
        FaseEntidadeServico.inserir(faseEntidade);
        personagem.carregar();
        this.inicializaInimigos();
        // int present = glfwJoystickPresent(GLFW_JOYSTICK_1);
        timer = new Timer(DELAY, this);
        timer.start();

        preencherEstrelas();

        tocarMusicaDeFundo("musicaDeFundo.wav");

        mensagemTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exibirMensagemSalvo = false;
                mensagemTimer.stop();
                repaint();
            }
        });
    }

    public void inicializaInimigos() {

        for (int i = 0; i < qInimigos; i++) {
            int y = (int) (Math.random() * 800 - 1024);
            int x = (int) (Math.random() * 650 + 30);
            Inimigo inimigo = new Inimigo(x, y);
            // evite que os inimigos nasçam um em cima do outro
            for (Inimigo inimigoExistente : faseEntidade.getInimigos()) {
                if (inimigo.getRectangle().intersects(inimigoExistente.getRectangle())) {
                    inimigo.setPosicaoEmX((int) (Math.random() * 650 + 30));
                    inimigo.setPosicaoEmY((int) (Math.random() * 800 - 1024));
                }
            }
            faseEntidade.getInimigos().add(inimigo);
        }
    }

    @Override
    public void preencherEstrelas() {
        int quantidadeEstrelas = 100;
        int distanciaMaxima = 1;
        int centroX = getWidth() / 2;
        int centroY = getHeight() / 2;

        for (int i = 0; i < quantidadeEstrelas; i++) {
            double angulo = Math.random() * 2 * Math.PI;
            int distancia = (int) (Math.random() * distanciaMaxima);
            int x = (int) (centroX + distancia * Math.cos(angulo));
            int y = (int) (centroY + distancia * Math.sin(angulo));
            int tamanhoAleatorio = 1 + (int) (Math.random() * 5);
            Star star = new Star(x, y, tamanhoAleatorio);
            faseEntidade.getStars().add(star);
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.drawImage(background, 0, 0, null);
        for (Star star : faseEntidade.getStars()) {
            if (star.getPosicaoEmY() >= getHeight()) {
                star.setPosicaoEmY((int) (Math.random() * getHeight()));
                star.setPosicaoEmX((int) (Math.random() * getWidth()));
            }
            star.draw(graphics);
        }

        if (exibirMensagemSalvo) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 20));
            g2d.drawString("Jogo salvo com sucesso", 500, 50);
        }
        if (faseEntidade.isEmJogo() && !menu) {
            graphics.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), this.personagem.getPosicaoEmY(),
                    this);
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

            for (Inimigo inimigo : faseEntidade.getInimigos()) {
                inimigo.carregar();
                graphics.drawImage(inimigo.getImagem(), inimigo.getPosicaoEmX(), inimigo.getPosicaoEmY(), this);
            }

            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Arial", Font.BOLD, 20));
            graphics.drawString("Pontuação: " + personagem.getPontuacao(), 10, 20);

            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Arial", Font.BOLD, 20));
            graphics.drawString("Vidas: " + personagem.getVidas(), 10, 60);

            if (faseEntidade.getTemporizador() >= 500) {
                graphics.setColor(Color.RED);
                graphics.setFont(new Font("Arial", Font.BOLD, 20));
                graphics.drawString("SUPER TIRO PRONTO!", 500, 20);
            }
        }
        if (menu) {

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
            graphics.drawString("PRESS \"ALT\" TO LOAD GAME", 240, 200);
            graphics.drawString("PRESS \"Q\" TO SAVE", 240, 250);
            graphics.drawString("USE \"WASD\" OR \"ARROW KEYS\" TO MOVE", 240, 310);

            space.paintIcon(this, graphics, 100, 320);
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Arial", Font.BOLD, 20));
            graphics.drawString("PRESS \"SPACE\" TO SHOOT", 170, 360);

            shift.paintIcon(this, graphics, 100, 370);
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Arial", Font.BOLD, 20));
            graphics.drawString("PRESS \"SHIFT\" TO SHOOT SUPER BULLET", 170, 420);

        }

        if (!personagem.getVisivel() && !menu) {
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
        for (Star star : faseEntidade.getStars()) {
            star.updatePosition();
            if (star.getPosicaoEmY() > getHeight()) {
                star.setPosicaoEmY(-star.getTamanho());
            }
        }
        if (faseEntidade.isEmJogo() && !menu) {
            faseEntidade.incrementaTemporizador();
            personagem.atualizar();

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

            for (int i = faseEntidade.getInimigos().size() - 1; i >= 0; i--) {
                Inimigo inimigo = faseEntidade.getInimigos().get(i);
                if (inimigo.getPosicaoEmY() > 800 || !inimigo.getVisivel()) {
                    faseEntidade.getInimigos().remove(i);
                    int y = (int) (Math.random() * 800 - 1024);
                    int x = (int) (Math.random() * 650 + 30);
                    Inimigo inimigos = new Inimigo(x, y);
                    faseEntidade.getInimigos().add(inimigos);

                } else {
                    inimigo.atualizar();
                }
            }

            this.verificarColisoes();
            

        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // keypressed para salvar
        // PersonsagemServico.inserir(personagem);
        if (menu) {
            if (e.getKeyCode() == KeyEvent.VK_ALT) {
                String idDigitado = JOptionPane.showInputDialog("Digite seu ID:");
                if (idDigitado != null && !idDigitado.isEmpty()) {
                    int id = Integer.parseInt(idDigitado);
                    faseEntidade = FaseEntidadeServico.buscarPorId(id);
                    personagem = faseEntidade.getPersonagem();
                    personagem.carregar();
                    faseEntidade.getEmJogo();
                    menu = false;
                }
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE && podeAtirar) {
            personagem.dispararTiro();
            podeAtirar = false;
        } else {
            personagem.mover(e);
        }
        if (e.getKeyCode() == KeyEvent.VK_SHIFT && podeAtirar && faseEntidade.getTemporizador() >= 500) {
            personagem.dispararSuperTiro();
            podeAtirar = false;
            faseEntidade.setTemporizador(0);
        } else {
            personagem.mover(e);

            int posX = personagem.getPosicaoEmX();
            int posY = personagem.getPosicaoEmY();

            personagem.setPosicaoEmX(posX);
            personagem.setPosicaoEmY(posY);
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            menu = false;
            faseEntidade.setEmJogo(true);
            playSound("NaveEntrando.wav");
            personagem.setVisivel(true);
            // inimigo.setVisivel(true);
            // inimigo.setVivo(true);
            inicializaInimigos();

        }

        if (e.getKeyCode() == KeyEvent.VK_P) {
            menu = true;
            faseEntidade.setEmJogo(false);
            personagem.setVisivel(false);
        }

        if (e.getKeyCode() == KeyEvent.VK_Q) {
            FaseEntidadeServico.atualizar(faseEntidade);
            exibirMensagemSalvo = true;
            mensagemTimer.start();
        }

        if (e.getKeyCode() == KeyEvent.VK_R) {
            faseEntidade.setEmJogo(true);
            playSound("NaveEntrando.wav");
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

    public void adicionarNovoInimigo(){
        int y = (int) (Math.random() * 800 - 1024);
        int x = (int) (Math.random() * 650 + 30);
        Inimigo inimigo = new Inimigo(x, y);
        for (Inimigo inimigoExistente : faseEntidade.getInimigos()) {
            if (inimigo.getRectangle().intersects(inimigoExistente.getRectangle())){
                inimigo.setPosicaoEmX((int) (Math.random() * 650 + 30));
                inimigo.setPosicaoEmY((int) (Math.random() * 800 - 1024));
            }
        }
        faseEntidade.getInimigos().add(inimigo);
    }

    @Override
    public void verificarColisoes() {
        Rectangle formaPersonagem = personagem.getRectangle();
        for (int i = faseEntidade.getInimigos().size() - 1; i >= 0; i--) {
            Inimigo inimigo = faseEntidade.getInimigos().get(i);
            Rectangle formaInimigo = inimigo.getRectangle();
            if (formaInimigo.intersects(formaPersonagem)) {
                personagem.perderVida();
                inimigo.setVisivel(false);

                if (personagem.getVidas() == 0) {
                    
                    FaseEntidadeServico.atualizar(faseEntidade);
                    exibirMensagemSalvo = true;
                    faseEntidade.setEmJogo(false);
                    // this.inimigo.setVisivel(false);
                    // inimigo.setVivo(false);
                    this.personagem.setVisivel(false);
                }
            }

            ArrayList<Tiro> tiros = personagem.getTiros();
            for (int j = 0; j < tiros.size(); j++) {
                Tiro tiro = tiros.get(j);
                Rectangle formaTiro = tiro.getRectangle();
                if (formaInimigo.intersects(formaTiro)) {
                    inimigo.setVisivel(false);
                    tiro.setVisivel(false);
                    personagem.incrementaPontuacao(10);
                    inimigo.setVelocidade(inimigo.getVelocidade() + 5);
                    adicionarNovoInimigo();
                    qInimigos++;
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
                    personagem.incrementaPontuacao(20);
                    inimigo.setVelocidade(inimigo.getVelocidade() + 5);
                    adicionarNovoInimigo();
                    qInimigos++;
                }
                if (formaInimigo.intersects(formaPersonagem)) {
                    superTiro.setVisivel(false);
                }
            }
        }
    }
}
