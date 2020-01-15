package facades;

import dto.DirectorDTO;
import dto.DirectorsDTO;
import entities.Director;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Klan
 */
public class DirectorFacade {

    private static DirectorFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private DirectorFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static DirectorFacade getDirectorFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new DirectorFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public DirectorDTO getDirector(int id) {
        EntityManager em = emf.createEntityManager();
            Director h = em.find(Director.class, id);
        try {
            DirectorDTO director = new DirectorDTO(h);
            return director;
        } finally {
            em.close();
        }
    }
    public DirectorDTO addDirector(DirectorDTO d) {
        EntityManager em = emf.createEntityManager();
        Director director = new Director(d.getName(), d.getAbout());
        try {
            em.getTransaction().begin();
            em.persist(director);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new DirectorDTO(director);
    }
    
    public DirectorsDTO getAllDirectors() {
        EntityManager em = emf.createEntityManager();
        
        try {
            List<Director> list = em.createQuery("SELECT d FROM Director d", Director.class).getResultList();
            return new DirectorsDTO(list);
        } finally {
            em.close();
        }
    }

    public DirectorDTO removeDirector(int id) {
        EntityManager em = emf.createEntityManager();
        Director director = em.find(Director.class, id);
        try {
            em.getTransaction().begin();
            em.remove(director);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new DirectorDTO(director);
    }

    
        public DirectorDTO editDirector(DirectorDTO d) {
        EntityManager em = emf.createEntityManager();
        Director director = new Director(d.getName(),d.getAbout());
        director.setId(d.getId());
        try {
            em.getTransaction().begin();
            em.merge(director);
            em.getTransaction().commit();
            
        } finally {
            em.close();
        }
        return new DirectorDTO(director);
    }
        

}
