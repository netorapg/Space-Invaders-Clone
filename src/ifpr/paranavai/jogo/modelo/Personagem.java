package ifpr.paranavai.jogo.modelo;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Personagem {
    private int positionX;
    private int positionY;
    private int deslocamentoX;
    private int deslocamentoY;
    private Image imagem;
    private int largura;
    private int altura;

    public Personagem() {
        this.positionX = 100;
        this.positionY = 100;
        this.deslocamentoX = 0;
        this.deslocamentoY = 0;
        
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

}