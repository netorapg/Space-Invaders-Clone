package ifpr.paranavai.jogo.modelo;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ifpr.paranavai.jogo.entidade.FaseEntidade;

@Entity
@Table(name = "tb_star")
public class Star extends ElementoGrafico {

    private int tamanho;
    @ManyToOne
    private FaseEntidade fase;

    public Star(int posicaoX, int posicaoY, int tamanho) {
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


    public FaseEntidade getFase() {
        return this.fase;
    }

    public void setFase(FaseEntidade fase) {
        this.fase = fase;
    }
   

    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillOval(this.getPosicaoEmX(), this.getPosicaoEmY(), tamanho, tamanho);
    }

    public void updatePosition() {
        this.setPosicaoEmY(this.getPosicaoEmY() + 2);
    }
}
