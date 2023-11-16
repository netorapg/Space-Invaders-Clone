package ifpr.paranavai.jogo.modelo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.swing.ImageIcon;

import org.hibernate.annotations.ManyToAny;

import ifpr.paranavai.jogo.entidade.FaseEntidade;

@Entity
@Table(name = "tb_inimigo")
public class Inimigo extends ElementoGrafico {
    private int velocidade = 1;
    @ManyToOne
    private FaseEntidade fase;

    public Inimigo(int xAleatorio, int yAleatorio) {
        this.carregar();
        setPosicaoEmX(xAleatorio);
        setPosicaoEmY(yAleatorio);
        this.velocidade = 1;
    }

    public int getVelocidade() {
        return this.velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }


    public FaseEntidade getFase() {
        return this.fase;
    }

    public void setFase(FaseEntidade fase) {
        this.fase = fase;
    }
    

    public void carregar() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/Imagens/ufo.png"));
        this.setImagem(loading.getImage());
        this.setAlturaImagem(this.getImagem().getWidth(null));
        this.setLarguraImagem(this.getImagem().getHeight(null));
    }

    public void atualizar() {
        if (!getVisivel()) {
            return;
        }
        this.setPosicaoEmY(this.getPosicaoEmY() + velocidade);
    }

}