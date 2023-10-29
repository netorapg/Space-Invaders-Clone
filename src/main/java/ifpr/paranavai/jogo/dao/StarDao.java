package ifpr.paranavai.jogo.dao;
import java.util.List;
import ifpr.paranavai.jogo.modelo.Star;
public interface StarDao {
    public List<Star> buscarTodos();
    public Star buscarPorId(Integer id);
    public void atualizar(Star star);
    public void excluir(Star star);
    public void inserir(Star star);
}
