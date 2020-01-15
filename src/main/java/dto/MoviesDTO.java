/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Movie;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Klan
 */
public class MoviesDTO {

    List<MovieDTO> all = new ArrayList();

    public MoviesDTO(List<Movie> movieEntities) {
        for (Movie movieEntity : movieEntities) {
            all.add(new MovieDTO(movieEntity));
        }
    }

    public List<MovieDTO> getAll() {
        return all;
    }

    public void setAll(List<MovieDTO> all) {
        this.all = all;
    }
    
}
