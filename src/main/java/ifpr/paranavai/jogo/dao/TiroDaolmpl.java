package ifpr.paranavai.jogo.dao;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ifpr.paranavai.jogo.conexao.HibernateUtil;
import ifpr.paranavai.jogo.modelo.Tiro;

public class TiroDaolmpl implements TiroDao {
    private Session sessao;

    public TiroDaolmpl() {
        this.sessao = HibernateUtil.getSession();
    }
    
    @Override
    public List<Tiro> buscarTodos() {
        Query<Tiro> query = this.sessao.createQuery("from Tiro", Tiro.class);
        List<Tiro> tiros = query.getResultList();
        return tiros;
    }

    @Override
    public Tiro buscarPorId(Integer id) {
        return this.sessao.find(Tiro.class, id);
    }

    @Override
    public void inserir(Tiro tiro) {
        try {
            sessao.beginTransaction();
            sessao.persist(tiro);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(Tiro tiro) {
        try {
            sessao.beginTransaction();
            sessao.remove(tiro);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Tiro tiro) {
        try {
            sessao.beginTransaction();
            sessao.persist(tiro);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
