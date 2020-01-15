package facades;

import dto.MovieDTO;
import dto.MoviesDTO;
import entities.Director;
import entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;

    // Private Constructor to ensure Singleton
    private MovieFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getMovieFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public MovieDTO getMovie(int id) {
        EntityManager em = emf.createEntityManager();
        Movie Movie = em.find(Movie.class, id);
        try {
            return new MovieDTO(Movie);
        } finally {
            em.close();
        }
    }

    public MoviesDTO getAllMovies() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Movie> list = em.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
            return new MoviesDTO(list);
        } finally {
            em.close();
        }
    }

    public MovieDTO addMovie(MovieDTO m) {
        EntityManager em = emf.createEntityManager();
        Movie movie = new Movie(m.getTitle(), m.getYear());
        try {
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new MovieDTO(movie);
    }

    public MovieDTO deleteMovie(int id) {
        EntityManager em = emf.createEntityManager();
        Movie Movie = em.find(Movie.class, id);
        try {
            em.getTransaction().begin();
            em.remove(Movie);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new MovieDTO(Movie);
    }

    public MovieDTO editMovie(MovieDTO m) {
        EntityManager em = emf.createEntityManager();
        Movie movie = new Movie(m.getTitle(), m.getYear());
        
        movie.setId(m.getId());
        movie.setVotes(m.getVotes());
        try {
            em.getTransaction().begin();
            em.merge(movie);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return new MovieDTO(movie);
    }

}
