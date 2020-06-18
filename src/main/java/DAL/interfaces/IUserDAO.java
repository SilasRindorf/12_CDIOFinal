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
    /**
     * Responsibility: Creating an interface for getting an user.
     * @param userId: Is the ID of an user.
     * @return User.
     * @throws DALException:
     * Error might happen if the user ID doesn't exist.
     * Could print "User ID not found. DALException thrown.".
     */
    User getUser(int userId) throws DALException;

    /**
     * Responsibility: Creating an interface for getting an user list.
     * @return List<User>.
     * @throws DALException:
     * Error might happen if the user list doesn't exist.
     * Could print "User list not found. DALException thrown.".
     */
    List<User> getUserList() throws DALException;

    /**
     * Responsibility: Creating an interface for creating an user.
     * @param user: The user to be created.
     * @throws DALException:
     * Error might happen if the user already exist.
     * Could print "User already exist. DALException thrown.".
     * @throws JunkFormatException:
     * Error might happen if the user information isn't within the given boundaries for a given field.
     * Could print "Please fill out the fields correctly. JunkFormatException thrown.".
     */
    void createUser(User user) throws DALException, JunkFormatException;

    /**
     * Responsibility: Creating an interface for updating an user.
     * @param user: The user to be updated.
     * @throws DALException:
     * Error might happen if the user doesn't exist.
     * Could print "User not found. DALException thrown."
     * @throws JunkFormatException
     * Error might happen if the user information isn't within the given boundaries for a given field.
     * Could print "Please fill out the fields correctly. JunkFormatException thrown.".
     */
    void updateUser(User user) throws DALException, JunkFormatException;

    /**
     * Responsibility: Creating an interface for setting an user to be active or inactive.
     * @param userId: The ID of a requested user.
     * @param isActive: A boolean describing whether an user is active or inactive.
     * @throws DALException:
     * Error might happen if the user requested doesn't exist.
     * Could print "User not found. DALException thrown.".
     */
    void setIsActive(int userId, boolean isActive) throws DALException;
}
