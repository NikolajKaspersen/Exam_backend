package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import facades.BookingFacade;
import dtos.BookingDto;
import utils.EMF_Creator;


@Path("bookings")
public class BookingResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private final BookingFacade bookingFacade = BookingFacade.getBookingFacade(EMF);
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBookings(){
        return GSON.toJson(bookingFacade.getAllBookings());
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBooking(String jsonBooking) {
        BookingDto bookingDto = GSON.fromJson(jsonBooking, BookingDto.class);
        BookingDto createdBooking = bookingFacade.createBooking(bookingDto);
        return Response.ok(GSON.toJson(createdBooking)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookingById(@PathParam("id") Long id) {
        BookingDto bookingDto = bookingFacade.getBookingById(id);
        if (bookingDto != null) {
            return Response.ok(GSON.toJson(bookingDto)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBookingById(@PathParam("id") Long id) {
        BookingDto deletedBooking = bookingFacade.deleteBookingById(id);
        return Response.ok(GSON.toJson(deletedBooking)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editBooking(String jsonBooking) {
        BookingDto bookingDto = GSON.fromJson(jsonBooking, BookingDto.class);
        BookingDto updatedBooking = bookingFacade.editBooking(bookingDto);
        return Response.ok(GSON.toJson(updatedBooking)).build();
    }
}