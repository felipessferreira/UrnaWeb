package negocio;

import entidades.Governador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class GovernadorService {

    EntityManager em = Persistence.createEntityManagerFactory("EleicoesUsjPU").createEntityManager();

    public List<Governador> buscarGovernadores() {

        em.getEntityManagerFactory().getCache().evictAll();
        em.clear();

        Query consulta = em.createQuery("select g from Governador g order by g.votos desc");
        List<Governador> governadores = consulta.getResultList();

        return governadores;
    }

    public void votarGovernador(int numero) {
        try{
        em.getEntityManagerFactory().getCache().evictAll();
        em.clear();
        Query consulta = em.createQuery("Select g from Governador g where g.numero= :numero");
        consulta.setParameter("numero", numero);
        Governador governador = (Governador) consulta.getSingleResult();
        governador.setVotos(governador.getVotos() + 1);
        em.getTransaction().begin();
        em.merge(governador);
        em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
