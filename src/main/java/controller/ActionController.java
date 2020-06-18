package controller;


import DAL.interfaces.DALException;
import DAL.interfaces.IUserDAO;
import DAL.nonPersistent.UserDAONonPersistent;

public class ActionController {
    private static ActionController ActionControllerInstance = null;
    private IUserDAO users = new UserDAONonPersistent();
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
            for (int i = 0; i < users.getUserList().size(); i++) {
                if (users.getUserList().get(i).getIsActive()
                        && users.getUserList().get(i).getUsername().equalsIgnoreCase(name)
                        && users.getUserList().get(i).getHashedPass().equals(pass)){
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
