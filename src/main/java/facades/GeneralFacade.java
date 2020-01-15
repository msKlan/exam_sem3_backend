/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.MovieDTO;
import dto.MoviesDTO;
import dto_OUT.GenreDTO_OUT;
import dto_OUT.GenresDTO_OUT;
import dto_OUT.ActorDTO_OUT;
import dto_OUT.ActorsDTO_OUT;
import dto_OUT.DirectorDTO_OUT;
import dto_OUT.DirectorsDTO_OUT;
import dto_OUT.MovieDTO_OUT;
import dto_OUT.MoviesDTO_OUT;

import entities.Actor;
import entities.Director;
import entities.Genre;
import entities.Movie;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * @author Klan
 */
public class GeneralFacade {

    private static GeneralFacade instance;
    private static EntityManagerFactory emf;

    public static GeneralFacade getGeneralFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new GeneralFacade();
        }
        return instance;
    }

    public MoviesDTO getAllMoviesByGenre(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m JOIN m.genres g WHERE g.name = :GenreName",
                    Movie.class);

            List<Movie> list = query.setParameter("GenreName", name).getResultList();

            return new MoviesDTO(list);
        } finally {
            em.close();
        }
    }

    public MoviesDTO getAllMoviesByActor(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m JOIN m.actors a WHERE a.name = :ActorName",
                    Movie.class);

            List<Movie> list = query.setParameter("ActorName", name).getResultList();

            return new MoviesDTO(list);
        } finally {
            em.close();
        }
    }

    public MoviesDTO getAllMoviesByDirector(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Movie> query = em
                    .createQuery("SELECT m FROM Movie m JOIN m.directors d WHERE d.name = :DirectorName", Movie.class);

            List<Movie> list = query.setParameter("DirectorName", name).getResultList();

            return new MoviesDTO(list);
        } finally {
            em.close();
        }
    }

    public MovieDTO_OUT addMovie(MovieDTO_OUT movie) {
        EntityManager em = emf.createEntityManager();
        Movie m = new Movie(movie.getTitle(), movie.getYear());
        System.out.println("Movie M: " + m);

        for (GenreDTO_OUT g : movie.getGenres()) {
            String name = g.getName();
            Genre Genre = new Genre(name);
            System.out.println("Genres: " + Genre);
            m.setGenre(Genre);
        }

        for (ActorDTO_OUT a : movie.getActors()) {
            String name = a.getName();
            String about = a.getAbout();
            Actor actor = new Actor(name, about);
            System.out.println("Actors: " + actor);
            m.setActor(actor);
        }

        for (DirectorDTO_OUT d : movie.getDirectors()) {
            String name = d.getName();
            String about = d.getAbout();
            Director director = new Director(name, about);
            System.out.println("Directors: " + director);
            m.setDirector(director);
        }

        try {
            em.getTransaction().begin();
            em.persist(m);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new MovieDTO_OUT(m);
    }

    public static void main(String[] args) {

    }

}
