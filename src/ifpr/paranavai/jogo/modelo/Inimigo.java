package ifpr.paranavai.jogo.modelo;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Inimigo {
    private int posicaoEmX;
    private int posicaoEmY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private static int VELOCIDADE = 1;
    private boolean vivo;

    public Inimigo (int xAleatorio, int yAleatorio) {
        this.posicaoEmX = xAleatorio;
        this.posicaoEmY = yAleatorio;
        this.vivo = true;
    }

    public void carregar() {
        ImageIcon loading = new ImageIcon("src/ifpr/paranavai/jogo/recursos/Imagens/ufo.png");
        this.imagem = loading.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }
    public void atualizar() {
        if (!vivo){
            return;
        }
        this.posicaoEmY += VELOCIDADE;
    }


    public boolean isVivo() {
        return this.vivo;
    }

    public boolean getVivo() {
        return this.vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
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