package ifpr.paranavai.jogo.dao;

import java.util.List;
import ifpr.paranavai.jogo.modelo.FaseUm;

public interface FaseUmDao {
    public List<FaseUm> buscarTodos();
    public FaseUm buscarPorId(Integer id);
    public void atualizar(FaseUm faseUm);
    public void excluir(FaseUm faseUm);
    public void inserir(FaseUm faseUm);
}
