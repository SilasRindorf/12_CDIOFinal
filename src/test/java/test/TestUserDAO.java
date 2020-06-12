package test;

import DAL.interfaces.DALException;
import DAL.interfaces.IUserDAO;
import DAL.interfaces.JunkFormatException;
import DAL.nonPersistent.UserDAONonPersistent;
import DAL.nonPersistent.DummyDataGenerator;
import DTO.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
    public void addANewUserWithUniqueID() throws JunkFormatException, DALException {
        // Generate
        IUserDAO userDAO = new UserDAONonPersistent();
        DummyDataGenerator generator = new DummyDataGenerator(13);
        generator.generateUsers(userDAO);

       // Find unused id
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 11; i<100; ++i){
            arr.add(i);
        }
        for(UserDTO user : userDAO.getUserList() ){
            arr.remove((Integer) user.getID());
        }

        // Add user
        UserDTO newUser = new UserDTO(arr.get(0),"Bob Brick", "BB", "0505934433", "GQsFm?=Ty=HcHGe+y-+", UserDTO.Role.Administrator, true);
        userDAO.createUser(newUser);
        assertTrue(userDAO.getUserList().contains(newUser));
    }

    @Test
    public void addANewUserWithExistingID() throws JunkFormatException, DALException {
        // Generate
        IUserDAO userDAO = new UserDAONonPersistent();
        DummyDataGenerator generator = new DummyDataGenerator(13);
        generator.generateUsers(userDAO);
        Random rand = new Random(13);


        List<UserDTO> list = userDAO.getUserList();
        UserDTO someuser = list.get(rand.nextInt(list.size()));
        int existingID = someuser.getID();

        boolean contains16 = false;
        for(int i = 0; i<list.size(); ++i){
            if(list.get(i).getID()==16){
                contains16=true;
            }
        }
        assertTrue(contains16);
        UserDTO newUser = new UserDTO(16,"Bob Brick", "BB", "0505934433", "GQsFm?=Ty=HcHGe+y-+", UserDTO.Role.Administrator, true);
        try {
            userDAO.createUser(newUser);
            assertTrue(false);
        }catch(DALException e){
            assertTrue(true);
        }





    }


}

