package ifpr.paranavai.jogo.modelo;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;

@Entity
@Table(name = "tb_super_tiro")
public class SuperTiro  extends ElementoGrafico{
    private static int VELOCIDADE = 2;

    public SuperTiro(int posicaoPersonagemEmX, int posicaoPersonagemEmY) {
       this.carregar();
       this.setPosicaoEmX(posicaoPersonagemEmX - this.getLarguraImagem() / 2);
       this.setPosicaoEmY(posicaoPersonagemEmY);
   }

     public void carregar() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/Imagens/BigLaser.png"));
        this.setImagem(carregando.getImage());
        this.setLarguraImagem(this.getImagem().getWidth(null));
        this.setAlturaImagem(this.getImagem().getHeight(null));
   }
    
     public void atualizar() {
       this.setPosicaoEmY(this.getPosicaoEmY() - VELOCIDADE);
       
   }

}
