package negocio;

import entidades.Prefeito;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PrefeitoService {

    EntityManager em = Persistence.createEntityManagerFactory("EleicoesUsjPU").createEntityManager();

    public List<Prefeito> buscarPrefeitos() {

        em.getEntityManagerFactory().getCache().evictAll();
        em.clear();

        Query consulta = em.createQuery("select p from Prefeito p order by p.votos desc");
        List<Prefeito> prefeitos = consulta.getResultList();

        return prefeitos;
    }

    public void votarPrefeito(Integer numero) {
        try{
        em.getEntityManagerFactory().getCache().evictAll();
        em.clear();
        Query consulta = em.createQuery("Select p from Prefeito p where p.numero= :numero");
        consulta.setParameter("numero", numero);
        Prefeito prefeito = (Prefeito) consulta.getSingleResult();
        prefeito.setVotos(prefeito.getVotos() + 1);
        em.getTransaction().begin();
        em.merge(prefeito);
        em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
