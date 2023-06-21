package ifpr.paranavai.jogo.modelo;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Personagem {
    private int posicaoEmX;
    private int posicaoEmY;
    private static final int DESLOCAMENTO = 3;
    private static final int POSICAO_INICIAL_EM_X = 100;
    private static final int POSICAO_INICIAL_EM_Y = 100;
    private int deslocamentoEmX;
    private int deslocamentoEmY;
    private Image imagem;
    private int largura;
    private int altura;
    private ArrayList<Tiro> tiros;

    public Personagem() {
        this.posicaoEmX = POSICAO_INICIAL_EM_X;
        this.posicaoEmY = POSICAO_INICIAL_EM_Y;
        this.tiros = new ArrayList<Tiro>();
        this.deslocamentoEmX = 0;
        this.deslocamentoEmY = 0;
        
    }

    public void atualizar() {
        this.posicaoEmX += this.deslocamentoEmX;
        this.posicaoEmY += this.deslocamentoEmY;
    }
    public void carregar() {
        ImageIcon loading = new ImageIcon("src/ifpr/paranavai/jogo/recursos/Imagens/spaceship.png");
        this.imagem = loading.getImage();
        this.largura = this.imagem.getWidth(null);
        this.altura = this.imagem.getHeight(null);

        
    }

    public void atirar() {
        int frenteDaNave = this.posicaoEmX + this.largura;
        int centroDaNave = this.posicaoEmY + (this.altura / 2);
        Tiro tiro = new Tiro(frenteDaNave, centroDaNave);
        this.tiros.add(tiro);
    }

    public ArrayList<Tiro> getTiros() {
        return this.tiros;
    }

    public void setTiros(ArrayList<Tiro> tiros) {
        this.tiros = new ArrayList<Tiro>();
        this.tiros = tiros;
    }

    public int getPositionX() {
        return posicaoEmX;
    }

    public void setPositionX(int posicaoEmX) {
        this.posicaoEmX = posicaoEmX;
    }

    public int getPositionY() {
        return posicaoEmY;
    }

    public void setPositionY(int posicaoEmY) {
        this.posicaoEmY = posicaoEmY;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public int getDeslocamentoEmX() {
        return deslocamentoEmX;
    }

    public void setDeslocamentoEmX(int deslocamentoX) {
        this.deslocamentoEmX = deslocamentoX;
    }

    public int getDeslocamentoEmY() {
        return deslocamentoEmY;
    }

    public void setDeslocamentoEmY(int deslocamentoY) {
        this.deslocamentoEmY = deslocamentoY;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void mover (KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        switch (codigo) {
            case KeyEvent.VK_UP:
                this.deslocamentoEmY = -DESLOCAMENTO;
                break;
            case KeyEvent.VK_DOWN:
                this.deslocamentoEmY = DESLOCAMENTO;
                break;
            case KeyEvent.VK_LEFT:
                this.deslocamentoEmX = -DESLOCAMENTO;
                break;
            case KeyEvent.VK_RIGHT:
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
                this.deslocamentoEmY = 0;
                break;
            case KeyEvent.VK_DOWN:
                this.deslocamentoEmY = 0;
                break;
            case KeyEvent.VK_LEFT:
                this.deslocamentoEmX = 0;
                break;
            case KeyEvent.VK_RIGHT:
                this.deslocamentoEmX = 0;
                break;
            default:
                break;
        }        
    }
    public int getPosicaoEmX() {
        return this.posicaoEmX;
    }

    public void setPosicaoEmX(int posicaoEmX) {
        this.posicaoEmX = posicaoEmX;
    }

    public int getPosicaoEmY() {
        return this.posicaoEmY;
    }

    public void setPosicaoEmY(int posicaoEmY) {
        this.posicaoEmY = posicaoEmY;
    }
}