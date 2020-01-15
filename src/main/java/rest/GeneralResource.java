package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MovieDTO;
import dto_OUT.MovieDTO_OUT;
import dto.MoviesDTO;
import entities.Director;

import entities.Actor;
import entities.Director;
import entities.Genre;
import entities.Movie;

import facades.GeneralFacade;
import utils.EMF_Creator;
import facades.MovieFacade;
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
import javax.persistence.EntityManager;
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

@Path("general")
public class GeneralResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV,
            EMF_Creator.Strategy.CREATE);
    private static final GeneralFacade FACADE = GeneralFacade.getGeneralFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("/all/genre/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieDTO> getAllMoviesByGenre(@PathParam("name") String name) {
        return FACADE.getAllMoviesByGenre(name).getAll();
    }

    @GET
    @Path("/all/actor/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieDTO> getAllMoviesByActor(@PathParam("name") String name) {
        return FACADE.getAllMoviesByActor(name).getAll();
    }

    @GET
    @Path("/all/director/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieDTO> getAllMoviesByDirector(@PathParam("name") String name) {
        return FACADE.getAllMoviesByDirector(name).getAll();
    }

}
