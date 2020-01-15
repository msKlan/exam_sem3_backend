package facades;

import dto.GenreDTO;
import entities.Actor;
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
import utils.EMF_Creator;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

/**
 *
 * @author Klan
 */
@Disabled
public class GenreFacadeTest {

    private static EntityManagerFactory emf;
    private static GenreFacade facade;
    private static Movie m1;
    private static GenreDTO g1, g2, g3;

    public GenreFacadeTest() {
    }

    @BeforeAll
    public static void setUpClassV2() {
         emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,
         Strategy.DROP_AND_CREATE);
 //       emf = EMF_Creator.createEntityManagerFactory("pu", "jdbc:mysql://localhost3307/exam_prep1_test", "dev", "ax2",
 //               EMF_Creator.Strategy.DROP_AND_CREATE);
        facade = GenreFacade.getGenreFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
    }

//    @BeforeEach
//    public void setUp() {
//        EntityManager em = emf.createEntityManager();
//        g1 = new Genre("thriller");
//        g2 = new Genre("documentary");
//
//        try {
//            em.getTransaction().begin();
//            em.createNamedQuery("Genre.deleteAllRows").executeUpdate();
//            em.persist(g1);
//            em.persist(g2);
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
//    public void testgetGenreByID() {
//        assertEquals(g1, facade.getGenre(g1.getId()));
//
//    }
//
//    @Test
//    public void testAddGenre() {
//        EntityManager em = emf.createEntityManager();
//
//        g1 = new GenreDTO("cartoon");
//        // GenreDTO exp = facade.addHubby(g3);
//
//        try {
//            em.getTransaction().begin();
//            em.createNamedQuery("Genre.deleteAllRows").executeUpdate();
//            em.persist(a1);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//
//        GenreDTO actual = new GenreDTO(em.find(Actor.class, exp.getId()));
//
//        assertEquals(exp, actual);
//    }
//
//    @Test
//    public void testgetAllGenres() {
//
//        assertEquals(2, facade.getAllGenres().getAll().size());
//    }
//
//    @Test
//    public void testRemovegenre() {
//
//        facade.removeGenre(h1.getId());
//        int exp = 1;
//        int actual = facade.getAllGenres().getAll().size();
//        assertEquals(exp, actual);
//    }
//
//    @Test
//    public void testEditGenre() {
//        EntityManager em = emf.createEntityManager();
//
//        g1.setName("Animated show");
//        GenreDTO genre = new GenreDTO(h1);
//
//        GenreDTO exp = facade.editGenre(genre);
//        GenreDTO actual = new GenreDTO(em.find(Actor.class, h1.getId()));
//
//        assertEquals(exp, actual);
//    }

}
