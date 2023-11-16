package ifpr.paranavai.jogo.servico;

import ifpr.paranavai.jogo.entidade.FaseEntidade;
import ifpr.paranavai.jogo.dao.FaseEntidadeDao;
import ifpr.paranavai.jogo.dao.FaseEntidadeDaolmpl;
import java.util.List;

public class FaseEntidadeServico {
    private static FaseEntidadeDao dao = new FaseEntidadeDaolmpl();

    public static List<FaseEntidade> buscarTodos() {
        return dao.buscarTodos();
    }

    public static FaseEntidade buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    public static void inserir(FaseEntidade faseEntidade) {
        dao.inserir(faseEntidade);
    }

    public static void atualizar(FaseEntidade faseEntidade) {
        dao.atualizar(faseEntidade);
    }

    public static void excluir(FaseEntidade faseEntidade) {
        dao.excluir(faseEntidade);
    }
    
}
