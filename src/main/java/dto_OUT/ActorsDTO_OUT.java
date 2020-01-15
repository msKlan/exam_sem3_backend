/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto_OUT;

import dto.GenreDTO;
import entities.Actor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Klan
 */
public class ActorsDTO_OUT {
    List<ActorDTO_OUT> all = new ArrayList<>();
    
    public ActorsDTO_OUT(List<Actor> actorEntities) {
        for (Actor actorEntity : actorEntities ){
            all.add(new ActorDTO_OUT(actorEntity));
        }
    }

    public List<ActorDTO_OUT> getAll() {
        return all;
    }

    public void setAll(List<ActorDTO_OUT> all) {
        this.all = all;
    }
    
    
}
