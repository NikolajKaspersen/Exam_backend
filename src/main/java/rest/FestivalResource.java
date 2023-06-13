package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import facades.FestivalFacade;
import dtos.FestivalDto;
import utils.EMF_Creator;

@Path("festivals")
public class FestivalResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private final FestivalFacade festivalFacade = FestivalFacade.getFestivalFacade(EMF);
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFestival(String jsonFestival) {
        FestivalDto festivalDto = gson.fromJson(jsonFestival, FestivalDto.class);
        FestivalDto createdFestival = festivalFacade.create(festivalDto);
        return Response.ok(gson.toJson(createdFestival)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFestivalById(@PathParam("id") Long id) {
        FestivalDto festivalDto = festivalFacade.getById(id);
        if (festivalDto != null) {
            return Response.ok(gson.toJson(festivalDto)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteFestivalById(@PathParam("id") Long id) {
        FestivalDto deletedFestival = festivalFacade.deleteById(id);
        return Response.ok(gson.toJson(deletedFestival)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFestival(String jsonFestival) {
        FestivalDto festivalDto = gson.fromJson(jsonFestival, FestivalDto.class);
        FestivalDto updatedFestival = festivalFacade.edit(festivalDto);
        return Response.ok(gson.toJson(updatedFestival)).build();
    }
}
