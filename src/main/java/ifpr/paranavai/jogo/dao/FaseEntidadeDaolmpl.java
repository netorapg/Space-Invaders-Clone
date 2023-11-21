package ifpr.paranavai.jogo.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ifpr.paranavai.jogo.conexao.HibernateUtil;
import ifpr.paranavai.jogo.entidade.FaseEntidade;

public class FaseEntidadeDaolmpl implements FaseEntidadeDao {
    private Session sessao;

    public FaseEntidadeDaolmpl() {
        this.sessao = HibernateUtil.getSession();
    }

    @Override
    public List<FaseEntidade> buscarTodos() {
        Query<FaseEntidade> query = this.sessao.createQuery("from FaseEntidade",
        FaseEntidade.class);
            List<FaseEntidade> faseEntidade = query.getResultList();
            return faseEntidade;
    }

    @Override
    public FaseEntidade buscarPorId(Integer id) {
        return this.sessao.find(FaseEntidade.class, id);
    }

    @Override
    public void inserir(FaseEntidade faseEntidade) {
        try {
            sessao.beginTransaction();
            sessao.persist(faseEntidade);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(FaseEntidade faseEntidade) {
        try {
            sessao.beginTransaction();
            sessao.remove(faseEntidade);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(FaseEntidade faseEntidade) {
    try{
       sessao.beginTransaction();
       sessao.persist(faseEntidade);
       sessao.getTransaction().commit();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }  
}
