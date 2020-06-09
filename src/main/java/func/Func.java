package func;

import DTO.UserDTO;

import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 09-06-2020
 * This class is responsible for:
 *  -
 */
public class Func implements IFunc {
    public UserDTO createUser(int userID, String userName, String cpr, List<String> roles) throws UserFormatException, DatabaseException {
        return null;
    }

    public List<UserDTO> getUserList() throws DatabaseException {
        return null;
    }

    public UserDTO getUser(int userID) throws DatabaseException {
        return null;
    }

    public UserDTO deleteUser(int userID) throws DatabaseException {
        return null;
    }
}
