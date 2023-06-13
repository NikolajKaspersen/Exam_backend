package rest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.User;
import facades.UserFacade;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

@Path("users")
public class UserResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private final UserFacade userFacade = UserFacade.getUserFacade(EMF);
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("username") String username) {
        User user = userFacade.getUser(username);
        if (user != null) {
            return Response.ok(gson.toJson(user)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(String jsonUser) {
        User user = gson.fromJson(jsonUser, User.class);
        User createdUser = userFacade.createUser(user);
        return Response.ok(gson.toJson(createdUser)).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        try {
            User user = userFacade.getVerifiedUser(username, password);
            // Implementer logikken for autentificering, session management osv.
            return Response.ok().build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @DELETE
    @Path("/{username}")
    public Response deleteUser(@PathParam("username") String username) {
        User deletedUser = userFacade.deleteUser(username);
        return Response.ok(gson.toJson(deletedUser)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editUser(@PathParam("id") Long id, String jsonUser) {
        User updatedUser = gson.fromJson(jsonUser, User.class);
        User editedUser = userFacade.editUser(id, updatedUser.getUserName(), updatedUser.getUserPass(),
                updatedUser.getName(), updatedUser.getPhone(), updatedUser.getEmail(), updatedUser.getStatus());
        return Response.ok(gson.toJson(editedUser)).build();
    }
}