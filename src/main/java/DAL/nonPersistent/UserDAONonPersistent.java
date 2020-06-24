package DAL.nonPersistent;

import DAL.interfaces.DALException;
import DAL.interfaces.IUserDAO;
import DAL.interfaces.JunkFormatException;
import RAM.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: Andreas, Christoffer
 * Created: 08-06-2020
 * This class is responsible for:
 *  Storing information about users in a non persistent manner
 *  Assuring wrong or illegal information is not stored
 */
public class UserDAONonPersistent implements IUserDAO {
    protected List<User> users;

    public UserDAONonPersistent() {
        users = new ArrayList<>();
    }

    public User getUser(int userId) throws DALException {
        for (User user : users) {
            if (user.getID() == userId) {
                return user;
            }
        }
        throw new DALException("There is no user where ID=" + userId);
    }

    public List<User> getUserList() throws DALException {
        return new ArrayList<>(users);
    }

    public void createUser(User newUser) throws DALException, JunkFormatException {

    if (!(11<= newUser.getID() && newUser.getID() <= 99)){ 
            throw new JunkFormatException("UserID needs to be between 11 and 99 inclusive", Arrays.asList(JunkFormatException.ErrorList.ID));
    }
        for (User user : users) {
            if (user.getID() == newUser.getID()) {
                throw new DALException("User with id already exists for: " + user.getID());
            }
            if (user.getIni() == newUser.getIni()){
                throw new DALException("User with name already exists for: " + user.getUsername());
            }
        }
        users.add(newUser);
    }

    public void updateUser(User newUser) throws DALException, JunkFormatException {
        for (User user : users) {
            if (user.getID() == newUser.getID()) {
                User backup = user;
                users.remove(user);
                try {
                    createUser(newUser);
                } catch (Exception e) {
                    users.add(backup);
                    throw e;
                }
                return;
            }
        }
        throw new DALException("The user you tried to update does not exist: " + newUser);
    }


    @Override
    public void setIsActive(int userId, boolean isActive) throws DALException {
        User user = getUser(userId);
        if (user.getIsActive() == isActive) {
            throw new DALException("The user activity is already " + isActive);
        }
        User newUser = new User(user.getID(), user.getUsername(), user.getIni(), user.getCPR(), user.getHashedPass(), user.getRole(), isActive);
        try {
            updateUser(newUser);
        } catch (JunkFormatException e) {
            throw new AssertionError("Changing isActive should not result in " +
                    "JunkFormatException, since it should only modify one and only one variable (isActive). " +
                    "This means that the database state was corrupt.");
        }
    }

}
