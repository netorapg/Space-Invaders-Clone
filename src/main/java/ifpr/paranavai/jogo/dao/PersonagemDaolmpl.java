package ifpr.paranavai.jogo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ifpr.paranavai.jogo.conexao.HibernateUtil;
import ifpr.paranavai.jogo.modelo.Personagem;

public class PersonagemDaolmpl implements PersonagemDao {
    private Session sessao;

    public PersonagemDaolmpl() {
        this.sessao = HibernateUtil.getSession();
    }

    @Override
    public List<Personagem> buscarTodos() {
        Query<Personagem> query = this.sessao.createQuery("from Personagem",
        Personagem.class);
            List<Personagem> personagens = query.getResultList();
            return personagens;
    }

    @Override
    public Personagem buscarPorId(Integer id) {
        return this.sessao.find(Personagem.class, id);
    }

    @Override
    public void inserir(Personagem personagem) {
        try {
            sessao.beginTransaction();
            sessao.persist(personagem);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(Personagem personagem) {
        try {
            sessao.beginTransaction();
            sessao.remove(personagem);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Personagem personagem) {
        try{
            sessao.beginTransaction();
            sessao.persist(personagem);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
}
}
