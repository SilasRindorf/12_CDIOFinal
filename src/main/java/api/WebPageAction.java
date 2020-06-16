package api;

import RAM.LogIn;
import controllers.ActionController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/actions")
public class WebPageAction {

    private static ActionController controller = new ActionController();
    public WebPageAction(){

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
    public Response logIn(LogIn logIn){
        controller.logIn(logIn.getUsername(), logIn.getPassword());
        return Response.status(201).build();
    }

}
