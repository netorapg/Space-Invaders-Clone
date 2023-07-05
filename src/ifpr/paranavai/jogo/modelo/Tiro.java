package ifpr.paranavai.jogo.modelo;
import javax.swing.ImageIcon;


public class Tiro extends ElementoGrafico {
    private static int VELOCIDADE = 2;


    public Tiro(int posicaoPersonagemEmX, int posicaoPersonagemEmY) {
       this.carregar();
       this.setPosicaoEmX(posicaoPersonagemEmX);
        this.setPosicaoEmY(posicaoPersonagemEmY);

    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon("src/ifpr/paranavai/jogo/recursos/Imagens/laser.png");
        this.setImagem(carregando.getImage());
        this.setAlturaImagem(this.getImagem().getWidth(null));
        this.setLarguraImagem(this.getImagem().getHeight(null));
    }

    public void atualizar() {
        this.setPosicaoEmY(this.getPosicaoEmY() - VELOCIDADE);
    }

    public Tiro get(int i) {
        return null;
    }
}