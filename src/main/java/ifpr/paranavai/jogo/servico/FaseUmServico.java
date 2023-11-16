package ifpr.paranavai.jogo.servico;

import ifpr.paranavai.jogo.modelo.FaseUm;
import ifpr.paranavai.jogo.dao.FaseUmDao;
import ifpr.paranavai.jogo.dao.FaseUmDaolmpl;
import java.util.List;

public class FaseUmServico {
    private static FaseUmDao dao = new FaseUmDaolmpl();

    public static List<FaseUm> buscarTodos() {
        return dao.buscarTodos();
    }

    public static FaseUm buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    public static void inserir(FaseUm faseUm) {
        dao.inserir(faseUm);
    }

    public static void atualizar(FaseUm faseUm) {
        dao.atualizar(faseUm);
    }

    public static void excluir(FaseUm faseUm) {
        dao.excluir(faseUm);
    }
    
}
