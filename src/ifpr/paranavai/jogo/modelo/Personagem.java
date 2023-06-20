package ifpr.paranavai.jogo.modelo;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Personagem {
    private int positionX;
    private int positionY;
    private static final int DESLOCAMENTO = 3;
    private static final int POSICAO_INICIAL_EM_X = 100;
    private static final int POSICAO_INICIAL_EM_Y = 100;
    private int deslocamentoX;
    private int deslocamentoY;
    private Image imagem;
    private int largura;
    private int altura;

    public Personagem() {
        this.positionX = POSICAO_INICIAL_EM_X;
        this.positionY = POSICAO_INICIAL_EM_Y;
        this.deslocamentoX = 0;
        this.deslocamentoY = 0;
        
    }

    public void atualizar() {
        this.positionX += this.deslocamentoX;
        this.positionY += this.deslocamentoY;
    }
    public void carregar() {
        ImageIcon loading = new ImageIcon("src/ifpr/paranavai/jogo/recursos/Imagens/spaceship.png");
        this.imagem = loading.getImage();
        this.largura = this.imagem.getWidth(null);
        this.altura = this.imagem.getHeight(null);

        
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public int getDeslocamentoX() {
        return deslocamentoX;
    }

    public void setDeslocamentoX(int deslocamentoX) {
        this.deslocamentoX = deslocamentoX;
    }

    public int getDeslocamentoY() {
        return deslocamentoY;
    }

    public void setDeslocamentoY(int deslocamentoY) {
        this.deslocamentoY = deslocamentoY;
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
            this.deslocamentoY = -DESLOCAMENTO;
            break;
            case KeyEvent.VK_DOWN:
            this.deslocamentoY = DESLOCAMENTO;
            break;
            case KeyEvent.VK_LEFT:
            this.deslocamentoX = -DESLOCAMENTO;
            break;
            case KeyEvent.VK_RIGHT:
            this.deslocamentoX = DESLOCAMENTO;
            break;
            default:
            break;
        }
    }

    public void parar (KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        switch (codigo) {
            case KeyEvent.VK_UP:
            this.deslocamentoY = 0;
            break;
            case KeyEvent.VK_DOWN:
            this.deslocamentoY = 0;
            break;
            case KeyEvent.VK_LEFT:
            this.deslocamentoX = 0;
            break;
            case KeyEvent.VK_RIGHT:
            this.deslocamentoX = 0;
            break;
            default:
            break;
        }        
    }
}