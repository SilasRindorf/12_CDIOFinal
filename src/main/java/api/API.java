package api;

import DAL.interfaces.DALException;
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
            return "Logget ind";
        }
        return "Forkert brugernavn eller kode";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user-create")
    public String createUser(UserDTO userDTO){
        return controller.createUser(userDTO);
    }
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user-get")
    public String getUsers(){
        return controller.getUsers();
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("userput")
    public void putUser(@QueryParam("ID") int ID, @QueryParam("username") String username,
                        @QueryParam("ini") String ini, @QueryParam("CPR") String CPR, @QueryParam("nonHashedPassword") String nonHashedPassword,
                        @QueryParam("role") String role, @QueryParam("isActive") boolean isActive){
        controller.createUser(new UserDTO(ID,username,ini,CPR,nonHashedPassword,role,isActive));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("setisactive")
    public void setIsActive(@QueryParam("ID") int ID, @QueryParam("isActive") boolean isActive) {
        controller.setIsActive(ID, isActive);
    }

}


