package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import facades.ConcertFacade;
import dtos.ConcertDto;
import utils.EMF_Creator;

@Path("concerts")
public class ConcertResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private final ConcertFacade concertFacade = ConcertFacade.getConcertFacade(EMF);
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createConcert(String jsonConcert) {
        ConcertDto concertDto = gson.fromJson(jsonConcert, ConcertDto.class);
        ConcertDto createdConcert = concertFacade.create(concertDto);
        return Response.ok(gson.toJson(createdConcert)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConcertById(@PathParam("id") Long id) {
        ConcertDto concertDto = concertFacade.getById(id);
        if (concertDto != null) {
            return Response.ok(gson.toJson(concertDto)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteConcertById(@PathParam("id") Long id) {
        ConcertDto deletedConcert = concertFacade.deleteById(id);
        return Response.ok(gson.toJson(deletedConcert)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateConcert(String jsonConcert) {
        ConcertDto concertDto = gson.fromJson(jsonConcert, ConcertDto.class);
        ConcertDto updatedConcert = concertFacade.update(concertDto);
        return Response.ok(gson.toJson(updatedConcert)).build();
    }
}