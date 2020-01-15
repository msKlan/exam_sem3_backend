/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto_OUT;

import dto.ActorDTO;
import dto.GenreDTO;
import dto.DirectorDTO;
import entities.Actor;
import entities.Genre;
import entities.Director;
import entities.Movie;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Klan
 */
public class MovieDTO_OUT {
    private int id;
    private String title;
    private Integer year;
    private Integer votes;
    private List<ActorDTO_OUT> actors = new ArrayList<>();
    private List<DirectorDTO_OUT> directors = new ArrayList<>();
    private List<GenreDTO_OUT> genres = new ArrayList<>();

    public MovieDTO_OUT() {
    }

    public MovieDTO_OUT(Movie movie) {
        if (movie.getId() != null) {
            this.id = movie.getId();
        }
        this.title = movie.getTitle();
        this.year = movie.getYear();
        this.votes = movie.getVotes();

        if(movie.getGenres() != null){
            for (Genre genre : movie.getGenres()) {
                this.genres.add(new GenreDTO_OUT(genre));
            }
        }

        if(movie.getActors() != null){
            for (Actor actor : movie.getActors()) {
                this.actors.add(new ActorDTO_OUT(actor));
            }
        }
        
        if(movie.getDirectors() != null){
            for (Director director : movie.getDirectors()) {
                this.directors.add(new DirectorDTO_OUT(director));
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public List<ActorDTO_OUT> getActors() {
        return actors;
    }

    public void setActors(List<ActorDTO_OUT> actors) {
        this.actors = actors;
    }
    
    public List<DirectorDTO_OUT> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorDTO_OUT> directors) {
        this.directors = directors;
    }
    
    public List<GenreDTO_OUT> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDTO_OUT> genres) {
        this.genres = genres;
    }
            
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.title);
        hash = 29 * hash + Objects.hashCode(this.year);
        hash = 29 * hash + Objects.hashCode(this.votes);
        hash = 29 * hash + Objects.hashCode(this.actors);
        hash = 29 * hash + Objects.hashCode(this.directors);
        hash = 29 * hash + Objects.hashCode(this.genres);
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
        final MovieDTO_OUT other = (MovieDTO_OUT) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        if (!Objects.equals(this.votes, other.votes)) {
            return false;
        }
        if (!Objects.equals(this.actors, other.actors)) {
            return false;
        }
        if (!Objects.equals(this.directors, other.directors)) {
            return false;
        }
        if (!Objects.equals(this.genres, other.genres)) {
            return false;
        }
        return true;
    }
    
    
}
