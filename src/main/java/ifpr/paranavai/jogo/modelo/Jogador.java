package ifpr.paranavai.jogo.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // Indica que a classe é uma entidade
@Table(name = "tb_jogador") // Indica o nome da tabela que será criada no banco de dados
public class Jogador {
    
    @Id // Indica que o atributo é uma chave primária
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_jogador", unique = true, nullable = false)
    private Integer jogadorId;

    @Column(name = "nome", unique = true, nullable = false, length = 100)
    private String nome;

    public Jogador() {
    }
    public Jogador(String nome) {
        this.nome = nome;
    }
    public Integer getJogadorId() {
        return jogadorId;
    }
    public void setJogadorId(Integer jogadorId) {
        this.jogadorId = jogadorId;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }


}
