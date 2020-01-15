/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

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
 * 
 */
@Disabled
public class ActorFacadeTest {

    private static EntityManagerFactory emf;
    private static ActorFacade facade;
    private static Movie m1;
    private static Actor a1, a2, a3;

    public ActorFacadeTest() {
    }

    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = ActorFacade.getActorFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        a1 = new Actor("Dupon", "Brother to Dupond");
        a2 = new Actor("Dupond", "Brother to Dupon");
        m1 = new Movie("300 burly naked men", 2007);

        a1.addMovie(m1);
        a2.addMovie(m1);

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Actor.deleteAllRows").executeUpdate();
            em.persist(a1);
            em.persist(a2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testgetActorByID() {
        assertEquals(a3, facade.getActor(a3.getId()));
    }

    @Test
    public void testAddActor() {
        EntityManager em = emf.createEntityManager();

        a3 = new Actor("xxxx", "xBrother to Dupon");
        a3.addMovie(m1);

        try {
            em.getTransaction().begin();
            em.persist(a1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

//        assertEquals(a4, new Actor(em.find(Actor.class, a3.getId())));
    }

    @Test
    public void testgetAllActors() {

        assertEquals(2, facade.getAllActors().getAll().size());
    }

    @Test
    public void testRemoveActor() {

//        facade.testRemoveActor(a1.getId());
        int exp = 1;
        int actual = facade.getAllActors().getAll().size();
        assertEquals(exp, actual);
    }

    @Test
    public void testEditActor() {
        EntityManager em = emf.createEntityManager();

        a1.setName("Dupond't");
//        Actor Actor = new Actor(a1);

//        Actor exp = facade.editActor(actor);
//        Actor actual = new Actor(em.find(Actor.class, a1.getId()));

//        assertEquals(exp, actual);
    }

}
