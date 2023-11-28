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
import logica.RRHH;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Beguelin
 */
public class RRHHJpaController implements Serializable {

    public RRHHJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public RRHHJpaController(){
    emf =Persistence.createEntityManagerFactory("com.mycompany_tpIntegradorJavaIntermedio_jar_1.0-SNAPSHOTPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RRHH RRHH) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(RRHH);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RRHH RRHH) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RRHH = em.merge(RRHH);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = RRHH.getIdRRHH();
                if (findRRHH(id) == null) {
                    throw new NonexistentEntityException("The rRHH with id " + id + " no longer exists.");
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
            RRHH RRHH;
            try {
                RRHH = em.getReference(RRHH.class, id);
                RRHH.getIdRRHH();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The RRHH with id " + id + " no longer exists.", enfe);
            }
            em.remove(RRHH);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RRHH> findRRHHEntities() {
        return findRRHHEntities(true, -1, -1);
    }

    public List<RRHH> findRRHHEntities(int maxResults, int firstResult) {
        return findRRHHEntities(false, maxResults, firstResult);
    }

    private List<RRHH> findRRHHEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RRHH.class));
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

    public RRHH findRRHH(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RRHH.class, id);
        } finally {
            em.close();
        }
    }

    public int getRRHHCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RRHH> rt = cq.from(RRHH.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
