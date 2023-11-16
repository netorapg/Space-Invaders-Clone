package ifpr.paranavai.jogo.modelo;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import javax.swing.Timer;

import ifpr.paranavai.jogo.entidade.FaseEntidade;


public abstract class Fase extends JPanel implements ActionListener, KeyListener{
    public static final int DELAY = 5;


    protected Image background;
    protected Personagem personagem;
    protected Timer timer;
    protected boolean podeAtirar = true;
    protected boolean menu = true;
    protected FaseEntidade faseEntidade;
    private HashMap<String, Clip> soundClips = new HashMap<>();
    private Clip clip;

    public Fase() {
        this.faseEntidade = new FaseEntidade();
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(this);
    }

    public abstract void inicializaInimigos();
    public abstract void preencherEstrelas();
    public abstract void verificarColisoes();

    
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
}
