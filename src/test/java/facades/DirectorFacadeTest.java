package facades;

import dto.DirectorDTO;
import dto.GenreDTO;
import entities.Actor;
import entities.Director;
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
public class DirectorFacadeTest {

    private static EntityManagerFactory emf;
    private static DirectorFacade facade;
    private static Movie m1;
    private static DirectorDTO d1, d2, d3;

    public DirectorFacadeTest() {
    }

    @BeforeAll
    public static void setUpClassV2() {
         emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,
         Strategy.DROP_AND_CREATE);
 //       emf = EMF_Creator.createEntityManagerFactory("pu", "jdbc:mysql://localhost3307/exam_prep1_test", "dev", "ax2",
 //               EMF_Creator.Strategy.DROP_AND_CREATE);
        facade = DirectorFacade.getDirectorFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
    }

//    @BeforeEach
//    public void setUp() {
//        EntityManager em = emf.createEntityManager();
//        d1 = new Director("Director1", "desc1");
//        d2 = new Director("Director2", "desc2");
//
//
//        try {
//            em.getTransaction().begin();
//            em.createNamedQuery("Director.deleteAllRows").executeUpdate();
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

//    @Test
//    public void testgetDirectorByID() {
//        assertEquals(g1, facade.getDirector(g1.getId()));
//
//    }
//
//    @Test
//    public void testAddDirector() {
//        EntityManager em = emf.createEntityManager();
//
//        g1 = new DirectorDTO("Director1","desc1");
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
//        DirectorDTO actual = new DirectorDTO(em.find(Actor.class, exp.getId()));
//
//        assertEquals(exp, actual);
//    }
//
//    @Test
//    public void testgetAllDirectors() {
//
//        assertEquals(2, facade.getAllDirectors().getAll().size());
//    }
//
//    @Test
//    public void testRemoveDirector() {
//
//        facade.removeDirector(h1.getId());
//        int exp = 1;
//        int actual = facade.getAllDirectors().getAll().size();
//        assertEquals(exp, actual);
//    }
//
//    @Test
//    public void testEditDirector() {
//        EntityManager em = emf.createEntityManager();
//
//        g1.setName("Animated show");
//        DirectorDTO director = new DirectorDTO(h1);
//
//        DirectorDTO exp = facade.editDirector(director);
//        DirectorDTO actual = new DirectorDTO(em.find(Actor.class, h1.getId()));
//
//        assertEquals(exp, actual);
//    }

}
