package api;

import DAL.interfaces.DALException;
import DTO.*;
import RAM.Commodity;
import RAM.CommodityBatch;
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
    @Path("setisactive-user")
    public void setIsActiveUser(@QueryParam("ID") int ID, @QueryParam("isActive") boolean isActive) {
        controller.setIsActiveUser(ID, isActive);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("commodityput")
    public void putCommodity(@QueryParam("commodityNr") int commodityNr, @QueryParam("name") String name, @QueryParam("isActive") boolean isActive){
        controller.createCommodity(new CommodityDTO(commodityNr,name,isActive));
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("commodity-get")
    public String getCommodity(){
        return controller.getCommodities();
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("setisactive-commodity")
    public void setIsActiveCommodity(@QueryParam("commodityNr") int commodityNr, @QueryParam("isActive") boolean isActive) {
        controller.setIsActiveCommodity(commodityNr, isActive);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("commoditybatchput")
    public void putCommodityBatch(@QueryParam("JSON_Object") String array){
       controller.createReceipt(new ReceiptDTO());
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("commoditybatch-get")
    public String getCommodityBatch(){
        return controller.getCommodityBatch();
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("setisactive-commoditybatch")
    public void setIsActiveCommodityBatch(@QueryParam("commodityBatchNr") int commodityBatchNr, @QueryParam("isActive") boolean isActive) {
        controller.setIsActiveCommodityBatch(commodityBatchNr, isActive);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("receiptput")
    public void putReceipt(@QueryParam("commodityBatchNr") int commodityBatchNr, @QueryParam("commodityNr") int commodityNr, @QueryParam("amount") double amount, @QueryParam("provider") String provider, @QueryParam("isActive") boolean isActive){
        controller.createCommodityBatch(new CommodityBatchDTO(commodityBatchNr,commodityNr,amount,provider,isActive));
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("receipt-get")
    public String getReceipt(){
        return controller.getCommodityBatch();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("setisactive-receipt")
    public void setIsActiveReceipt(@QueryParam("commodityBatchNr") int commodityBatchNr, @QueryParam("isActive") boolean isActive) {
        controller.setIsActiveCommodityBatch(commodityBatchNr, isActive);
    }



}


