package ifpr.paranavai.jogo.modelo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ElementoGrafico {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id_elemento") 
private Integer idElementoGrafico;

@Column(name = "posicao_em_x") 
private int posicaoEmX;

@Column(name = "posicao_em_y") 
private int posicaoEmY;

@Transient
private Image imagem;

@Transient
private int larguraImagem;

@Transient
private int alturaImagem;

@Column(name = "visivel") //colocar no banco
private boolean visivel = true;

    public boolean isVisivel() {
        return this.visivel;
    }

    public boolean getVisivel() {
        return this.visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

    public Rectangle getRectangle() {
        if (getImagem() != null) {
            int largura = getLarguraImagem() > 0 ? getLarguraImagem() : getImagem().getWidth(null);
            int altura = getAlturaImagem() > 0 ? getAlturaImagem() : getImagem().getHeight(null);
            return new Rectangle(getPosicaoEmX(), getPosicaoEmY(), largura, altura);
        }
        return new Rectangle();
    }

    public int getPosicaoEmX() {
        return this.posicaoEmX;
    }

    public void setPosicaoEmX(int posicaoEmX) {
        this.posicaoEmX = posicaoEmX;
    }

    public int getPosicaoEmY() {
        return this.posicaoEmY;
    }

    public void setPosicaoEmY(int posicaoEmY) {
        this.posicaoEmY = posicaoEmY;
    }

    public Image getImagem() {
        return this.imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public int getLarguraImagem() {
        return this.larguraImagem;
    }

    public void setLarguraImagem(int larguraImagem) {
        this.larguraImagem = larguraImagem;
    }

    public int getAlturaImagem() {
        return this.alturaImagem;
    }

    public void setAlturaImagem(int alturaImagem) {
        this.alturaImagem = alturaImagem;
    }

    public Integer getIdElementoGrafico() {
        return this.idElementoGrafico;
    }

    public void setIdElementoGrafico(Integer idElementoGrafico) {
        this.idElementoGrafico = idElementoGrafico;
    }

}