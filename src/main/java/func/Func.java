package func;

import RAM.User;

import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 09-06-2020
 * This class is responsible for:
 *  -
 */
public class Func implements IFunc {
    public User createUser(int userID, String userName, String cpr, List<String> roles) throws UserFormatException, DatabaseException {
        return null;
    }

    public List<User> getUserList() throws DatabaseException {
        return null;
    }

    public User getUser(int userID) throws DatabaseException {
        return null;
    }

    public User deleteUser(int userID) throws DatabaseException {
        return null;
    }
}
