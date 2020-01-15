/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Director;
import entities.Genre;
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
// @Schema(name = "MovieInfo")

public class MovieDTO {

    private int id;
    private String title;
    private Integer year;
    private Integer votes;

    public MovieDTO(Movie movie) {
        if (movie.getId() != null) {
            this.id = movie.getId();
        }
        this.title = movie.getTitle();
        this.year = movie.getYear();
        this.votes = movie.getVotes();
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

    public void setVotes(String email) {
        this.votes = votes;
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
        final MovieDTO other = (MovieDTO) obj;
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
        return true;
    }

}
