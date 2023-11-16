package ifpr.paranavai.jogo.entidade;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;
import ifpr.paranavai.jogo.modelo.Inimigo;
import ifpr.paranavai.jogo.modelo.Star;

@Entity
@Table(name = "fase")
public class FaseEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer idFaseEntidade;
    @Column(name = "pode_atirar")
    private boolean podeAtirar = true;
    @OneToMany(mappedBy = "fase")
    private List<Inimigo> inimigos;
    @Column(name = "temporizador")
    private int temporizador = 0;
    @OneToMany(mappedBy = "fase")
    private List<Star> stars;
    @Column(name = "em_jogo")
    private boolean emJogo = false;

    public FaseEntidade() {
        stars = new ArrayList<Star>();
        inimigos = new ArrayList<Inimigo>();
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

}
