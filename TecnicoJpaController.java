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
import logica.Especialidad;
import logica.RRHH;
import logica.Tecnico;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Beguelin
 */
public class TecnicoJpaController implements Serializable {

    public TecnicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public TecnicoJpaController(){
    emf =Persistence.createEntityManagerFactory("com.mycompany_tpIntegradorJavaIntermedio_jar_1.0-SNAPSHOTPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tecnico tecnico) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Especialidad especialidad = tecnico.getEspecialidad();
            if (especialidad != null) {
                especialidad = em.getReference(especialidad.getClass(), especialidad.getIdEspecialidad());
                tecnico.setEspecialidad(especialidad);
            }
            RRHH rrhh = tecnico.getRrhh();
            if (rrhh != null) {
                rrhh = em.getReference(rrhh.getClass(), rrhh.getIdRRHH());
                tecnico.setRrhh(rrhh);
            }
            em.persist(tecnico);
            if (especialidad != null) {
                especialidad.getTecnico().add(tecnico);
                especialidad = em.merge(especialidad);
            }
            if (rrhh != null) {
                rrhh.getTecnico().add(tecnico);
                rrhh = em.merge(rrhh);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tecnico tecnico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tecnico persistentTecnico = em.find(Tecnico.class, tecnico.getIdTecnico());
            Especialidad especialidadOld = persistentTecnico.getEspecialidad();
            Especialidad especialidadNew = tecnico.getEspecialidad();
            RRHH rrhhOld = persistentTecnico.getRrhh();
            RRHH rrhhNew = tecnico.getRrhh();
            if (especialidadNew != null) {
                especialidadNew = em.getReference(especialidadNew.getClass(), especialidadNew.getIdEspecialidad());
                tecnico.setEspecialidad(especialidadNew);
            }
            if (rrhhNew != null) {
                rrhhNew = em.getReference(rrhhNew.getClass(), rrhhNew.getIdRRHH());
                tecnico.setRrhh(rrhhNew);
            }
            tecnico = em.merge(tecnico);
            if (especialidadOld != null && !especialidadOld.equals(especialidadNew)) {
                especialidadOld.getTecnico().remove(tecnico);
                especialidadOld = em.merge(especialidadOld);
            }
            if (especialidadNew != null && !especialidadNew.equals(especialidadOld)) {
                especialidadNew.getTecnico().add(tecnico);
                especialidadNew = em.merge(especialidadNew);
            }
            if (rrhhOld != null && !rrhhOld.equals(rrhhNew)) {
                rrhhOld.getTecnico().remove(tecnico);
                rrhhOld = em.merge(rrhhOld);
            }
            if (rrhhNew != null && !rrhhNew.equals(rrhhOld)) {
                rrhhNew.getTecnico().add(tecnico);
                rrhhNew = em.merge(rrhhNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = tecnico.getIdTecnico();
                if (findTecnico(id) == null) {
                    throw new NonexistentEntityException("The tecnico with id " + id + " no longer exists.");
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
            Tecnico tecnico;
            try {
                tecnico = em.getReference(Tecnico.class, id);
                tecnico.getIdTecnico();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tecnico with id " + id + " no longer exists.", enfe);
            }
            Especialidad especialidad = tecnico.getEspecialidad();
            if (especialidad != null) {
                especialidad.getTecnico().remove(tecnico);
                especialidad = em.merge(especialidad);
            }
            RRHH rrhh = tecnico.getRrhh();
            if (rrhh != null) {
                rrhh.getTecnico().remove(tecnico);
                rrhh = em.merge(rrhh);
            }
            em.remove(tecnico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tecnico> findTecnicoEntities() {
        return findTecnicoEntities(true, -1, -1);
    }

    public List<Tecnico> findTecnicoEntities(int maxResults, int firstResult) {
        return findTecnicoEntities(false, maxResults, firstResult);
    }

    private List<Tecnico> findTecnicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tecnico.class));
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

    public Tecnico findTecnico(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tecnico.class, id);
        } finally {
            em.close();
        }
    }

    public int getTecnicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tecnico> rt = cq.from(Tecnico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    void destroyTecnico(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void editTecnico(Tecnico tecnico) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
