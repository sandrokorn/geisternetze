package de.iu.ghostnet.dao;

import de.iu.ghostnet.model.GhostNet;
import de.iu.ghostnet.model.Person;
import de.iu.ghostnet.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class GhostNetDAO {

    public void saveGhostNet(GhostNet net) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(net);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Fehler beim Speichern des Geisternetzes: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void updateGhostNet(GhostNet net) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(net);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Fehler beim Aktualisieren des Geisternetzes: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<GhostNet> getAllNets() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<GhostNet> query = em.createQuery("SELECT g FROM GhostNet g", GhostNet.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Person findOrCreatePerson(String name, String tel) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            // Check ob es die Person anhand von Name und Telefonnummer schon gibt
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.name = :name AND p.telefonnummer = :tel", Person.class);
            query.setParameter("name", name);
            query.setParameter("tel", tel);
            
            List<Person> results = query.getResultList();
            if (!results.isEmpty()) {
                // System.out.println("Person existiert schon!");
                return results.get(0);
            }
            
            // Neue Person anlegen, falls nicht gefunden
            Person p = new Person();
            p.setName(name);
            p.setTelefonnummer(tel);
            
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
            
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Fehler beim Suchen/Erstellen der Person: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
}