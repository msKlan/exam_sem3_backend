/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ActorDTO;
import dto.ActorsDTO;
import entities.Actor;
import facades.ActorFacade;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.EMF_Creator;

/**
 *
 * @author Klan
 */
@OpenAPIDefinition(info = @Info(title = "Simple actor API", version = "0.4", description = "Simple API to get info about Actors.", contact = @Contact(name = "Michael Sander Klan", email = "michaelsanderklan@gmail.com")), tags = {
                @Tag(name = "Actor", description = "API related to actors")

}, servers = { @Server(description = "For Local host testing", url = "http://localhost:8080/exam_sem3/"),
                @Server(description = "Server API", url = "https://aieou.dk/exam_sem3/")

})

@Path("actor")
public class ActorResource {

        private static final EntityManagerFactory EMF = EMF_Creator
                        .createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
        private static final ActorFacade FACADE = ActorFacade.getActorFacade(EMF);
        private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

        @Operation(summary = "Shows Hello World - Actor", tags = { "Actor" }, responses = {
                        @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActorDTO.class))),
                        @ApiResponse(responseCode = "200", description = "The requested Path"),
                        @ApiResponse(responseCode = "400", description = "Path not found") })
        @GET
        @Produces({ MediaType.APPLICATION_JSON })
        public String demo() {
                return "{\"msg\":\"Hello World - actor\"}";
        }

        @Operation(summary = "Get actor based on ID", tags = { "Actor" }, responses = {
                        @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActorDTO.class))),
                        @ApiResponse(responseCode = "200", description = "The Requested addressByID"),
                        @ApiResponse(responseCode = "400", description = "Actor by ID not found") })
        @GET
        @Path("id/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getActorByID(@PathParam("id") int id) {
                ActorDTO actor = FACADE.getActor(id);
                return Response.ok().entity(GSON.toJson(actor)).build();
        }

        @Operation(summary = "Get all actors info", tags = { "Actor" }, responses = {
                        @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActorDTO.class))),
                        @ApiResponse(responseCode = "200", description = "The requested Actor"),
                        @ApiResponse(responseCode = "400", description = "Actor not found") })
        @GET
        @Path("all")
        @Produces({ MediaType.APPLICATION_JSON })
        public List<ActorDTO> getAllActors() {
                return FACADE.getAllActors().getAll();
        }

        @POST
        @Path("add")
        @Produces({ MediaType.APPLICATION_JSON })
        @Operation(summary = "add Actor", tags = { "Actor" }, responses = {
                        @ApiResponse(responseCode = "200", description = "The Newly created actor"),
                        @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body") })
        @RolesAllowed({ "useradmin", "admin" })
        public ActorDTO addActor(String actor) {
                Actor a = GSON.fromJson(actor, Actor.class);
                ActorDTO actorDTO = new ActorDTO(a);
                if (actorDTO.getName() == null || actorDTO.getAbout() == null) {
                        throw new WebApplicationException("Not all required arguments indecluded", 400);
                }
                FACADE.addActor(actorDTO);
                return actorDTO;
        }

        @Operation(summary = "Edit actor", tags = { "Actor" }, responses = {
                        @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActorDTO.class))),
                        @ApiResponse(responseCode = "200", description = "The Requested actor"),
                        @ApiResponse(responseCode = "400", description = "Actor not found") })
        @PUT
        @Path("edit")
        @Produces({ MediaType.APPLICATION_JSON })
        @Consumes(MediaType.APPLICATION_JSON)
        @RolesAllowed({ "useradmin", "admin" })
        public ActorDTO editActor(String actor) {
                Actor a = GSON.fromJson(actor, Actor.class);
                ActorDTO actorDTO = new ActorDTO(a);
                FACADE.editActor(actorDTO);
                return actorDTO;
        }

        @Operation(summary = "Delete Actor by ID", tags = { "Actor" }, responses = {
                        @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActorDTO.class))),
                        @ApiResponse(responseCode = "200", description = "The Resquested actor"),
                        @ApiResponse(responseCode = "400", description = "Id not found") })
        @DELETE
        @Path("delete")
        @Produces({ MediaType.APPLICATION_JSON })
        @Consumes(MediaType.APPLICATION_JSON)
        @RolesAllowed({ "useradmin", "admin" })
        public ActorDTO removeActor(int id) {
                ActorDTO a = FACADE.getActor(id);
                FACADE.removeActor(id);
                return a;
        }
}
