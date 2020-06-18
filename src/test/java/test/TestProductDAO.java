package test;

import DAL.interfaces.DALException;
import DAL.interfaces.IProductDAO;
import DAL.interfaces.IUserDAO;
import DAL.interfaces.JunkFormatException;
import DAL.nonPersistent.DummyDataGenerator;
import DAL.nonPersistent.ProductDAONonPersistent;
import DAL.persistent.FileAPI;
import DAL.persistent.ProductDAO;
import DAL.persistent.UserDAO;
import DTO.IdAndActivatable;
import DTO.ProductBatchCompDTO;
import DTO.ProductBatchDTO;
import DTO.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.*;


/***
 * Initial version created by: Andreas
 * Edited by:
 * Created: 15-06-2020
 * This class is responsible for:
 *  Testing ProductDAO classes
 */
public class TestProductDAO {

    //Since "getBatchList" is basically just a getter, i will assume that works,
    //and use it to test the other methods in ProductDAONonPersistent

    @Test
    public void testCreateBatch() throws DALException
    {
        // Create ProductDAONonPersistent instance.
        IProductDAO batches = new ProductDAONonPersistent();
        Date date = new Date(2014, 02, 11);
        List<ProductBatchCompDTO> products = new ArrayList<ProductBatchCompDTO>();

        List<ProductBatchDTO> productBatches = batches.getBatchList();
        // Before adding anything to the productbatchces, i assume the list has length 0.
        assertTrue(productBatches.size() == 0);

        // When adding a new batch, i expect no exceptions.
        ProductBatchDTO batch = new ProductBatchDTO(1, 1, date,ProductBatchDTO.Status.CREATED,products, true);
        String message = "";
        try {
            batches.createBatch(batch);
        } catch (Exception e){
            message = e.getMessage();
        }
        assertEquals(message, "");

        // After the batch is added, i expect the list of batches to have increased to size 1.
        productBatches = batches.getBatchList();
        assertTrue(productBatches.size() == 1);

        // When adding a batch with the same productBatchNr (ID) as the previous one, i expect an exception.
        message = "";
        try {
            batches.createBatch(batch);
        } catch (Exception e){
            message = e.getMessage();
        }
        assertNotEquals(message, "");

        // After this action, i expect the length to still be one.
        productBatches = batches.getBatchList();
        assertTrue(productBatches.size() == 1);
    }

    @Test
    public void testGetBatch(){
        // Create ProductDAONonPersistent instance.
        IProductDAO batches = new ProductDAONonPersistent();
        Date date = new Date(2014, 02, 11);
        List<ProductBatchCompDTO> products = new ArrayList<ProductBatchCompDTO>();

        // These lines are tested in a previous test, so i assume they work.
        ProductBatchDTO batch = new ProductBatchDTO(1, 1, date,ProductBatchDTO.Status.CREATED,products, true);

        String message = "";
        try {
            batches.createBatch(batch);
        } catch (Exception e){

        }

        // When trying to get a batch that doesn't exist, we get an exception

        message = "";
        try {
            ProductBatchDTO fetchedBatch = batches.getBatch(-1);
        } catch (Exception e){
            message = e.getMessage();
        }
        assertNotEquals(message, "");

        // When trying to get a batch that exists, we get the batch.

        message = "";
        try {
            ProductBatchDTO fetchedBatch = batches.getBatch(1);
            assertTrue(fetchedBatch.getStatus() == ProductBatchDTO.Status.CREATED);
        } catch (Exception e){
            message = e.getMessage();
        }
        assertEquals(message, "");

    }

