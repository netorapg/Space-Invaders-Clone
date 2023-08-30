package ifpr.paranavai.jogo.modelo;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Asteroide extends ElementoGrafico {
    private static final int  VELOCIDADE = 1;

    public Asteroide(int xAleatorio, int yAleatorio) {
        this.carregar();
        super.setPosicaoEmX(xAleatorio);
        super.setPosicaoEmY(yAleatorio);
    }
    public void carregar(){
        ImageIcon carregando = new ImageIcon("src/ifpr/paranavai/jogo/recursos/Imagens/asteroid.png");
        super.setImagem(carregando.getImage());
    }

    @Override
    public void atualizar(){
        if (this.getPosicaoEmX() < 0) {
        int y = (int) (Math.random() * 640);
        super.setPosicaoEmX(800);
        super.setPosicaoEmY(y);
        } else {
            super.setPosicaoEmX(super.getPosicaoEmX() - VELOCIDADE);
        }
    }
}
