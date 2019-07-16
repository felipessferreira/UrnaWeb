package negocio;

import entidades.Eleitor;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class EleitorService {

    EntityManager em = Persistence.createEntityManagerFactory("EleicoesUsjPU").createEntityManager();

    public Eleitor buscarEleitor(Eleitor e) {
        em.getEntityManagerFactory().getCache().evictAll();
        em.clear();

        Query consulta = em.createQuery("select e from Eleitor e where e.cpf=" + e.getCpf() + " and e.senha=" + e.getSenha());
        Eleitor eleitor = (Eleitor) consulta.getSingleResult();
        return eleitor;
    }
    public Boolean cadastrar(Eleitor e){
        try{
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    public Eleitor votou(Eleitor e){
        e.setVotou(1);
        em.getTransaction().begin();
        em.merge(e);
        em.getTransaction().commit();
        
        return e;
    }
    public Eleitor verificarVotou(Eleitor e){
        em.getTransaction().begin();
        em.merge(e);
        em.getTransaction().commit();
        
        return e;
    }
}
