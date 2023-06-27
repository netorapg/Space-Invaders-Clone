package ifpr.paranavai.jogo.modelo;
public class Star {
    private int posicaoX;
    private int posicaoY;
    private int tamanho;

    public Star(int posicaoX, int posicaoY, int tamanho) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.tamanho = tamanho;
    }

    public int getPosicaoX() {
        return posicaoX;
    }

    public void setPosicaoX(int posicaoX) {
        this.posicaoX = posicaoX;
    }

    public int getPosicaoY() {
        return posicaoY;
    }

    public void setPosicaoY(int posicaoY) {
        this.posicaoY = posicaoY;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
}
