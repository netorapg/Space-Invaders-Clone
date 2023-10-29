package ifpr.paranavai.jogo.servico;

import ifpr.paranavai.jogo.modelo.Inimigo;
import ifpr.paranavai.jogo.dao.InimigoDao;
import ifpr.paranavai.jogo.dao.InimigoDaolmpl;
import java.util.List;

public class InimigoServico {
    private static InimigoDao dao = new InimigoDaolmpl();

    public static List<Inimigo> buscarTodos() {
        return dao.buscarTodos();
    }

    public static Inimigo buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    public static void inserir(Inimigo inimigo) {
        dao.inserir(inimigo);
    }

    public static void atualizar(Inimigo inimigo) {
        dao.atualizar(inimigo);
    }

    public static void excluir(Inimigo inimigo) {
        dao.excluir(inimigo);
    }
}
