package api.controller;

import DTO.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/actions")
public class ActionController {

    public ActionController(){

    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getter(){
        return "Action page";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("log-in")
    public Response logIn(LogInDTO logInDTO){
        System.out.println("logger ind som: " + logInDTO.getUsername() + " With password: " + logInDTO.getPassword());
        return Response.status(201).build();
    }

}
