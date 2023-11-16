package ifpr.paranavai.jogo.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ifpr.paranavai.jogo.conexao.HibernateUtil;
import ifpr.paranavai.jogo.modelo.FaseUm;

public class FaseUmDaolmpl implements FaseUmDao {
    private Session sessao;

    public FaseUmDaolmpl() {
        this.sessao = HibernateUtil.getSession();
    }

    @Override
    public List<FaseUm> buscarTodos() {
        Query<FaseUm> query = this.sessao.createQuery("from FaseUm",
        FaseUm.class);
            List<FaseUm> faseUm = query.getResultList();
            return faseUm;
    }

    @Override
    public FaseUm buscarPorId(Integer id) {
        return this.sessao.find(FaseUm.class, id);
    }

    @Override
    public void inserir(FaseUm faseUm) {
        try {
            sessao.beginTransaction();
            sessao.persist(faseUm);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(FaseUm faseUm) {
        try {
            sessao.beginTransaction();
            sessao.remove(faseUm);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(FaseUm faseUm) {
    try{
       sessao.beginTransaction();
       sessao.persist(faseUm);
       sessao.getTransaction().commit();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    
}
