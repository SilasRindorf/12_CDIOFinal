package test.java;

import DAL.interfaces.DALException;
import DAL.interfaces.IUserDAO;
import DAL.nonPersistent.UserDAONonPersistent;
import DAL.nonPersistent.DummyDataGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;


/***
 * Initial version created by: Silas
 * Edited by: Andreas
 * Created: 08-06-2020
 * This class is responsible for:
 *  Testing UserDAO classes
 */
public class TestUserDAO {

    private final IUserDAO USERS = new UserDAONonPersistent();

    @Test
    public void getUser() {
        IUserDAO users = USERS;
        String message = "";
        try {
            message = users.getUser(-1).toString();
        } catch (DALException e) {
            message = e.getMessage();
        }
        assertEquals(message, "User ID should be between 11 and 99 (inclusive)");
    }

    @Test
    public void getUserListTest() {
        IUserDAO users = USERS;
    }


    @Test
    public void createUserTest() {
        IUserDAO users = USERS;

    }

    @Test
    public void updateUserTest() {
        IUserDAO users = USERS;

    }

    @Test
    public void setInactiveUserTest() {
        IUserDAO users = USERS;

    }

    @Test
    public void generateAndManipulateUsersTest() {
        IUserDAO users = new UserDAONonPersistent();
        DummyDataGenerator generator = new DummyDataGenerator();
        

    }


}

