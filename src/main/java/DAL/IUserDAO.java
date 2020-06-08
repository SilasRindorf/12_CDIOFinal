package DAL;

import DTO.UserDTO;

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
    void createUser(UserDTO user) throws DALException;
    void updateUser(UserDTO user) throws DALException;
    void deleteUser(int userId) throws DALException;
    /***
     * Initial version created by: Taken from CDIO 1
     * Edited by: Silas
     * Created: 08-06-2020
     * This class is responsible for:
     *  Exception class
     *  Gets cast when a edit to IUserDAO methods fail
     */
    class DALException extends Exception {

        /**
         *
         */
        private static final long serialVersionUID = 7355418246336739229L;

        public DALException(String msg, Throwable e) {
            super(msg,e);
        }

        public DALException(String msg) {
            super(msg);
        }

    }

}
