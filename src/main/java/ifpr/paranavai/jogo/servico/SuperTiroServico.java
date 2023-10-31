package ifpr.paranavai.jogo.servico;
import ifpr.paranavai.jogo.modelo.SuperTiro;
import ifpr.paranavai.jogo.dao.SuperTiroDao;
import ifpr.paranavai.jogo.dao.SuperTiroDaolmpl;
import java.util.List;
public class SuperTiroServico {
    private static SuperTiroDao dao = new SuperTiroDaolmpl();

    public static List<SuperTiro> buscarTodos() {
        return dao.buscarTodos();
    }

    public static SuperTiro buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    public static void inserir(SuperTiro superTiro) {
        dao.inserir(superTiro);
    }

    public static void atualizar(SuperTiro superTiro) {
        dao.atualizar(superTiro);
    }

    public static void excluir(SuperTiro superTiro) {
        dao.excluir(superTiro);
    }
}
