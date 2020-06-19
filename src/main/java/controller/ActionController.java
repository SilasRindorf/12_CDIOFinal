package controller;


import DAL.interfaces.DALException;
import DAL.interfaces.IUserDAO;
import DAL.interfaces.JunkFormatException;
import DAL.nonPersistent.UserDAONonPersistent;
import DTO.UserDTO;
import RAM.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ActionController {
    private static ActionController ActionControllerInstance = null;
    private final IUserDAO USERS = new UserDAONonPersistent();
    private ActionController(){
        try {
            USERS.createUser(new User(11, "Silas", "SIL", "123", User.hash("Abe"), User.Role.Administrator, true));
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

    public String createUser(UserDTO userDTO){
        try {
            USERS.createUser(new User(userDTO.getID(), userDTO.getUsername(),
                    userDTO.getIni(), User.hash(userDTO.getNonHashedPassword()), userDTO.getCPR(),
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
            return "Could not get users";
        }
    }
}
