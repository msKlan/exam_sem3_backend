/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Actor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Klan
 */ 
public class ActorsDTO {

    List<ActorDTO> all = new ArrayList<>();

    public ActorsDTO(List<Actor> actorEntities) {
        for (Actor actorEntity : actorEntities) {
            all.add(new ActorDTO(actorEntity));
        }
    }

    public List<ActorDTO> getAll() {
        return all;
    }

    public void setAll(List<ActorDTO> all) {
        this.all = all;
    }
    
    

}
