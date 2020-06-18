package DAL.interfaces;

import RAM.User;

import java.util.List;

/***
 * Initial version created by: Taken from CDIO 1
 * Edited by: Silas
 * Created: 08-06-2020
 * This interface is responsible for:
 *
 */
public interface IUserDAO {
    User getUser(int userId) throws DALException;
    List<User> getUserList() throws DALException;
    void createUser(User user) throws DALException, JunkFormatException;
    void updateUser(User user) throws DALException, JunkFormatException;
    void setIsActive(int userId, boolean isActive) throws DALException;
}
