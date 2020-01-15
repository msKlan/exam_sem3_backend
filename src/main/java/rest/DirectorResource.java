/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.DirectorsDTO;
import dto.DirectorDTO;
import entities.Director;
import facades.DirectorFacade;
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
import javax.annotation.security.RolesAllowed;
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

@OpenAPIDefinition(info = @Info(title = "Simple Director API", version = "0.4", description = "Simple API to get info about directors.", contact = @Contact(name = "Jacob Hildebrandt", email = "@cphbusiness.dk")), tags = {
        @Tag(name = "Director", description = "API related to Director")

}, servers = { @Server(description = "For Local host testing", url = "http://localhost:8080/exam_sem3/"),
        @Server(description = "Server API", url = "https://aieou.dk/exam_sem3/")

})

@Path("director")
public class DirectorResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV,
            EMF_Creator.Strategy.CREATE);
    private static final DirectorFacade FACADE = DirectorFacade.getDirectorFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Operation(summary = "Shows Hello World - Director", tags = { "Director" }, responses = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = DirectorDTO.class))),
            @ApiResponse(responseCode = "200", description = "The rest interface is running director"),
            @ApiResponse(responseCode = "400", description = "The rest interface is probably not running") })

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public String demo() {
        return "{\"msg\":\"Hello World - director\"}";
    }

    @Operation(summary = "Get Hobby by ID", tags = { "Director" }, responses = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = DirectorDTO.class))),
            @ApiResponse(responseCode = "200", description = "The Requested Hobby"),
            @ApiResponse(responseCode = "400", description = "Hobby not found") })
    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public DirectorDTO getHobbyByID(@PathParam("id") int id) {
        DirectorDTO hobby = FACADE.getDirector(id);
        return hobby;
    }

    @Operation(summary = "Get All Directors", tags = { "Director" }, responses = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = DirectorDTO.class))),
            @ApiResponse(responseCode = "200", description = "A list of all Hobbies"),
            @ApiResponse(responseCode = "400", description = "????") })
    @GET
    @Path("all")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<DirectorDTO> getAllHobbies() {
        return FACADE.getAllDirectors().getAll();
    }

    @Operation(summary = "Add a Director", tags = { "Director" }, responses = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = DirectorDTO.class))),
            @ApiResponse(responseCode = "200", description = "The added Director"),
            @ApiResponse(responseCode = "400", description = "Invalid input") })
    @POST
    @Path("add")
    @Produces({ MediaType.APPLICATION_JSON })
    @RolesAllowed({ "useradmin", "admin" })
    public DirectorDTO addHobby(String director) {
        Director d = GSON.fromJson(director, Director.class);
        DirectorDTO directorDTO = new DirectorDTO(d);
        FACADE.addDirector(directorDTO);
        return directorDTO;
    }

    @Operation(summary = "Edit a Director", tags = { "Director" }, responses = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = DirectorDTO.class))),
            @ApiResponse(responseCode = "200", description = "The edited Director"),
            @ApiResponse(responseCode = "400", description = "Director not found") })
    @PUT
    @Path("edit")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "useradmin", "admin" })
    public DirectorDTO editDirector(String director) {
        Director d = GSON.fromJson(director, Director.class);
        DirectorDTO directorDTO = new DirectorDTO(d);
        FACADE.editDirector(directorDTO);
        return directorDTO;
    }

    @Operation(summary = "Remove/Delete a Director", tags = { "Director" }, responses = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = DirectorDTO.class))),
            @ApiResponse(responseCode = "200", description = "The removed Director"),
            @ApiResponse(responseCode = "400", description = "Director not found") })
    @DELETE
    @Path("delete/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "useradmin", "admin" })
    public DirectorDTO removeHobby(@PathParam("id") int id) {
        return FACADE.removeDirector(id);
    }
}
