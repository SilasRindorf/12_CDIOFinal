package controller;


import DAL.interfaces.DALException;
import DAL.interfaces.ICommodityDAO;
import DAL.interfaces.IUserDAO;
import DAL.interfaces.JunkFormatException;
import DAL.nonPersistent.CommodityDAONonPersistent;
import DAL.nonPersistent.UserDAONonPersistent;
import DTO.UserDTO;
import RAM.Commodity;
import RAM.CommodityBatch;
import RAM.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ActionController {
    private static ActionController ActionControllerInstance = null;
    private final IUserDAO USERS = new UserDAONonPersistent();
    private final ICommodityDAO COM = new CommodityDAONonPersistent();
    private ActionController(){
        try {
            USERS.createUser(new User(11, "Admin", "adm", "123", User.hash("password"), User.Role.Administrator, true));
            COM.createCommodity(new Commodity(1,"Citron",true));
            COM.createBatch(new CommodityBatch(1,1,5000,"Mærsk",true));
        } catch (Exception ignored){

        }
    }
    // static method to create instance of Singleton class
    public static ActionController getInstance()
    {
        if (ActionControllerInstance == null)
            ActionControllerInstance = new ActionController();

        return ActionControllerInstance;
    }

    public boolean logIn(String name, String pass){
        try {
            for (int i = 0; i < USERS.getUserList().size(); i++) {
                if (USERS.getUserList().get(i).getIsActive()
                        && USERS.getUserList().get(i).getUsername().equalsIgnoreCase(name)
                        && User.check(pass,USERS.getUserList().get(i).getHashedPass())){
                    return true;
                }
            }
        } catch (DALException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
    // ------------------------------- User methods ------------------------------------------
    public String createUser(UserDTO userDTO){
        try {
            USERS.createUser(new User(userDTO.getID(), userDTO.getUsername(),
                    userDTO.getIni(), userDTO.getCPR(),User.hash(userDTO.getNonHashedPassword()) ,
                    User.Role.valueOf(userDTO.getRole()), userDTO.isActive()));
        } catch (DALException | JunkFormatException e){
            e.printStackTrace();
            return "Bruger kunne ikke laves";
        }
        return "Bruger lavet";
    }

    public String getUsers(){
        ObjectMapper objMapper = new ObjectMapper();
        try {
            return objMapper.writeValueAsString(USERS.getUserList());
        } catch (JsonProcessingException | DALException e){
            e.printStackTrace();
            return "Kunne ikke skaffe brugere";
        }
    }

    public void setIsActiveUser(int id, boolean status) {
        try{
            USERS.setIsActive(id,status);
        }
        catch (DALException e){
            e.printStackTrace();
        }

    }
    // ------------------------------- Commodity methods ------------------------------------------
    public String getCommodities(){
        ObjectMapper objMapper = new ObjectMapper();
        try {
            return objMapper.writeValueAsString(COM.getCommodityList());
        } catch (JsonProcessingException | DALException e){
            e.printStackTrace();
            return "Kunne ikke skaffe brugere";
        }
    }

    public void setIsActiveCommodity(int commodityNr, boolean status) {
        try{
            COM.setIsActiveCommodity(commodityNr,status);
        }
        catch (DALException e){
            e.printStackTrace();
        }
    }

    public String createCommodity(Commodity commodity){
        try {
            COM.createCommodity(new Commodity(commodity.getCommodityNr(),commodity.getName(),commodity.getIsActive()));
        } catch (DALException | JunkFormatException e){
            e.printStackTrace();
            return "Råvare kunne ikke laves";
        }
        return "Råvare lavet";
    }
    // ------------------------------- CommodityBatch methods ------------------------------------------
    public String getCommodityBatch(){
        ObjectMapper objMapper = new ObjectMapper();
        try {
            return objMapper.writeValueAsString(COM.getBatchList());
        } catch (JsonProcessingException | DALException e){
            e.printStackTrace();
            return "Kunne ikke skaffe råvarebatches";
        }
    }
    public void setIsActiveCommodityBatch(int commodityBatchNr, boolean status) {
        try{
            COM.setIsActiveBatch(commodityBatchNr,status);
        }
        catch (DALException e){
            e.printStackTrace();
        }
    }
    public String createCommodityBatch(CommodityBatch commodityBatch){
        try {
            COM.createBatch(new CommodityBatch(commodityBatch.getCommodityBatchNr(),commodityBatch.getCommodityNr(),commodityBatch.getAmount(),commodityBatch.getProvider(),commodityBatch.getIsActive()));
        } catch (DALException | JunkFormatException e){
            e.printStackTrace();
            return " Kunne ikke laves";
        }
        return "Råvarebatch lavet";
    }
}
