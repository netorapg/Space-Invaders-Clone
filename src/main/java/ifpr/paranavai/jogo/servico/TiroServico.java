package ifpr.paranavai.jogo.servico;
import ifpr.paranavai.jogo.modelo.Tiro;
import ifpr.paranavai.jogo.dao.TiroDao;
import ifpr.paranavai.jogo.dao.TiroDaolmpl;
import java.util.List;

public class TiroServico {
    private static TiroDao dao = new TiroDaolmpl();
    
    public static List<Tiro> buscarTodos() {
        return dao.buscarTodos();
    }

    public static Tiro buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    public static void inserir(Tiro tiro) {
        dao.inserir(tiro);
    }

    public static void atualizar(Tiro tiro) {
        dao.atualizar(tiro);
    }

    public static void excluir(Tiro tiro) {
        dao.excluir(tiro);
    }
}
