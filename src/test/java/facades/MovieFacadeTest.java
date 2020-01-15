package facades;

import dto.MovieDTO;
import utils.EMF_Creator;
import entities.Movie;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;
import entities.Actor;
import entities.Director;
import entities.Genre;


/**
 *
 * @author Klan
 */
@Disabled
public class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;
    private static Movie p1, p2, p3;
    private static Genre g1;
    private static Director d1;
    private static Actor a1;

    public MovieFacadeTest() {
    }

    @BeforeAll
    public static void setUpClassV2() {
         emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,
         Strategy.DROP_AND_CREATE);
        facade = MovieFacade.getMovieFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    // TODO -- Make sure to change the script below to use YOUR OWN entity class
//    @BeforeEach
//    public void setUp() {
//        EntityManager em = emf.createEntityManager();
//        m1 = new Movie("title1", "1234");
//        m2 = new Movie("title2", "5678");
//        a1 = new Actor("actor1", "desc1A");
//        g1 = new Genre("genre1");
//        g1 = new Director("director1", "desc1D");
//
//
//        try {
//            em.getTransaction().begin();
//            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
//            em.persist(m1);
//            em.persist(m2);
//            em.persist(a1);
//            em.persist(g1);
//            em.persist(d1);
//
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
//
//    @AfterEach
//    public void tearDown() {
//    }
//
//    @Test
//    public void testgetMovieByID() {
//        MovieDTO exp = new MovieDTO(p1);
//        assertEquals(exp, facade.getMovie(p1.getId()));
//
//    }
//
//    @Test
//    public void testgetAllMovies() {
//        int exp = facade.getAllMovies().getAll().size();
//        exp = 2;
//
//        assertEquals(exp, facade.getAllMovies().getAll().size());
//    }
//
//    @Test
//    public void testAddMovie() {
//        EntityManager em = emf.createEntityManager();
//        m3 = new Movie("movie3", "7653");
//        // p3DTO = new MovieDTO(p3);
//        m3 = new MovieDTO(m3);
//
//        MovieDTO exp = facade.addMovie(m3);
//
//        MovieDTO actual = new MovieDTO(em.find(Movie.class, exp.getId()));
//        assertEquals(exp, actual);
//    }
//
//    @Test
//    public void testDeleteMovie() {
//        int exp = facade.getAllMovies().getAll().size();
//        facade.deleteMovie(p1.getId());
//        exp -= 1;
//        assertEquals(exp, facade.getAllMovies().getAll().size());
//    }
//
//    @Test
//    public void testEditMovie() {
//        EntityManager em = emf.createEntityManager();
//        p1.setTitle("newtitle");
//        MovieDTO pDTO = new MovieDTO(p1);
//        MovieDTO exp = facade.editMovie(pDTO);
//        MovieDTO actual = new MovieDTO(em.find(Movie.class, p1.getId()));
//
//        assertEquals(exp, actual);
//    }

}
