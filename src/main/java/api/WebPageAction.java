package api;

import DTO.LogInDTO;
import controllers.ActionController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("actions")
public class WebPageAction {
    private static ActionController controller = new ActionController();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("confirm-log-in")
    public Response logIn(LogInDTO logInDTO){
        if (controller.logIn(logInDTO.getUsername(),logInDTO.getPassword()))
            return Response.status(201).build();
        else
            return Response.status(400).build();
    }

}
