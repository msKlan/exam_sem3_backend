/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Director;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Klan
 */
public class DirectorsDTO {
    // @Schema(example="[\"1\",\"Id\",\"Collection\"]")

    List<DirectorDTO> all = new ArrayList<>();

    public DirectorsDTO(List<Director> directorEntities) {
        for (Director directorEntity : directorEntities) {
            all.add(new DirectorDTO(directorEntity));
        }
    }

    public List<DirectorDTO> getAll() {
        return all;
    }

    public void setAll(List<DirectorDTO> all) {
        this.all = all;
    }

}
