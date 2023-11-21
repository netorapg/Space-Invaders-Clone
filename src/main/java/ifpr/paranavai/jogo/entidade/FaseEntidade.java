package ifpr.paranavai.jogo.entidade;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.List;
import ifpr.paranavai.jogo.modelo.Inimigo;
import ifpr.paranavai.jogo.modelo.Personagem;
import ifpr.paranavai.jogo.modelo.Star;

@Entity
@Table(name = "fase")
public class FaseEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_fase")
    private Integer idFaseEntidade;
    @Column(name = "pode_atirar")
    private boolean podeAtirar = true;
    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn (name = "chave_fase")
    private List<Inimigo> inimigos;
    @Column(name = "temporizador")
    private int temporizador = 0;
    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn (name = "chave_fase")
    private List<Star> stars;
    @Column(name = "em_jogo")
    private boolean emJogo = false;
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "chave_personagem")
    private Personagem personagem;

    public FaseEntidade() {
        stars = new ArrayList<Star>();
        inimigos = new ArrayList<Inimigo>();
        this.personagem = new Personagem();
    }

    public boolean isPodeAtirar() {
        return podeAtirar;
    }

    public void setPodeAtirar(boolean podeAtirar) {
        this.podeAtirar = podeAtirar;
    }

    public List<Inimigo> getInimigos() {
        return inimigos;
    }

    public void setInimigos(List<Inimigo> inimigos) {
        this.inimigos = inimigos;
    }

    public int getTemporizador() {
        return temporizador;
    }

    public void setTemporizador(int temporizador) {
        this.temporizador = temporizador;
    }

    public List<Star> getStars() {
        return stars;
    }

    public void setStars(List<Star> stars) {
        this.stars = stars;
    }

    public boolean isEmJogo() {
        return emJogo;
    }

    public Integer getIdFaseEntidade() {
        return this.idFaseEntidade;
    }

    public void setIdFaseEntidade(Integer idFaseEntidade) {
        this.idFaseEntidade = idFaseEntidade;
    }

    public boolean getPodeAtirar() {
        return this.podeAtirar;
    }

    public void setEmJogo(boolean emJogo) {
        this.emJogo = emJogo;
    }

    public void incrementaTemporizador() {
        this.temporizador++;
    }

    public boolean getEmJogo() {
        return this.emJogo;
    }

    public Personagem getPersonagem() {
        return this.personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }


}
