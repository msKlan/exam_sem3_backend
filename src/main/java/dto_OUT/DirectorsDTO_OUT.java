/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto_OUT;

import dto.DirectorDTO;
import entities.Director;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Klan
 */
public class DirectorsDTO_OUT {
    List<DirectorDTO_OUT> all = new ArrayList<>();
    
    public DirectorsDTO_OUT(List<Director> directorEntities) {
        for (Director directorEntity : directorEntities ){
            all.add(new DirectorDTO_OUT(directorEntity));
        }
    }

    public List<DirectorDTO_OUT> getAll() {
        return all;
    }

    public void setAll(List<DirectorDTO_OUT> all) {
        this.all = all;
    }
    
    
}
