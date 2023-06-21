package ifpr.paranavai.jogo.modelo;
import java.awt.Image;
import javax.swing.ImageIcon;


public class Tiro {
    private int positionInX;
    private int positionInY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private static int VELOCIDADE = 2;


    public Tiro(int positionX, int positionY) {
        this.positionInX = positionX;
        this.positionInY = positionY;
    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon("src/ifpr/paranavai/jogo/recursos/Imagens/laser.png");
        this.imagem = carregando.getImage();
        this.larguraImagem = this.imagem.getWidth(null);
        this.alturaImagem = this.imagem.getHeight(null);
    }

    public void atualizar() {
        this.positionInX += VELOCIDADE;
    }


    public int getPositionInX() {
        return this.positionInX;
    }

    public void setPositionInX(int positionInX) {
        this.positionInX = positionInX;
    }

    public int getPositionInY() {
        return this.positionInY;
    }

    public void setPositionInY(int positionInY) {
        this.positionInY = positionInY;
    }

    public Image getImagem() {
        return this.imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public int getLarguraImagem() {
        return this.larguraImagem;
    }

    public void setLarguraImagem(int larguraImagem) {
        this.larguraImagem = larguraImagem;
    }

    public int getAlturaImagem() {
        return this.alturaImagem;
    }

    public void setAlturaImagem(int alturaImagem) {
        this.alturaImagem = alturaImagem;
    }
}