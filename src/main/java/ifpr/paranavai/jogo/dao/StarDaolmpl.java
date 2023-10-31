package ifpr.paranavai.jogo.dao;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ifpr.paranavai.jogo.conexao.HibernateUtil;
import ifpr.paranavai.jogo.modelo.Star;

public class StarDaolmpl implements StarDao {
    private Session sessao;

    public StarDaolmpl() {
        this.sessao = HibernateUtil.getSession();
    }

    @Override
    public List<Star> buscarTodos() {
        Query<Star> query = this.sessao.createQuery("from Star",
        Star.class);
            List<Star> stars = query.getResultList();
            return stars;
    }

    @Override
    public Star buscarPorId(Integer id) {
        return this.sessao.find(Star.class, id);
    }

    @Override
    public void inserir(Star star) {
        try {
            sessao.beginTransaction();
            sessao.persist(star);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(Star star) {
        try {
            sessao.beginTransaction();
            sessao.remove(star);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Star star) {
    try{
       sessao.beginTransaction();
       sessao.persist(star);
       sessao.getTransaction().commit();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
