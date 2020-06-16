package DAL.persistent;

import DAL.interfaces.DALException;
import DAL.interfaces.IUserDAO;
import DAL.interfaces.JunkFormatException;
import DAL.nonPersistent.UserDAONonPersistent;
import DTO.UserDTO;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 09-06-2020
 * This class is responsible for:
 *  -
 */

public class UserDAO extends UserDAONonPersistent {
    private final String FILE;

    public UserDAO(String filepath) throws IOException, ClassNotFoundException {
        super();
        FILE = filepath;
        File file = new File(FILE);
        boolean isNew = file.createNewFile();
        if(!isNew){
            try {
                users = (ArrayList) FileAPI.loadDataFromFile(FILE);
            }catch(EOFException ignored){ //Means that no objects are in the file
            }
        }
    }


    public void createUser(UserDTO user) throws DALException, JunkFormatException{
        super.createUser(user);
        try {
            FileAPI.saveDataToFile(getUserList(), FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(UserDTO user) throws DALException, JunkFormatException{
        super.updateUser(user);
        try {
            FileAPI.saveDataToFile(getUserList(), FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setIsActive(int userId, boolean isActive) throws DALException{
        super.setIsActive(userId, isActive);
        try {
            FileAPI.saveDataToFile(getUserList(), FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
