/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto_OUT;

import dto.GenreDTO;
import entities.Genre;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Klan
 */
public class GenresDTO_OUT {
    List<GenreDTO_OUT> all = new ArrayList<>();
    
    public GenresDTO_OUT(List<Genre> genreEntities) {
        for (Genre genreEntity : genreEntities ){
            all.add(new GenreDTO_OUT(genreEntity));
        }
    }

    public List<GenreDTO_OUT> getAll() {
        return all;
    }

    public void setAll(List<GenreDTO_OUT> all) {
        this.all = all;
    }
    
    
}
