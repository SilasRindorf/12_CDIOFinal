package DAL.persistent;

import DAL.interfaces.DALException;
import DAL.interfaces.IUserDAO;
import DTO.UserDTO;

import java.util.ArrayList;
import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 09-06-2020
 * This class is responsible for:
 *  -
 */
public class UserDAO implements IUserDAO {
    private List<UserDTO> users = new ArrayList<>();
    @Override
    public UserDTO getUser(int userID) throws DALException {
        if (users.get(userID) == null)
            throw new DALException("No user found");
        else
            return users.get(userID);
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        if (users.isEmpty())
            throw new DALException("No users available");
        else
            return users;
    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        if (users.get(user.getID())!= null)
            throw new DALException("Receipt ID Taken");
        else
            users.add(user.getID(),user);
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {

    }

    @Override
    public void setInactiveUser(int userId) throws DALException {

    }

}
