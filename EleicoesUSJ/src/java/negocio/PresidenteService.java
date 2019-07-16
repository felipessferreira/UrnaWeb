package negocio;

import entidades.Presidente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PresidenteService {

    EntityManager em = Persistence.createEntityManagerFactory("EleicoesUsjPU").createEntityManager();

    public List<Presidente> buscarPresidentes() {

        em.getEntityManagerFactory().getCache().evictAll();
        em.clear();
        Query consulta = em.createQuery("select p from Presidente p order by p.votos desc");

        List<Presidente> presidentes = consulta.getResultList();

        return presidentes;
    }
    public void votarPresidente(int numero){
        try{
        em.getEntityManagerFactory().getCache().evictAll();
        em.clear();
        Query consulta = em.createQuery("Select p from Presidente p where p.numero= :numero");
        consulta.setParameter("numero", numero);
        Presidente presidente = (Presidente) consulta.getSingleResult();
        presidente.setVotos(presidente.getVotos() + 1);
        em.getTransaction().begin();
        em.merge(presidente);
        em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
