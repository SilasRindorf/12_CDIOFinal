package DAL.nonPersistent;

import DAL.interfaces.DALException;
import DAL.interfaces.IUserDAO;
import DTO.UserDTO;
import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 08-06-2020
 * This class is responsible for:
 *  Storing information about users in a non persistent manner
 *  Assuring wrong or illegal information is not stored
 */
public class UserDAONonPersistent implements IUserDAO {
    private List<UserDTO> users;

    public UserDTO getUser(int userId) throws DALException {
        for (UserDTO user : users)
        {
            if (user.getUserID() == userId)
            {
                return user;
            }
        }
        throw new DALException("There is no user where ID=" + userId);
    }

    public List<UserDTO> getUserList() throws DALException
    {
        return users;
    }

    public void createUser(UserDTO newUser) throws DALException
    {
        if (newUser.getUserID() < 11 || newUser.getUserID() > 99)
        {
            throw new DALException("BrugerID skal være mellem 11 og 99 (inklusivt)");
        }
        for (UserDTO user : users)
        {
            if (user.getUserID() == newUser.getUserID())
            {
                throw new DALException("Der findes allerede en bruger med ID " + user.getUserID());
            }
        }
        users.add(newUser);
    }

    public void updateUser(UserDTO newUser) throws DALException
    {
        for (UserDTO user : users)
        {
            if (user.getUserID() == newUser.getUserID())
            {
                users.remove(user);
                users.add(newUser);
                return;
            }
        }
        throw new DALException("Brugeren du prøvede at opdatere fandtes ikke");
    }



    public void setInactiveUser(int userId) throws DALException
    {
        for (UserDTO user : users)
        {
            if (user.getUserID() == userId)
            {
                users.remove(user);
                return;
            }
        }
        throw new DALException("Der fandtes ingen bruger med ID " + userId);
    }
}
