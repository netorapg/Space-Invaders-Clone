package ifpr.paranavai.jogo.modelo;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_star")
public class Star extends ElementoGrafico {

    private int tamanho;

    public Star(int posicaoX, int posicaoY, int tamanho){
        this.setPosicaoEmX(posicaoY);
        this.setPosicaoEmY(posicaoY);
        this.tamanho = tamanho;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void draw (Graphics2D graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillOval(this.getPosicaoEmX(), this.getPosicaoEmY(), tamanho, tamanho);
    }

    public void updatePosition() {
        this.setPosicaoEmY(this.getPosicaoEmY() + 2);
    }
}

