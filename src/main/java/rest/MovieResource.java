package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MovieDTO;
import dto.MoviesDTO;
import entities.Director;
import entities.Actor;
import entities.Genre;
import entities.Movie;

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
import javax.annotation.security.RolesAllowed;
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

@OpenAPIDefinition(info = @Info(title = "Simple Movie API", version = "0.4", description = "Simple API to get info about Movies.", contact = @Contact(name = "Michael Sander Klan", email = "michaelsanderklan@gmail.com")), tags = {
    @Tag(name = "Movie", description = "API related to movies")

}, servers = { @Server(description = "For Local host testing", url = "http://localhost:8080/exam_sem3/"),
    @Server(description = "Server API", url = "https://aieou.dk/exam_sem3/")

})
@Path("movie")
public class MovieResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV,
            EMF_Creator.Strategy.CREATE);
    private static final MovieFacade FACADE = MovieFacade.getMovieFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Operation(summary = "Setup default site data", tags = { "Movie" }, responses = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class))),
            @ApiResponse(responseCode = "200", description = "Defualt data now ready"),
            @ApiResponse(responseCode = "400", description = "Could not setup default data") })
    
    @Path("/setup")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSetup() {
        EntityManager em = EMF.createEntityManager();

        Director d1 = new Director("Guielmo Deltoro", "has cool sunglasses");
        Genre g1 = new Genre("Comedy");
        Actor a1 = new Actor("Owen Wilson", "has a fucked up nose");
        Movie m1 = new Movie("Star Wars: the empire strikes back", 1980);

        d1.addMovie(m1);
        a1.addMovie(m1);
        g1.addMovie(m1);

        Director d2 = new Director("Rian", "Is a complete and utter moron");
        Genre g2 = new Genre("Drama");
        Actor a2 = new Actor("Mark Hamil", "Voices the joker");
        Movie m2 = new Movie("Scream 21", 2347);

        d2.addMovie(m2);
        a2.addMovie(m2);
        g2.addMovie(m2);

        Director d3 = new Director("Dave Filoni", "is the savior of the star wars franchise");
        Genre g3 = new Genre("Horror");
        Actor a3 = new Actor("Adam  Sandler", "he is \"funny\"");
        Movie m3 = new Movie("Loop Shoop Scoop Doop", 12345);

        d3.addMovie(m3);
        a3.addMovie(m3);
        g3.addMovie(m3);

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Actor.deleteAllRows").executeUpdate();
            em.createNamedQuery("Director.deleteAllRows").executeUpdate();
            em.createNamedQuery("Genre.deleteAllRows").executeUpdate();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            
            em.persist(g1);
            em.persist(g2);
            em.persist(g3);
            em.persist(a1);
            em.persist(a2);
            em.persist(a3);
            em.persist(d1);
            em.persist(d2);
            em.persist(d3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return Response.ok().entity(GSON.toJson("Setup Complete")).build();
    }

    @Operation(summary = "Hello world - Movie", tags = { "Movie" }, responses = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = Director.class))),
            @ApiResponse(responseCode = "200", description = "The rest interface is running movie"),
            @ApiResponse(responseCode = "400", description = "The rest interface is probably not running") })
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public String demo() {
        return "{\"msg\":\"Hello World - Movie\"}";
    }

    @Operation(summary = "Get all Movies", tags = { "Movie" }, responses = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = Director.class))),
            @ApiResponse(responseCode = "200", description = "The Requested movie"),
            @ApiResponse(responseCode = "400", description = "Movie not found") })
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieDTO> getAll() {
        return FACADE.getAllMovies().getAll();
    }

    @Operation(summary = "Get Movie by ID", tags = { "Movie" }, responses = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class))),
            @ApiResponse(responseCode = "200", description = "The requested Movie"),
            @ApiResponse(responseCode = "400", description = "Movie not found") })
    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MovieDTO getMovieByID(@PathParam("id") int id) {
        return FACADE.getMovie(id);
    }

    
    @Operation(summary = "Get Movie by Genre Name", tags = { "Movie" }, responses = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class))),
            @ApiResponse(responseCode = "200", description = "The requested Movies by GenreId"),
            @ApiResponse(responseCode = "400", description = "Movies not found") })
    @GET
    @Path("genre/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieDTO> getMoviesByGenreName(@PathParam("name") String genreName) {
        List<Movie> movieEntities = FACADE.getMoviesByGenreName(genreName);
        List<MovieDTO> list = new ArrayList();
        for (Movie movieEntity : movieEntities) {
            list.add(new MovieDTO(movieEntity));
        }
        return list;
//        return FACADE.getMoviesByGenreName(genreName);
    }
    
    
    @Operation(summary = "Add a Movie",
       tags = {"Movie"},
       responses = {
                @ApiResponse(
                content = @Content(mediaType = "application/json",schema = @Schema(implementation = MovieDTO.class))),
               @ApiResponse(responseCode = "200", description = "The added Movie"),                       
               @ApiResponse(responseCode = "400", description = "Invalid input")})    @POST
    @Path("add")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"useradmin", "admin"})
    public MovieDTO addMovie(String movie) {
        Movie h = GSON.fromJson(movie, Movie.class);
        MovieDTO movieDTO = new MovieDTO(h);
        FACADE.addMovie(movieDTO);
        return movieDTO;
    }
    
    @Operation(summary = "Edit a Movie",
       tags = {"Movie"},
       responses = {
                @ApiResponse(
                content = @Content(mediaType = "application/json",schema = @Schema(implementation = MovieDTO.class))),
               @ApiResponse(responseCode = "200", description = "The edited Movie"),                       
               @ApiResponse(responseCode = "400", description = "Movie not found")})
    @PUT
    @Path("edit")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"useradmin", "admin"})
    public MovieDTO editMovie(String movie) {
        Movie h = GSON.fromJson(movie, Movie.class);
        MovieDTO movieDTO = new MovieDTO(h);
        FACADE.editMovie(movieDTO);
        return movieDTO;
    }
    
    @Operation(summary = "Remove/Delete a Movie",
       tags = {"Movie"},
       responses = {
                @ApiResponse(
                content = @Content(mediaType = "application/json",schema = @Schema(implementation = MovieDTO.class))),
               @ApiResponse(responseCode = "200", description = "The Removed Movie"),                       
               @ApiResponse(responseCode = "400", description = "Movie not found")})
    @DELETE
    @Path("delete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"useradmin", "admin"})
    public MovieDTO removeMovie(@PathParam("id") int id) {
        return FACADE.deleteMovie(id);
    }    
    
}
