package test;

import DAL.interfaces.DALException;
import DAL.interfaces.IReceiptDAO;
import DAL.interfaces.JunkFormatException;
import DAL.nonPersistent.DummyDataGenerator;
import DAL.nonPersistent.ReceiptDAONonPersistent;
import DAL.persistent.FileAPI;
import DAL.persistent.ReceiptDAO;
import DTO.ReceiptCompDTO;
import DTO.ReceiptDTO;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestReceiptDAO {

    @Test
    public void testCreateReceipt() throws DALException, JunkFormatException {
        IReceiptDAO dao = new ReceiptDAONonPersistent();

        List<ReceiptCompDTO> components = new ArrayList<>();

        // When i made no changes yet, the list of receipts should be 0, and i get no errors.

        assertTrue(dao.getReceiptList().size() == 0);

        // When creating a receipt object, it gets inserted into the receipts list.

        ReceiptDTO rec = new ReceiptDTO(1, "name", components, true);
        dao.createReceipt(rec);

        assertTrue(dao.getReceiptList().size() == 1);

        // When trying to add a new receipt with the same ID, i expect an exception.

        try {
            dao.createReceipt(rec);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testGetReceipt() throws DALException, JunkFormatException {
        IReceiptDAO dao = new ReceiptDAONonPersistent();

        //This is tested in a previous test.
        List<ReceiptCompDTO> components = new ArrayList<>();
        ReceiptDTO rec = new ReceiptDTO(1, "name", components, true);
        dao.createReceipt(rec);

        // When trying to get a receipt with ID 1, i get a receipt with name "name".

        assertEquals(dao.getReceipt(1).getName(), "name");

        // When trying to get a receipt with ID 1234, i get an exception because it doesn't exist.

        try {
            dao.getReceipt(1234);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }

    }

    @Test
    public void testSetIsActive() throws DALException, JunkFormatException {
        IReceiptDAO dao = new ReceiptDAONonPersistent();

        //This is tested in a previous test.
        List<ReceiptCompDTO> components = new ArrayList<>();
        ReceiptDTO rec = new ReceiptDTO(1, "name", components, true);
        dao.createReceipt(rec);

        // When i try to set "isActive" for the receipt with ID 1 to "false", it will do that.

        dao.setIsActive(1, false);
        assertFalse(dao.getReceipt(1).getIsActive());

        // When i do it again, the activity is already false, so it should throw an exception.

        try {
            dao.setIsActive(1, false);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }

    }
//////////////////////////////
//Persistency tests below
//////////////////////////////

    @Test
    public void persistentGetReceipt() throws DALException, IOException, ClassNotFoundException {
        File file = new File(FileAPI.TEST_RECEIPT_DAO_FILE);
        file.delete();
        IReceiptDAO DAO = new ReceiptDAO(FileAPI.TEST_RECEIPT_DAO_FILE);
        DummyDataGenerator DDG = new DummyDataGenerator(4);
        DDG.generateReceipts(DAO);
        IReceiptDAO newDAO = new ReceiptDAO(FileAPI.TEST_RECEIPT_DAO_FILE); //creat a new DAO with the data in the file


        for (int i = 0; i < DAO.getReceiptList().size(); i++) {
            assertEquals(DAO.getReceipt(i).toString(), newDAO.getReceipt(i).toString());
        }

    }

    @Test
    public void persistentGetReceiptList() throws DALException, IOException, ClassNotFoundException {
        File file = new File(FileAPI.TEST_RECEIPT_DAO_FILE);
        file.delete();
        IReceiptDAO DAO = new ReceiptDAO(FileAPI.TEST_RECEIPT_DAO_FILE);
        DummyDataGenerator DDG = new DummyDataGenerator(4);
        DDG.generateReceipts(DAO);
        IReceiptDAO newDAO = new ReceiptDAO(FileAPI.TEST_RECEIPT_DAO_FILE); //creat a new DAO with the data in the file

        List<ReceiptDTO> fromRam = DAO.getReceiptList();
        List<ReceiptDTO> fromFile = newDAO.getReceiptList();

        for (int i = 0; i < fromRam.size(); i++) {
            assertEquals(fromRam.get(i).toString(), fromFile.get(i).toString());
        }
    }

    @Test
    public void persistentCreateReceiptTest() throws JunkFormatException, DALException, IOException, ClassNotFoundException {
        File file = new File(FileAPI.TEST_RECEIPT_DAO_FILE);
        file.delete();
        IReceiptDAO receiptDAO = new ReceiptDAO(FileAPI.TEST_RECEIPT_DAO_FILE);

        List<ReceiptCompDTO> compList = new ArrayList<>();
        for (int x = 0; x < 4; x++) {
            compList.add(new ReceiptCompDTO(1 * x, 0.1 * x, 0.2 * x));
        }
        ReceiptDTO randRecipt = new ReceiptDTO(9000, "SKKKK", compList, true);
        receiptDAO.createReceipt(randRecipt);
        assertEquals(randRecipt.toString(), receiptDAO.getReceipt(9000).toString());

        IReceiptDAO receiptDAO2 = new ReceiptDAO(FileAPI.TEST_RECEIPT_DAO_FILE);

        assertEquals(randRecipt.toString(), receiptDAO2.getReceipt(9000).toString());
    }

    @Test
    public void persistentSetIsActive() throws DALException, JunkFormatException, IOException, ClassNotFoundException {
        File file = new File(FileAPI.TEST_RECEIPT_DAO_FILE);
        file.delete();
        DummyDataGenerator DDG = new DummyDataGenerator(4);
        ReceiptDAO dao = new ReceiptDAO(FileAPI.TEST_RECEIPT_DAO_FILE);
        DDG.generateReceipts(dao);

        List<ReceiptDTO> fromRam = dao.getReceiptList();
        for(ReceiptDTO x : fromRam){
            assertTrue(x.getIsActive());
        }
        for(int i = 0; i < fromRam.size(); i++){
            dao.setIsActive(i,false);
        }
        for(ReceiptDTO x : fromRam){
            assertFalse(x.getIsActive());
        }

        ReceiptDAO dao2 = new ReceiptDAO(FileAPI.TEST_RECEIPT_DAO_FILE);
        List<ReceiptDTO> fromFile = dao2.getReceiptList();
        for(ReceiptDTO x : fromFile){
            assertFalse(x.getIsActive());
        }
        for(int i = 0; i < fromFile.size(); i++){
            dao2.setIsActive(i,true);
        }
        for(ReceiptDTO x : fromFile){
            assertTrue(x.getIsActive());
        }


    }
}
