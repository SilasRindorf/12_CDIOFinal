package controllers;

import DAL.interfaces.DALException;
import DAL.interfaces.IUserDAO;
import DAL.nonPersistent.UserDAONonPersistent;
import DTO.UserDTO;

public class ActionController {
    private IUserDAO users = new UserDAONonPersistent();
    public boolean logIn(String uname, String password){
        try {
            for (UserDTO user : users.getUserList()) {
                System.out.println("user:" +
                        "\n\tname=" + user.getUsername() +
                        "\n\tpassword=" + user.getHashedPass() +
                        "\n\tisActive=" + user.getIsActive());
                if (user.getUsername().equals(uname) && user.getHashedPass().equals(password) && user.getIsActive()){
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