    @Test
    public void testUpdateBatch() {
        // Create ProductDAONonPersistent instance.
        IProductDAO batches = new ProductDAONonPersistent();
        Date date = new Date(2014, 02, 11);
        List<ProductBatchCompDTO> products = new ArrayList<ProductBatchCompDTO>();

        // These lines are tested in a previous test, so i assume they work.
        ProductBatchDTO batch = new ProductBatchDTO(1, 1, date,ProductBatchDTO.Status.CREATED,products, true);

        String message = "";
        try {
            batches.createBatch(batch);
        } catch (Exception e){

        }

        // Tying to update the Status on a productbatch that doesn't exist should throw an exception
        ProductBatchDTO newbatch = new ProductBatchDTO(1234, 1, date,ProductBatchDTO.Status.IN_PRODUCTION,products, true);
        message = "";
        try{
            batches.updateBatch(newbatch);
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertNotEquals(message, "");

        // When updating a productbatch that exists, the list should stay the same size, and the list should contain
        // the updated object.

        newbatch = new ProductBatchDTO(1, 1, date,ProductBatchDTO.Status.IN_PRODUCTION,products, true);
        int sizebefore= -1, sizeafter = -2;
        try{
            sizebefore = batches.getBatchList().size();
            batches.updateBatch(newbatch);
            sizeafter = batches.getBatchList().size();
            ProductBatchDTO updatedBatch = batches.getBatch(1);
            assertTrue(updatedBatch.getStatus() == ProductBatchDTO.Status.IN_PRODUCTION);
        } catch (Exception e){

        }
        assertEquals(sizebefore, sizeafter);

    }

    @Test
    public void testSetIsActiveBatch(){
        // Create ProductDAONonPersistent instance.
        IProductDAO batches = new ProductDAONonPersistent();
        Date date = new Date(2014, 02, 11);
        List<ProductBatchCompDTO> products = new ArrayList<ProductBatchCompDTO>();

        // These lines are tested in a previous test, so i assume they work.
        ProductBatchDTO batch = new ProductBatchDTO(1, 1, date,ProductBatchDTO.Status.CREATED,products, true);

        String message = "";
        try {
            batches.createBatch(batch);
        } catch (Exception e){

        }

        // This method uses "getBatch()" to fetch the batch which means, that if i try with an ID that doesn't exist,
        // the test from previous method covers this case.

        // When trying to change batch from "true" to "true", i get told that it was already true in the first place.

        try {
            batches.setIsActive(1, true);
        } catch (Exception e){
            message = e.getMessage();
        }
        assertEquals(message, "The productbatch activity is already "+true);

        // When trying to change activity from true to false, it changes the activity.

        message = "";
        try {
            batches.setIsActive(1, false);
            assertFalse(batches.getBatch(1).getIsActive());
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals(message, "");

    }


    ///////////////////////////////////
    // Persistency tests below
    ///////////////////////////////////

    @Test
    public void testCreateBatchPersistent() throws DALException, JunkFormatException, IOException, ClassNotFoundException {
        // When i create a Productbatch and save it persistently, i'll be able to open collect the batch
        // from an instance of ProductDAO that didn't create the item.

        List<ProductBatchCompDTO> products = new ArrayList<ProductBatchCompDTO>();

        File file = new File(FileAPI.TEST_PRODUCT_DAO_FILE);
        file.delete();

        ProductBatchDTO a = new ProductBatchDTO(1,1, new Date(2014, 02, 11), ProductBatchDTO.Status.CREATED, products, true);

        IProductDAO dao = new ProductDAO(FileAPI.TEST_PRODUCT_DAO_FILE);

        // dao creates the batch
        dao.createBatch(a);

        // When creating dao2, it will load everything in the file at this point in time.
        IProductDAO dao2 = new ProductDAO(FileAPI.TEST_PRODUCT_DAO_FILE);

        // both dao and dao2 tries to collect the item, and then compares them.
        ProductBatchDTO expected = dao.getBatchList().get(0);
        ProductBatchDTO got = dao2.getBatchList().get(0);
        assertEquals(expected.getID(), got.getID());
        assertEquals(expected.getIsActive(), got.getIsActive());
        assertEquals(expected.getStatus(), got.getStatus());
        assertEquals(expected.getCreated(), got.getCreated());
        assertEquals(expected.getReceipt(), got.getReceipt());
        assertEquals(expected.getProductComps(), got.getProductComps());

    }

    @Test
    public void testUpdateBatchPersistent() throws DALException, JunkFormatException, IOException, ClassNotFoundException {

        // When updating a batch, i expect the batch to get updated for both instances of the ProductDAO
        List<ProductBatchCompDTO> products = new ArrayList<ProductBatchCompDTO>();

        File file = new File(FileAPI.TEST_PRODUCT_DAO_FILE);
        file.delete();

        IProductDAO dao = new ProductDAO(FileAPI.TEST_PRODUCT_DAO_FILE);

        ProductBatchDTO a = new ProductBatchDTO(1,1, new Date(2014, 02, 11), ProductBatchDTO.Status.CREATED, products, true);
        ProductBatchDTO b = new ProductBatchDTO(1,1234, new Date(2015, 12, 16), ProductBatchDTO.Status.IN_PRODUCTION, products, false);

        // I create the first object (a) from dao, then overwrite it with the second object (b) from dao.
        dao.createBatch(a);
        dao.updateBatch(b);

        IProductDAO dao2 = new ProductDAO(FileAPI.TEST_PRODUCT_DAO_FILE);

        // Then i use dao2 to collect whatever it has stored in the file it's pointed to, and compares it to
        // the second object (b)
        ProductBatchDTO expected = b;
        ProductBatchDTO got = dao2.getBatchList().get(0);
        assertEquals(expected.getID(), got.getID());
        assertEquals(expected.getIsActive(), got.getIsActive());
        assertEquals(expected.getStatus(), got.getStatus());
        assertEquals(expected.getCreated(), got.getCreated());
        assertEquals(expected.getReceipt(), got.getReceipt());
        assertEquals(expected.getProductComps(), got.getProductComps());
    }

    @Test
    public void testSetIsActivePersistent() throws DALException, IOException, ClassNotFoundException, JunkFormatException {

        List<ProductBatchCompDTO> products = new ArrayList<ProductBatchCompDTO>();

        File file = new File(FileAPI.TEST_PRODUCT_DAO_FILE);
        file.delete();

        IProductDAO dao = new ProductDAO(FileAPI.TEST_PRODUCT_DAO_FILE);

        ProductBatchDTO a = new ProductBatchDTO(1,1, new Date(2014, 02, 11), ProductBatchDTO.Status.CREATED, products, true);
        dao.createBatch(a);

        // When i try to set the productbatch with ID 1 to false from dao, i expect it to happen in dao2 aswell.
        dao.setIsActive(1,false);

        IProductDAO dao2 = new ProductDAO(FileAPI.TEST_PRODUCT_DAO_FILE);

        assertFalse(dao2.getBatch(1).getIsActive());

        // When i then try to set it "false" again from dao2 this time, i expect it to throw an exception.
        // saying, that the productbatch already has activity = false.

        try{
            dao2.setIsActive(1, false);
            assertTrue(false);
        } catch (Exception e){
            assertTrue(true);
        }
    }
}


