package ifpr.paranavai.jogo.modelo;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Inimigo extends ElementoGrafico{
    private static int VELOCIDADE = 1;
    private boolean vivo;

    public Inimigo (int xAleatorio, int yAleatorio) {
        this.carregar();
        setPosicaoEmX(xAleatorio);
        setPosicaoEmY(yAleatorio);
        this.vivo = true;
    }

    public void carregar() {
        ImageIcon loading = new ImageIcon("src/ifpr/paranavai/jogo/recursos/Imagens/ufo.png");
        this.setImagem(loading.getImage());
        this.setAlturaImagem(this.getImagem().getWidth(null));
        this.setLarguraImagem(this.getImagem().getHeight(null));

    }
    public void atualizar() {
        if (!vivo){
            return;
        }
        this.setPosicaoEmY(this.getPosicaoEmY() + VELOCIDADE);
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


    public Rectangle getBounds() {
         return new Rectangle(getPosicaoEmX(), getPosicaoEmY(), getImagem().getWidth(null), getImagem().getHeight(null));
    }
    
}