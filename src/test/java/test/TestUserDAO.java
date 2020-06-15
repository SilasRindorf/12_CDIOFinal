package test;

import DAL.interfaces.DALException;
import DAL.interfaces.IUserDAO;
import DAL.interfaces.JunkFormatException;
import DAL.nonPersistent.UserDAONonPersistent;
import DAL.nonPersistent.DummyDataGenerator;
import DAL.persistent.FileAPI;
import DAL.persistent.UserDAO;
import DTO.IdAndActivatable;
import DTO.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;
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

    @Test
    public void getUser() {
        IUserDAO users = new UserDAONonPersistent();
        String message = "";
        try {
            message = users.getUser(-1).toString();
        } catch (DALException e) {
            message = e.getMessage();
        }
        assertEquals(message, "There is no user where ID=-1");
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
    @Test
    public void getExistingUser() throws JunkFormatException, DALException {
        // Generate
        IUserDAO userDAO = new UserDAONonPersistent();
        DummyDataGenerator generator = new DummyDataGenerator(13);
        generator.generateUsers(userDAO);
        UserDTO newUser = new UserDTO(11,"Bob Brick", "BB", "0505934433", "GQsFm?=Ty=HcHGe+y-+", UserDTO.Role.Administrator, true);

        userDAO.createUser(newUser);

        assertEquals(userDAO.getUser(11),newUser);
    }

    @Test
    public void getUserListTest() throws JunkFormatException, DALException {
        IUserDAO userDAO = new UserDAONonPersistent();
        UserDTO a = new UserDTO(12,"Alice Andersen", "AA", "0505931234", "GQsFm?=Toeutnhy=HcHGe+y-+", UserDTO.Role.Administrator, true);
        UserDTO b = new UserDTO(36,"Bob Brick", "BB", "0505934433", "GQsFm?=Tyoue=HcHGe+y-+", UserDTO.Role.Farmaceut, true);
        UserDTO c = new UserDTO(29,"Charles Champagne", "CC", "0706034435", "GQsFm?=Ty=HoeucHGe+y-+", UserDTO.Role.Laborant, true);
        userDAO.createUser(a);
        userDAO.createUser(b);
        userDAO.createUser(c);
        DummyDataGenerator generator = new DummyDataGenerator(15);
        generator.generateUsers(userDAO);
        assertTrue(userDAO.getUserList().size()>20);
        assertTrue(userDAO.getUserList().contains(a));
        assertTrue(userDAO.getUserList().contains(b));
        assertTrue(userDAO.getUserList().contains(c));
    }

    public List<UserDTO> makeListWithoutID(List<UserDTO> list, int id){
        List<UserDTO> arr = new ArrayList<>();
        for(UserDTO user : list){
            if(user.getID()!=id){
                arr.add(user);
            }
        }
        return arr;
    }

    @Test
    public void updateUserTest()throws DALException, JunkFormatException{
        // Generate list, pick user, save deep copy of list without user
        // Update user, check if users are different now
        // Check if lists without user are identical
        IUserDAO userDAO = new UserDAONonPersistent();
        DummyDataGenerator generator = new DummyDataGenerator(13);
        generator.generateUsers(userDAO);

        UserDTO pickedUser = userDAO.getUserList().get(5);
        List<UserDTO> list1 = makeListWithoutID(userDAO.getUserList(),pickedUser.getID());
        UserDTO updatedUser = new UserDTO(pickedUser.getID(),pickedUser.getUsername()+"e", "HEHE", pickedUser.getCPR(),pickedUser.getHashedPass(), pickedUser.getRole(),pickedUser.getIsActive());
        userDAO.updateUser(updatedUser);
        List<UserDTO> list2 = makeListWithoutID(userDAO.getUserList(),pickedUser.getID());
        assertEquals(list1, list2);
        assertNotEquals(pickedUser, userDAO.getUser(pickedUser.getID()));
        assertEquals(pickedUser.getID(), userDAO.getUser(pickedUser.getID()).getID());
    }

    @Test
    public void disableActivity()throws DALException, JunkFormatException{
        IUserDAO userDAO = new UserDAONonPersistent();
        UserDTO a = new UserDTO(12,"Alice Andersen", "AA", "0505931234", "GQsFm?=Toeutnhy=HcHGe+y-+", UserDTO.Role.Administrator, true);
        userDAO.createUser(a);
        assertTrue(userDAO.getUser(12).getIsActive());
        DummyDataGenerator generator = new DummyDataGenerator(13);
        generator.generateUsers(userDAO);
        userDAO.setIsActive(12,false);
        assertFalse(userDAO.getUser(12).getIsActive());
    }

    @Test
    public void enableActivity()throws DALException, JunkFormatException{
        IUserDAO userDAO = new UserDAONonPersistent();
        UserDTO a = new UserDTO(12,"Alice Andersen", "AA", "0505931234", "GQsFm?=Toeutnhy=HcHGe+y-+", UserDTO.Role.Administrator, false);
        userDAO.createUser(a);
        assertFalse(userDAO.getUser(12).getIsActive());
        DummyDataGenerator generator = new DummyDataGenerator(13);
        generator.generateUsers(userDAO);
        userDAO.setIsActive(12,true);
        assertTrue(userDAO.getUser(12).getIsActive());
    }

    @Test
    public void doubleEnable()throws DALException, JunkFormatException{
        IUserDAO userDAO = new UserDAONonPersistent();
        UserDTO a = new UserDTO(12,"Alice Andersen", "AA", "0505931234", "GQsFm?=Toeutnhy=HcHGe+y-+", UserDTO.Role.Administrator, true);
        userDAO.createUser(a);
        assertTrue(userDAO.getUser(12).getIsActive());
        DummyDataGenerator generator = new DummyDataGenerator(13);
        generator.generateUsers(userDAO);
        String message = "";
        try {
            userDAO.setIsActive(12, true);
        }catch(DALException e){
            message = e.getMessage();
        }
        assertEquals(message, "The user activity is already true");
        assertTrue(userDAO.getUser(12).getIsActive());
    }
    @Test
    public void doubleDisable()throws DALException, JunkFormatException{
        IUserDAO userDAO = new UserDAONonPersistent();
        UserDTO a = new UserDTO(12,"Alice Andersen", "AA", "0505931234", "GQsFm?=Toeutnhy=HcHGe+y-+", UserDTO.Role.Administrator, false);
        userDAO.createUser(a);
        assertFalse(userDAO.getUser(12).getIsActive());
        DummyDataGenerator generator = new DummyDataGenerator(13);
        generator.generateUsers(userDAO);
        String message = "";
        try {
            userDAO.setIsActive(12, false);
        }catch(DALException e){
            message = e.getMessage();
        }
        assertEquals(message, "The user activity is already false");
        assertFalse(userDAO.getUser(12).getIsActive());
    }

    ///////////////////////////////////
    // Persistency tests belowt
    ///////////////////////////////////

    @Test
    public void testPersistency() throws JunkFormatException, DALException, IOException, ClassNotFoundException {
        //// Persistent after reloading file
        File file = new File(FileAPI.TEST_USER_DAO_FILE);
        file.delete();
        IUserDAO dao = new UserDAO(FileAPI.TEST_USER_DAO_FILE);
        DummyDataGenerator generator = new DummyDataGenerator(13);
        UserDTO a = new UserDTO(11,"Bob Brick", "BB", "0505934433", "GQsFm?=Ty=HcHGe+y-+", UserDTO.Role.Administrator, true);
        dao.createUser(a);
        IUserDAO dao2 = new UserDAO(FileAPI.TEST_USER_DAO_FILE);
        for(int i = 0; i<dao.getUserList().size(); ++i) {
            UserDTO expected = dao.getUserList().get(i);
            UserDTO got = dao2.getUserList().get(i);
            assertEquals(expected.getID(), got.getID());
            assertEquals(expected.getIsActive(), got.getIsActive());
            assertEquals(expected.getCPR(), got.getCPR());
            assertEquals(expected.getHashedPass(), got.getHashedPass());
            assertEquals(expected.getIni(), got.getIni());
            assertEquals(expected.getRole(), got.getRole());
        }



    }
    @Test
    public void canStartWithEmptyFileMultipleTimesWithoutError(){
        // Can start with empty file multiple times
        File file = new File(FileAPI.TEST_USER_DAO_FILE);
        file.delete();
        IUserDAO dao = new UserDAO(FileAPI.TEST_USER_DAO_FILE);
        try {
            dao = new UserDAO(FileAPI.TEST_USER_DAO_FILE);
            assertTrue(true); //reached end of test
        }catch(Exception e){
            assertTrue(false); //reached end of test
            e.printStackTrace();
        }
    }
    @Test
    public void persistentSetActive() throws DALException, IOException, ClassNotFoundException, JunkFormatException {
        //Persistent set active
        File file = new File(FileAPI.TEST_USER_DAO_FILE);
        file.delete();
        IUserDAO dao = new UserDAO(FileAPI.TEST_USER_DAO_FILE);
        UserDTO a = new UserDTO(12,"Alice Andersen", "AA", "0505931234", "GQsFm?=Toeutnhy=HcHGe+y-+", UserDTO.Role.Administrator, true);
        dao.createUser(a);
        dao.setIsActive(12,false);
        IUserDAO dao2 = new UserDAO(FileAPI.TEST_USER_DAO_FILE);
        assertFalse(dao2.getUser(12).getIsActive());
        dao2.setIsActive(12, true);
        UserDAO dao3  = new UserDAO(FileAPI.TEST_USER_DAO_FILE);
        assertTrue(dao3.getUser(12).getIsActive());
    }


}

