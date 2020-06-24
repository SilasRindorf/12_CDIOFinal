package DAL.persistent;

import DAL.interfaces.DALException;
import DAL.interfaces.JunkFormatException;
import DAL.nonPersistent.UserDAONonPersistent;
import RAM.User;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// @formatter:off
/***
 * Main responsible: Christoffer
 * Edited by: Silas, Alexander, Sejr, Andreas
 */
// @formatter:on

public class UserDAO extends UserDAONonPersistent {
    private final String FILE;

    public UserDAO(String filepath) throws IOException, ClassNotFoundException {
        super();
        FILE = filepath;
        File file = new File(FILE);
        boolean isNew = file.createNewFile();
        if (!isNew) {
            try {
                users = (ArrayList) FileAPI.loadDataFromFile(FILE);
            } catch (EOFException ignored) { //Means that no objects are in the file
            }
        }
    }


    public void createUser(User user) throws DALException, JunkFormatException {
        super.createUser(user);
        try {
            FileAPI.saveDataToFile(getUserList(), FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) throws DALException, JunkFormatException {
        super.updateUser(user);
        try {
            FileAPI.saveDataToFile(getUserList(), FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setIsActive(int userId, boolean isActive) throws DALException {
        super.setIsActive(userId, isActive);
        try {
            FileAPI.saveDataToFile(getUserList(), FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
