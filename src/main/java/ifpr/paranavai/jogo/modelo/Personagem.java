package ifpr.paranavai.jogo.modelo;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;

@Entity
@Table (name = "tb_personagem")
public class Personagem extends ElementoGrafico {
    private static final int DESLOCAMENTO = 3;
    private static final int POSICAO_INICIAL_EM_X = 360;
    private static final int POSICAO_INICIAL_EM_Y = 500;
    @Column(name = "deslocamento_em_x")
    private int deslocamentoEmX = 0;
    @Column(name = "deslocamento_em_y")
    private int deslocamentoEmY = 0;
    private ArrayList<Tiro> tiros;
    private ArrayList<SuperTiro> superTiros;
    @Column(name = "vidas")
    private int vidas = 3;
    @Column(name = "pontuacao")
    private int pontuacao = 0;

    public int getPontuacao() {
        return this.pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
   
    public Personagem() {
        this.tiros = new ArrayList<Tiro>();
        this.superTiros = new ArrayList<SuperTiro>();
        this.deslocamentoEmX = 0;
        this.deslocamentoEmY = 0;
        setPosicaoEmX(POSICAO_INICIAL_EM_X);
        setPosicaoEmY(POSICAO_INICIAL_EM_Y);
    }
  
    public void atualizar() {
        int novaPosX = this.getPosicaoEmX() + this.getDeslocamentoEmX();
        int novaPosY = this.getPosicaoEmY() + this.getDeslocamentoEmY();
        if (novaPosX < 0) {
            novaPosX = 0;
        } else if (novaPosX + this.getLarguraImagem() > 800) {
            novaPosX = 800 - this.getLarguraImagem();
        }
        if (novaPosY < 0) {
            novaPosY = 0;
        } else if (novaPosY + this.getAlturaImagem() > 640) {
            novaPosY = 640 - this.getAlturaImagem();
        }
        this.setPosicaoEmX(novaPosX);
        this.setPosicaoEmY(novaPosY);
    }
    
    public void carregar() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/Imagens/spaceship.png"));
        setImagem(loading.getImage());
        setAlturaImagem(getImagem().getWidth(null));
        setLarguraImagem(getImagem().getHeight(null));
        setPosicaoEmX(POSICAO_INICIAL_EM_X);
        setPosicaoEmY(POSICAO_INICIAL_EM_Y);
    }

    public void atirar() {
        int frenteDaNave = this.getPosicaoEmX() + this.getLarguraImagem() / 2;
        Tiro tiro = new Tiro(frenteDaNave, this.getPosicaoEmY());
        this.tiros.add(tiro);
    }

    public void superAtirar() {
        int frenteDaNave = this.getPosicaoEmX() + this.getLarguraImagem() / 2;
        SuperTiro superTiro = new SuperTiro(frenteDaNave, this.getPosicaoEmY());
        this.superTiros.add(superTiro);
    }

    public ArrayList<Tiro> getTiros() {
        return this.tiros;
    }

    public ArrayList<SuperTiro> getSuperTiros() {
        return this.superTiros;
    }
    
    public void setSuperTiros(ArrayList<SuperTiro> superTiros) {
        this.superTiros = new ArrayList<SuperTiro>();
        this.superTiros = superTiros;
    }

    public void setTiros(ArrayList<Tiro> tiros) {
        this.tiros = new ArrayList<Tiro>();
        this.tiros = tiros;
    }

    public void mover (KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        switch (codigo) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                this.deslocamentoEmY = -DESLOCAMENTO;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                this.deslocamentoEmY = DESLOCAMENTO;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                this.deslocamentoEmX = -DESLOCAMENTO;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                this.deslocamentoEmX = DESLOCAMENTO;
                break;
            default:
                break;
        }
    }
    
    public void parar (KeyEvent tecla) {
          int codigo = tecla.getKeyCode();
        switch (codigo) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                this.deslocamentoEmY = 0;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                this.deslocamentoEmY = 0;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                this.deslocamentoEmX = 0;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                this.deslocamentoEmX = 0;
                break;
            default:
                break;
        }
    }

    public void dispararTiro() {  
            atirar();
    }

    public void dispararSuperTiro() {
        superAtirar();
    }

    public Rectangle getBounds() {
        return null;
    }

    public void setVisivel(boolean b) {
    }

    public void morrer() {
    }

    public int getDeslocamentoEmX() {
        return this.deslocamentoEmX;
    }

    public void setDeslocamentoEmX(int deslocamentoEmX) {
        this.deslocamentoEmX = deslocamentoEmX;
    }

    public int getDeslocamentoEmY() {
        return this.deslocamentoEmY;
    }

    public void setDeslocamentoEmY(int deslocamentoEmY) {
        this.deslocamentoEmY = deslocamentoEmY;
    }

    public void perderVida() {
        this.vidas--;
    }

    public int getVidas() {
        return this.vidas;
    }
    
   public void setVidas(int vidas) {
        this.vidas = vidas;
    }
}