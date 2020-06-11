package DAL.interfaces;

import DTO.UserDTO;
import DAL.interfaces.DALException;
import DAL.interfaces.JunkFormatException;
import java.util.List;

/***
 * Initial version created by: Taken from CDIO 1
 * Edited by: Silas
 * Created: 08-06-2020
 * This interface is responsible for:
 *
 */
public interface IUserDAO {
    UserDTO getUser(int userId) throws DALException;
    List<UserDTO> getUserList() throws DALException;
    void createUser(UserDTO user) throws DALException, JunkFormatException;
    void updateUser(UserDTO user) throws DALException, JunkFormatException;
    void setIsActive(int userId, boolean isActive) throws DALException;
}
