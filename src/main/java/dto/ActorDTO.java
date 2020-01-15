/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Actor;

import entities.Movie;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Klan
 */
public class ActorDTO {

    @Schema(name = "ActorInfo")
    private int id;
    private String name;
    private String about;
    private List<MovieDTO> movies = new ArrayList();

    public ActorDTO(Actor actor) {
        if (actor.getId() != null) {
            this.id = actor.getId();
        }
        this.name = actor.getName();
        this.about = actor.getAbout();

            for (Movie movie : actor.getMovies()) {
                this.movies.add(new MovieDTO(movie));
            }
    }

    public ActorDTO() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAbout() {
        return about;
    }

    public List<MovieDTO> getMovie() {
        return movies;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setMovie(List<MovieDTO> movie) {
        this.movies = movies;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.about);
        hash = 67 * hash + Objects.hashCode(this.movies);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ActorDTO other = (ActorDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.about, other.about)) {
            return false;
        }
        if (!Objects.equals(this.movies, other.movies)) {
            return false;
        }

        return true;
    }

}
