/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.GenresDTO;
import dto.GenreDTO;
import entities.Genre;
import facades.GenreFacade;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.EMF_Creator;

/**
 *
 * @author Klan
 */

@OpenAPIDefinition(info = @Info(title = "Simple genre API", version = "0.4", description = "Simple API to get info about Genres.", contact = @Contact(name = "Michael Sander Klan", email = "michaelsanderklan@gmail.com")), tags = {
        @Tag(name = "Genre", description = "API related to genres")

}, servers = { @Server(description = "For Local host testing", url = "http://localhost:8080/exam_sem3/"),
        @Server(description = "Server API", url = "https://aieou.dk/exam_sem3/")

})

@Path("genre")
public class GenreResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV,
            EMF_Creator.Strategy.CREATE);
    private static final GenreFacade FACADE = GenreFacade.getGenreFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Operation(summary = "Shows Hello World - Genre", tags = { "Genre" }, responses = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenreDTO.class))),
            @ApiResponse(responseCode = "200", description = "The rest interface is running genre"),
            @ApiResponse(responseCode = "400", description = "The rest interface is probably not running") })
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Operation(summary = "Get Genre by ID", tags = { "Genre" }, responses = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenreDTO.class))),
            @ApiResponse(responseCode = "200", description = "The requested Genre"),
            @ApiResponse(responseCode = "400", description = "Genre not found") })
    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public GenreDTO getGenreByID(@PathParam("id") int id) {
        GenreDTO genre = FACADE.getGenre(id);
        return genre;
    }

    @Operation(summary = "Get All Genres", tags = { "Genre" }, responses = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenresDTO.class))),
            @ApiResponse(responseCode = "200", description = "The requested Genre"),
            @ApiResponse(responseCode = "400", description = "Genre not found") })
    @GET
    @Path("all")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<GenreDTO> getAllGenres() {
        return FACADE.getAllGenres().getAll();
    }

//    @Operation(summary = "Add a Genre", tags = { "Genre" }, responses = {
//            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenreDTO.class))),
//            @ApiResponse(responseCode = "200", description = "The added Genre"),
//            @ApiResponse(responseCode = "400", description = "Invalid input") })
//    @POST
//    @Path("add")
//    @Produces({ MediaType.APPLICATION_JSON })
//    public GenreDTO addGenre(String genre) {
//        Genre g = GSON.fromJson(genre, Genre.class);
//        GenreDTO genreDTO = new GenreDTO(g);
//        FACADE.addGenre(genreDTO);
//        return genreDTO;
//    }
//
//    @Operation(summary = "Edit a Genre", tags = { "Genre" }, responses = {
//            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenreDTO.class))),
//            @ApiResponse(responseCode = "200", description = "The edited Genre"),
//            @ApiResponse(responseCode = "400", description = "Genre not found") })
//    @PUT
//    @Path("edit")
//    @Produces({ MediaType.APPLICATION_JSON })
//    @Consumes(MediaType.APPLICATION_JSON)
//    public GenreDTO editHobby(String genre) {
//        Genre g = GSON.fromJson(genre, Genre.class);
//        GenreDTO genreDTO = new GenreDTO(g);
//        FACADE.editGenre(genreDTO);
//        return genreDTO;
//    }
//
//    @Operation(summary = "Remove/Delete a Genre", tags = { "Genre" }, responses = {
//            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenreDTO.class))),
//            @ApiResponse(responseCode = "200", description = "The Removed Genre"),
//            @ApiResponse(responseCode = "400", description = "Genre not found") })
//    @DELETE
//    @Path("delete/{id}")
//    @Produces({ MediaType.APPLICATION_JSON })
//    @Consumes(MediaType.APPLICATION_JSON)
//    public GenreDTO removeGenre(@PathParam("id") int id) {
//        return FACADE.removeGenre(id);
//    }
}
