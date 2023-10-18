package ifpr.paranavai.jogo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ifpr.paranavai.jogo.conexao.HibernateUtil;
import ifpr.paranavai.jogo.modelo.ElementoGrafico;

public class ElementoGraficoDaolmpl implements ElementoGraficoDao {
        private Session sessao;

        public ElementoGraficoDaolmpl() {
            this.sessao = HibernateUtil.getSession();
        }

        @Override
        public List<ElementoGrafico> buscarTodos() {
            Query<ElementoGrafico> query = this.sessao.createQuery("from ElementoGrafico",
            ElementoGrafico.class);
                List<ElementoGrafico> elementosGraficos = query.getResultList();
                return elementosGraficos;
        }
        
        @Override
        public ElementoGrafico buscarPorId(Integer id) {
            return this.sessao.find(ElementoGrafico.class, id);
        }

        @Override
        public void inserir(ElementoGrafico elementoGrafico) {
            try {
                sessao.beginTransaction();
                sessao.persist(elementoGrafico);
                sessao.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void excluir(ElementoGrafico elementoGrafico) {
            try {
                sessao.beginTransaction();
                sessao.remove(elementoGrafico);
                sessao.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void atualizar(ElementoGrafico elementoGrafico) {
            try{
                sessao.beginTransaction();
                sessao.persist(elementoGrafico);
                sessao.getTransaction().commit();
                } catch (Exception e) {
                 e.printStackTrace();
            }
        }
    }