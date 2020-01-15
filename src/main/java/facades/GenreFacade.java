package facades;

import dto.GenresDTO;
import dto.GenreDTO;
import entities.Genre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Klan
 */
public class GenreFacade {

    private static GenreFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private GenreFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static GenreFacade getGenreFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new GenreFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public GenreDTO getGenre(int id) {
        EntityManager em = emf.createEntityManager();
            Genre g = em.find(Genre.class, id);
        try {
            GenreDTO genre = new GenreDTO(g);
            return genre;
        } finally {
            em.close();
        }
    }

    public GenreDTO addGenre(GenreDTO g) {
        EntityManager em = emf.createEntityManager();
        Genre genre = new Genre(g.getName());
        try {
            em.getTransaction().begin();
            em.persist(genre);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new GenreDTO(genre);
    }
    
    public GenresDTO getAllGenres() {
        EntityManager em = emf.createEntityManager();
        
        try {
            List<Genre> list = em.createQuery("SELECT g FROM Genre g", Genre.class).getResultList();
            return new GenresDTO(list);
        } finally {
            em.close();
        }
    }

    public GenreDTO removeGenre(int id) {
        EntityManager em = emf.createEntityManager();
        Genre genre = em.find(Genre.class, id);
        try {
            em.getTransaction().begin();
            em.remove(genre);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new GenreDTO(genre);
    }

    
        public GenreDTO editGenre(GenreDTO g) {
        EntityManager em = emf.createEntityManager();
        Genre genre = new Genre(g.getName());
        genre.setId(g.getId());
        try {
            em.getTransaction().begin();
            em.merge(genre);
            em.getTransaction().commit();
            
        } finally {
            em.close();
        }
        return new GenreDTO(genre);
    }
        

}
