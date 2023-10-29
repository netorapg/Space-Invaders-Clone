package ifpr.paranavai.jogo.dao;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ifpr.paranavai.jogo.conexao.HibernateUtil;
import ifpr.paranavai.jogo.modelo.SuperTiro;

public class SuperTiroDaolmpl implements SuperTiroDao {
    private Session sessao;

    public SuperTiroDaolmpl() {
        this.sessao = HibernateUtil.getSession();
    }

    @Override
    public List<SuperTiro> buscarTodos() {
        Query<SuperTiro> query = this.sessao.createQuery("from superTiro", 
        SuperTiro.class);
        List<SuperTiro> superTiro = query.getResultList();
        return superTiro;
    }

    @Override
    public SuperTiro buscarPorId(Integer id) {
        return this.sessao.find(SuperTiro.class, id);
    }

    @Override
    public void inserir(SuperTiro superTiro) {
        try {
            sessao.beginTransaction();
            sessao.persist(superTiro);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(SuperTiro superTiro) {
        try {
            sessao.beginTransaction();
            sessao.remove(superTiro);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(SuperTiro superTiro){
        try{
        sessao.beginTransaction();
        sessao.persist(superTiro);
        sessao.getTransaction().commit();
    } catch (Exception e) {
        e.printStackTrace();
    }
}    
}
