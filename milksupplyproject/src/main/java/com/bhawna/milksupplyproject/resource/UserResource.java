package com.bhawna.milksupplyproject.resource;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bhawna.milksupplyproject.model.User;
import com.bhawna.milksupplyproject.service.UserService;
import com.codahale.metrics.annotation.Timed;

@Path("/User")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
 
    private final UserService userService;
 
    public UserResource(UserService userService) {
        this.userService = userService;
    }
 
    @GET
    @Timed
    public Response getUsers() {
        return Response.ok(userService.getUsers()).build();
    }
 
    @GET
    @Timed
    @Path("{id}")
    public Response getUser(@PathParam("id") final int id) {
        return Response.ok(userService.getUser(id)).build();
    }
 
    @POST
    @Timed
    public Response createUser(@NotNull @Valid final User user) {
        User userCreate = new User(user.getFirstName(),user.getLastName(),user.getPhone(), user.getEmail(),user.getPassword());
        return Response.ok(userService.createUser(userCreate)).build();
    }
 
    @PUT
    @Timed
    @Path("{id}")
    public Response editUser(@NotNull @Valid final User user,
                                 @PathParam("id") final long id) {
        user.setId(id);
        return Response.ok(userService.editUser(user)).build();
    }
 
    @DELETE
    @Timed
    @Path("{id}")
    public Response deleteUser(@PathParam("id") final int id) {
        Map<String, String> response = new HashMap<>();
        response.put("status", userService.deleteUser(id));
        return Response.ok(response).build();
    }
}
