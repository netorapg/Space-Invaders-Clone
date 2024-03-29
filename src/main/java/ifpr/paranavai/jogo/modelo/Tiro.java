package ifpr.paranavai.jogo.modelo;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;

@Entity
@Table(name = "tb_tiro")
public class Tiro extends ElementoGrafico {
    private static int VELOCIDADE = 2;

    public Tiro(int posicaoPersonagemEmX, int posicaoPersonagemEmY) {
       this.carregar();
       this.setPosicaoEmX(posicaoPersonagemEmX - this.getLarguraImagem() / 2);
       this.setPosicaoEmY(posicaoPersonagemEmY);

    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/Imagens/laser.png"));
        this.setImagem(carregando.getImage());
        this.setAlturaImagem(this.getImagem().getHeight(null));
        this.setLarguraImagem(this.getImagem().getWidth(null));
    }

    public void atualizar() {
        this.setPosicaoEmY(this.getPosicaoEmY() - VELOCIDADE);
    }

    public Tiro get(int i) {
        return null;
    }
}