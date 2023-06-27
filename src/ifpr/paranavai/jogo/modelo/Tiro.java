package ifpr.paranavai.jogo.modelo;
import java.awt.Image;
import javax.swing.ImageIcon;


public class Tiro {
    private int posicaoEmX;
    private int posicaoEmY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private static int VELOCIDADE = 2;


    public Tiro(int posicaoPersonagemX, int posicaoPersonagemY) {
        this.carregar();
        this.posicaoEmX = posicaoPersonagemX - (this.larguraImagem / 2);
        this.posicaoEmY = posicaoPersonagemY;
    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon("src/ifpr/paranavai/jogo/recursos/Imagens/laser.png");
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    public void atualizar() {
        this.posicaoEmY -= VELOCIDADE;
    }


    public int getPosicaoEmX() {
        return this.posicaoEmX;
    }

    public void setPosicaoEmX(int positionInX) {
        this.posicaoEmX = positionInX;
    }

    public int getPosicaoEmY() {
        return this.posicaoEmY;
    }

    public void setPosicaoEmY(int positionInY) {
        this.posicaoEmY = positionInY;
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