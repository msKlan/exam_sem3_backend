package dto;

import entities.Genre;
import entities.Movie;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Klan
 */

public class GenreDTO {
    private Integer id;
    @Schema(required = true,example = "3")
    private String name;
    @Schema(required = true,example = "Comedy")
    private List<MovieDTO> movies = new ArrayList<>();
    
    public GenreDTO() {
    }

    
    public GenreDTO(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
        
        for (Movie movie : genre.getMovies()) {
            this.movies.add(new MovieDTO(movie));
        }
            
        for (Movie movie : genre.getMovies()) {
            this.movies.add(new MovieDTO(movie));
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MovieDTO> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieDTO> movies) {
        this.movies = movies;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.movies);
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
        final GenreDTO other = (GenreDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.movies, other.movies)) {
            return false;
        }
        return true;
    }




    
    
}
