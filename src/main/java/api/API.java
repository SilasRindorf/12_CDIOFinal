package api;

import DAL.interfaces.DALException;
import DTO.*;
import RAM.ProductBatch;
import controller.ActionController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;

@Path("/actions")
public class API {
    //_______________________________ SETUP _______________________________
    private ActionController controller = ActionController.getInstance();

    public API() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getter() {
        return "Action page";
    }

    //_______________________________ LOGIN _______________________________
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("log-in")
    public String logIn(LogInDTO logInDTO) {
        if (controller.logIn(logInDTO.getUsername(), logInDTO.getPassword())) {
            return "Logget ind";
        }
        return "Forkert brugernavn eller kode";
    }
    //_______________________________ USER _______________________________

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user-create")
    public String createUser(UserDTO userDTO) {
        return controller.createUser(userDTO);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user-get")
    public String getUsers() {
        return controller.getUsers();
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("userput")
    public void putUser(@QueryParam("ID") int ID, @QueryParam("username") String username,
                        @QueryParam("ini") String ini, @QueryParam("CPR") String CPR, @QueryParam("nonHashedPassword") String nonHashedPassword,
                        @QueryParam("role") String role, @QueryParam("isActive") boolean isActive
    ) {

        controller.createUser(new UserDTO(ID, username, ini, CPR, nonHashedPassword, role, isActive));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("user-post")
    public String postUser(UserDTO userDTO) {
        controller.createUser(userDTO);
        return "SUCCESS MY FRIEND";
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("setisactive-user")
    public void setIsActiveUser(@QueryParam("ID") int ID, @QueryParam("isActive") boolean isActive) {
        controller.setIsActiveUser(ID, isActive);
    }

    //_______________________________ Commodity _______________________________


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("commodity-post")
    public String postCommodity(CommodityDTO commodityDTO) {
        controller.createCommodity(commodityDTO);
        return "SUCCESS MY FRIEND";
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("commodityput")
    public void putCommodity(@QueryParam("commodityNr") int commodityNr, @QueryParam("name") String name, @QueryParam("isActive") boolean isActive) {
        controller.createCommodity(new CommodityDTO(commodityNr, name, isActive));
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("commodity-get")
    public String getCommodity() {
        return controller.getCommodities();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("setisactive-commodity")
    public void setIsActiveCommodity(@QueryParam("commodityNr") int commodityNr, @QueryParam("isActive") boolean isActive) {
        controller.setIsActiveCommodity(commodityNr, isActive);
    }

    //_______________________________ Commodity Batch _______________________________

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("commoditybatchput")
    public void putReceipt(@QueryParam("commodityBatchNr") int commodityBatchNr, @QueryParam("commodityNr") int commodityNr, @QueryParam("amount") double amount, @QueryParam("provider") String provider, @QueryParam("isActive") boolean isActive) {
        controller.createCommodityBatch(new CommodityBatchDTO(commodityBatchNr, commodityNr, amount, provider, isActive));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("commodity-batch-post")
    public String postCommodityBatch(CommodityBatchDTO commodityBatchDTO) {
        controller.createCommodityBatch(commodityBatchDTO);
        return "SUCCESS MY FRIEND";
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("commoditybatch-get")
    public String getCommodityBatch() {
        return controller.getCommodityBatch();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("setisactive-commoditybatch")
    public void setIsActiveCommodityBatch(@QueryParam("commodityBatchNr") int commodityBatchNr, @QueryParam("isActive") boolean isActive) {
        controller.setIsActiveCommodityBatch(commodityBatchNr, isActive);
    }

    //_______________________________ Receipt Comp _______________________________

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("receiptcompput")
    public void putReceiptComp(@QueryParam("receiptNr") int receiptNr, @QueryParam("commodityNr") int commodityNr, @QueryParam("amount") double amount, @QueryParam("tolerance") double tolerance) {
        controller.addReceiptComp(receiptNr, new ReceiptCompDTO(receiptNr, commodityNr, amount, tolerance, true));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("receipt-comp-post")
    public String postReceiptComp(ReceiptCompDTO receiptCompDTO) {
        controller.addReceiptComp(receiptCompDTO.getReceiptNr(), receiptCompDTO);
        return "SUCCESS MY FRIEND";
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("receiptcomp-get")
    public String getReceiptComps(@QueryParam("receiptNr") int receiptNr) {
        return controller.getReceiptComps(receiptNr);
    }
    //_______________________________ Receipt DTO _______________________________

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("receiptdtoput")
    public void putReceiptDTO(@QueryParam("receiptNr") int receiptNr, @QueryParam("name") String name) {
        try {
            controller.createReceiptDTO(receiptNr, name);
        } catch (Exception e) {
            System.out.println("Der findes allerede en recept med nummer: " + receiptNr);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("receipt-dto-post")
    public String postReceiptDTO(ReceiptDTO receiptDTO) {
        try {
            controller.createReceiptDTO(receiptDTO.getReceiptNr(), receiptDTO.getName());
        } catch (DALException e) {
            e.printStackTrace();
            return "Kunne ikke lave recept";
        }

        return "SUCCESS MY FRIEND";
    }


    //_______________________________ Receipt _______________________________

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("receiptput")
    public void putReceipt(@QueryParam("receiptNr") int receiptNr) {
        controller.createReceipt(receiptNr);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("receipt-get")
    public String getReceipt() {
        return controller.getReceipts();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("setisactive-receipt")
    public void setIsActiveReceipt(@QueryParam("receiptNr") int receiptNr, @QueryParam("isActive") boolean isActive) {
        controller.setIsActiveReceipt(receiptNr, isActive);
    }


    //_______________________________ Product Batch _______________________________
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("product-batch-post")
    public String postProductBatch(ProductBatchDTO productBatchDTO) {
        try{
        return controller.createProductBatch(productBatchDTO.getProductBatchNr(),true,
                productBatchDTO.getReceiptNr(),productBatchDTO.getCreated(),
                ProductBatch.Status.CREATED,new PrintDTO(0,0,new ArrayList<>(),new Date(),0,0),new ArrayList<>());
        }catch (Exception e){
            e.printStackTrace();
            return "Lortet virker ikke";
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("product-batch-get")
    public String getProductbatches() {
            return controller.getProductBatches();

    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("print-product-batch")
    public String postPrint(@QueryParam("productBatchid") int productBatchID){
        try {
            return controller.createAndGetPrint(productBatchID);
        } catch (DALException e) {
            e.printStackTrace();
            return "ARGHHHHHHHHHHHHH intet tårn hihi";
        }
    }



}


