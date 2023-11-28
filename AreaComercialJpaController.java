/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.AreaComercial;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Beguelin
 */
public class AreaComercialJpaController implements Serializable {

    public AreaComercialJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
       
  public AreaComercialJpaController(){
    emf =Persistence.createEntityManagerFactory("com.mycompany_tpIntegradorJavaIntermedio_jar_1.0-SNAPSHOTPU");
    }
  
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AreaComercial areaComercial) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(areaComercial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AreaComercial areaComercial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            areaComercial = em.merge(areaComercial);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = areaComercial.getIdAreaComercial();
                if (findAreaComercial(id) == null) {
                    throw new NonexistentEntityException("The areaComercial with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AreaComercial areaComercial;
            try {
                areaComercial = em.getReference(AreaComercial.class, id);
                areaComercial.getIdAreaComercial();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The areaComercial with id " + id + " no longer exists.", enfe);
            }
            em.remove(areaComercial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AreaComercial> findAreaComercialEntities() {
        return findAreaComercialEntities(true, -1, -1);
    }

    public List<AreaComercial> findAreaComercialEntities(int maxResults, int firstResult) {
        return findAreaComercialEntities(false, maxResults, firstResult);
    }

    private List<AreaComercial> findAreaComercialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AreaComercial.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AreaComercial findAreaComercial(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AreaComercial.class, id);
        } finally {
            em.close();
        }
    }

    public int getAreaComercialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AreaComercial> rt = cq.from(AreaComercial.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
