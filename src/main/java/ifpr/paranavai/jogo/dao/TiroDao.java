package ifpr.paranavai.jogo.dao;
import java.util.List;
import ifpr.paranavai.jogo.modelo.Tiro;
public interface TiroDao {
    public List<Tiro> buscarTodos();
    public Tiro buscarPorId(Integer id);
    public void atualizar(Tiro tiro);
    public void excluir(Tiro tiro);
    public void inserir(Tiro tiro);
    
}
