package dto;

import entities.Genre;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Klan
 */
public class GenresDTO {
    @Schema(example="[\"Comedy\"]")
    List<GenreDTO> all = new ArrayList<>();
    
    public GenresDTO(List<Genre> genreEntities) {
        for (Genre genreEntity : genreEntities ){
            all.add(new GenreDTO(genreEntity));
        }
    }

    public List<GenreDTO> getAll() {
        return all;
    }

    public void setAll(List<GenreDTO> all) {
        this.all = all;
    }
    
    
}
