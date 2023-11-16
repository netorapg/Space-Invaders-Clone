package ifpr.paranavai.jogo.dao;

import java.util.List;
import ifpr.paranavai.jogo.entidade.FaseEntidade;

public interface FaseEntidadeDao {
    public List<FaseEntidade> buscarTodos();
    public FaseEntidade buscarPorId(Integer id);
    public void atualizar(FaseEntidade faseEntidade);
    public void excluir(FaseEntidade faseEntidade);
    public void inserir(FaseEntidade faseEntidade);
}
