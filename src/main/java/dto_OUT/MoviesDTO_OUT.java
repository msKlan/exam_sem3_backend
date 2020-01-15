/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto_OUT;

import dto_OUT.MovieDTO_OUT;
import entities.Movie;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Klan
 */
public class MoviesDTO_OUT {
        List<MovieDTO_OUT> all = new ArrayList();

    public MoviesDTO_OUT(List<Movie> movieEntities) {
        for (Movie movieEntity : movieEntities) {
            all.add(new MovieDTO_OUT(movieEntity));
        }
    }

    public List<MovieDTO_OUT> getAll() {
        return all;
    }

    public void setAll(List<MovieDTO_OUT> all) {
        this.all = all;
    }
}
