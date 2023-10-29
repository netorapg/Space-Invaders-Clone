package ifpr.paranavai.jogo.servico;

import ifpr.paranavai.jogo.modelo.Star;
import ifpr.paranavai.jogo.dao.StarDao;
import ifpr.paranavai.jogo.dao.StarDaolmpl;
import java.util.List;

public class StarServico {
    private static StarDao dao = new StarDaolmpl();
    
    public static List<Star> buscarTodos() {
        return dao.buscarTodos();
    }

    public static Star buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    public static void inserir(Star star) {
        dao.inserir(star);
    }

    public static void atualizar(Star star) {
        dao.atualizar(star);
    }

    public static void excluir(Star star) {
        dao.excluir(star);
    }    
}
