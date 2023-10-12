package ifpr.paranavai.jogo.modelo;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;


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

    /* public void salvarPontuacao(int pontuacao) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/pontuacao.txt", true))){
            writer.write(String.valueOf(pontuacao));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    
    private HashMap<String, Clip> soundClips = new HashMap<>();
    public void playSound(String soundName){
        try {
            if (!soundClips.containsKey(soundName)) {
                URL url = this.getClass().getResource("/Sons/" + soundName);
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                soundClips.put(soundName, clip);
            }
    
            Clip clipToPlay = soundClips.get(soundName);
            if (clipToPlay.isRunning()) {
                clipToPlay.stop(); // Pare o clip antes de reiniciar
            }
            clipToPlay.setFramePosition(0); // Volte para o início do clip
            clipToPlay.start();
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private Clip clip;
    public void tocarMusicaDeFundo(String soundName) {
        try {
            URL url = this.getClass().getResource("/Sons/" + soundName);
            if (url == null) {
                throw new RuntimeException("O arquivo de musica não foi encontrado: " + soundName);
            }
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public void pararMusicaDeFundo(){
        if (clip != null && clip.isRunning()) {
            clip.stop();
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
