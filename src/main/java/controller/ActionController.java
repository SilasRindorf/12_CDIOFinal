package controller;


import DAL.interfaces.DALException;
import DAL.interfaces.IUserDAO;
import DAL.nonPersistent.UserDAONonPersistent;
import RAM.User;

public class ActionController {
    private static ActionController ActionControllerInstance = null;
    private final IUserDAO USERS = new UserDAONonPersistent();
    private ActionController(){

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
                System.out.println(USERS.getUserList().get(i).getUsername());
                if (USERS.getUserList().get(i).getIsActive()
                        && USERS.getUserList().get(i).getUsername().equalsIgnoreCase(name)
                        && USERS.getUserList().get(i).getHashedPass().equals(pass)){
                    return true;
                }
            }
        } catch (DALException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
