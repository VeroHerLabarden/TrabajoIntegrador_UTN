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
import logica.Incidente;
import logica.Tecnico;
import logica.RRHH;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Beguelin
 */
public class IncidenteJpaController implements Serializable {

    public IncidenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public IncidenteJpaController(){
    emf =Persistence.createEntityManagerFactory("com.mycompany_tpIntegradorJavaIntermedio_jar_1.0-SNAPSHOTPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Incidente incidente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tecnico tecnico = incidente.getTecnico();
            if (tecnico != null) {
                tecnico = em.getReference(tecnico.getClass(), tecnico.getIdTecnico());
                incidente.setTecnico(tecnico);
            }
            RRHH rrhh = incidente.getRrhh();
            if (rrhh != null) {
                rrhh = em.getReference(rrhh.getClass(), rrhh.getIdRRHH());
                incidente.setRrhh(rrhh);
            }
            em.persist(incidente);
            if (tecnico != null) {
                Incidente oldIncidenteOfTecnico = tecnico.getIncidente();
                if (oldIncidenteOfTecnico != null) {
                    oldIncidenteOfTecnico.setTecnico(null);
                    oldIncidenteOfTecnico = em.merge(oldIncidenteOfTecnico);
                }
                tecnico.setIncidente(incidente);
                tecnico = em.merge(tecnico);
            }
            if (rrhh != null) {
                rrhh.getIncidente().add(incidente);
                rrhh = em.merge(rrhh);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Incidente incidente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Incidente persistentIncidente = em.find(Incidente.class, incidente.getIdIncidentes());
            Tecnico tecnicoOld = persistentIncidente.getTecnico();
            Tecnico tecnicoNew = incidente.getTecnico();
            RRHH rrhhOld = persistentIncidente.getRrhh();
            RRHH rrhhNew = incidente.getRrhh();
            if (tecnicoNew != null) {
                tecnicoNew = em.getReference(tecnicoNew.getClass(), tecnicoNew.getIdTecnico());
                incidente.setTecnico(tecnicoNew);
            }
            if (rrhhNew != null) {
                rrhhNew = em.getReference(rrhhNew.getClass(), rrhhNew.getIdRRHH());
                incidente.setRrhh(rrhhNew);
            }
            incidente = em.merge(incidente);
            if (tecnicoOld != null && !tecnicoOld.equals(tecnicoNew)) {
                tecnicoOld.setIncidente(null);
                tecnicoOld = em.merge(tecnicoOld);
            }
            if (tecnicoNew != null && !tecnicoNew.equals(tecnicoOld)) {
                Incidente oldIncidenteOfTecnico = tecnicoNew.getIncidente();
                if (oldIncidenteOfTecnico != null) {
                    oldIncidenteOfTecnico.setTecnico(null);
                    oldIncidenteOfTecnico = em.merge(oldIncidenteOfTecnico);
                }
                tecnicoNew.setIncidente(incidente);
                tecnicoNew = em.merge(tecnicoNew);
            }
            if (rrhhOld != null && !rrhhOld.equals(rrhhNew)) {
                rrhhOld.getIncidente().remove(incidente);
                rrhhOld = em.merge(rrhhOld);
            }
            if (rrhhNew != null && !rrhhNew.equals(rrhhOld)) {
                rrhhNew.getIncidente().add(incidente);
                rrhhNew = em.merge(rrhhNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = incidente.getIdIncidentes();
                if (findIncidente(id) == null) {
                    throw new NonexistentEntityException("The incidente with id " + id + " no longer exists.");
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
            Incidente incidente;
            try {
                incidente = em.getReference(Incidente.class, id);
                incidente.getIdIncidentes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The incidente with id " + id + " no longer exists.", enfe);
            }
            Tecnico tecnico = incidente.getTecnico();
            if (tecnico != null) {
                tecnico.setIncidente(null);
                tecnico = em.merge(tecnico);
            }
            RRHH rrhh = incidente.getRrhh();
            if (rrhh != null) {
                rrhh.getIncidente().remove(incidente);
                rrhh = em.merge(rrhh);
            }
            em.remove(incidente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Incidente> findIncidenteEntities() {
        return findIncidenteEntities(true, -1, -1);
    }

    public List<Incidente> findIncidenteEntities(int maxResults, int firstResult) {
        return findIncidenteEntities(false, maxResults, firstResult);
    }

    private List<Incidente> findIncidenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Incidente.class));
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

    public Incidente findIncidente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Incidente.class, id);
        } finally {
            em.close();
        }
    }

    public int getIncidenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Incidente> rt = cq.from(Incidente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    void destroyIncidente(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void editIncidente(Incidente incidente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
