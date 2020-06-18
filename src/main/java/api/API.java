package api;

import DTO.*;
import controller.ActionController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/actions")
public class API {
    private ActionController controller = ActionController.getInstance();

    public API(){

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
    public String logIn(LogInDTO logInDTO){
        if (controller.logIn(logInDTO.getUsername(),logInDTO.getPassword())){
            return "logget ind";
        }
        return "Forkert brugernavn eller kode";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("create-user")
    public String createUser(UserDTO userDTO){
        return controller.createUser(userDTO);
    }

}
