package facades;

import dto.ActorDTO;
import dto.ActorsDTO;
import entities.Actor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * @author Klan
 */
public class ActorFacade {

    private static ActorFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private ActorFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static ActorFacade getActorFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ActorFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ActorDTO getActor(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            Actor ad = em.find(Actor.class, id);
            return new ActorDTO(ad);
        } finally {
            em.close();
        }
    }

    public ActorDTO addActor(ActorDTO a) {
        EntityManager em = emf.createEntityManager();
        Actor actor = new Actor(a.getName(), a.getAbout());
        try {
            em.getTransaction().begin();
            em.persist(actor);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new ActorDTO(actor);
    }

    public ActorsDTO getAllActors() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Actor> list = em.createQuery("SELECT a FROM Actor a", Actor.class).getResultList();
            return new ActorsDTO(list);
        } finally {
            em.close();
        }
    }

    public ActorDTO removeActor(int id) {
        EntityManager em = emf.createEntityManager();
        Actor actor = em.find(Actor.class, id);
        try {
            em.getTransaction().begin();
            em.remove(actor);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new ActorDTO(actor);
    }

    public ActorDTO editActor(ActorDTO a) {
        EntityManager em = emf.createEntityManager();
        Actor actor = new Actor(a.getAbout(), a.getName());
        actor.setId(a.getId());
        try {
            em.getTransaction().begin();
            em.merge(actor);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return new ActorDTO(actor);
    }

}
