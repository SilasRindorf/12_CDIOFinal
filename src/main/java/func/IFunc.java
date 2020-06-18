package func;

import RAM.User;
import java.util.List;

/***
 * Initial version created by: Taken from CDIO 1
 * Edited by: Silas
 * Created: 09-06-2020
 * This class is responsible for:
 *  -
 */
public interface IFunc {
        // This method will generate the initials and password based on the given data. Returns a deep-copy of the resulting user.
        User createUser(int userID, String userName, String cpr, List<String> roles) throws UserFormatException, DatabaseException;
        // Returns a deep-copy of the list of users, as to not being able to modify it.
        List<User> getUserList() throws DatabaseException;
        // Returns a deep-copy of the user with the specific id.
        User getUser(int userID) throws DatabaseException;
        // Deletes the user with specified id. Returns a deep-copy of the user.
        User deleteUser(int userID) throws DatabaseException;

        class UserFormatException extends Exception{
            public List<errorTypes> errorlist;
            UserFormatException(String message, List<errorTypes> errorlist){
                super(message);
                this.errorlist = errorlist;
            }
            public enum errorTypes {
                ID,
                username,
                CPR,
                roles,
                password
            }
        }
        class DatabaseException extends Exception{
            DatabaseException(String message){
                super(message);
            }
        }
}