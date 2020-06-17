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
    public void testCreateReceipt() throws DALException, JunkFormatException
    {
        IReceiptDAO dao = new ReceiptDAONonPersistent();

        List<ReceiptCompDTO> components = new ArrayList<>();

        // When i made no changes yet, the list of receipts should be 0, and i get no errors.

        assertTrue(dao.getReceiptList().size() == 0);

        // When creating a receipt object, it gets inserted into the receipts list.

        ReceiptDTO rec = new ReceiptDTO(1, "name", components, true);
        dao.createReceipt(rec);

        assertTrue(dao.getReceiptList().size() == 1);

        // When trying to add a new receipt with the same ID, i expect an exception.

        try{
            dao.createReceipt(rec);
            assertTrue(false);
        } catch (Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testGetReceipt() throws DALException, JunkFormatException
    {
        IReceiptDAO dao = new ReceiptDAONonPersistent();

        //This is tested in a previous test.
        List<ReceiptCompDTO> components = new ArrayList<>();
        ReceiptDTO rec = new ReceiptDTO(1, "name", components, true);
        dao.createReceipt(rec);

        // When trying to get a receipt with ID 1, i get a receipt with name "name".

        assertEquals(dao.getReceipt(1).getName(), "name");

        // When trying to get a receipt with ID 1234, i get an exception because it doesn't exist.

        try{
            dao.getReceipt(1234);
            assertTrue(false);
        } catch (Exception e){
            assertTrue(true);
        }

    }

    @Test
    public void testSetIsActive() throws DALException, JunkFormatException
    {
        IReceiptDAO dao = new ReceiptDAONonPersistent();

        //This is tested in a previous test.
        List<ReceiptCompDTO> components = new ArrayList<>();
        ReceiptDTO rec = new ReceiptDTO(1, "name", components, true);
        dao.createReceipt(rec);

        // When i try to set "isActive" for the receipt with ID 1 to "false", it will do that.

        dao.setIsActive(1, false);
        assertFalse(dao.getReceipt(1).getIsActive());

        // When i do it again, the activity is already false, so it should throw an exception.

        try{
            dao.setIsActive(1, false);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }

    }
//////////////////////////////
//Persistency tests below
//////////////////////////////

//    @Test
//    public void testPersistency() throws IOException, ClassNotFoundException, DALException {
//        IReceiptDAO dao = new ReceiptDAO(FileAPI.TEST_RECEIPT_DAO_FILE);

        //for(int i =0; i<dao.getReceiptList().size();++i){
        //    System.out.println(dao.getReceiptList().get(i));
        //}
//    }


    //@org.junit.jupiter.api.Test
    //void getReceipt() throws DALException, IOException, ClassNotFoundException {
    //    File file = new File(FileAPI.TEST_RECEIPT_DAO_FILE);
    //    file.delete();
    //    IReceiptDAO DAO = new ReceiptDAO(FileAPI.TEST_RECEIPT_DAO_FILE);
    //    //DummyDataGenerator DDG = new DummyDataGenerator(4);

    //    //DDG.generateReceipts(DAO);
    //    //for(int i = 0; i<DAO.getReceiptList().size(); ++i){
    //    //    System.out.println(DAO.getReceiptList().get(i));
    //    //}
    //}

//    @Test
//    void getReceiptList() throws DALException {
//        File file = new File(FileAPI.TEST_RECEIPT_DAO_FILE);
//        file.delete();
//
//        DDG = new DummyDataGenerator(4);
//        RL = DDG.generateReceiptsAndGet(receiptDAO);
//        List<ReceiptDTO> TL = receiptDAO.getReceiptList();
//        for(int i = 0; i< RL.size(); i++){
//            assertEquals(RL.get(i).toString(), TL.get(i).toString());
//
//        }
//
//    }

    @Test
    public void createReceiptTest() throws JunkFormatException, DALException, IOException, ClassNotFoundException {
        File file = new File(FileAPI.TEST_RECEIPT_DAO_FILE);
        file.delete();
        IReceiptDAO receiptDAO = new ReceiptDAO(FileAPI.TEST_RECEIPT_DAO_FILE);

        List<ReceiptCompDTO> compList = new ArrayList<>();
       for(int x = 0; x < 4; x++){
            compList.add(new ReceiptCompDTO(1*x, 0.1*x, 0.2*x));
        }
       ReceiptDTO randRecipt = new ReceiptDTO(9000,"SKKKK",compList, true);
       receiptDAO.createReceipt(randRecipt);
        assertEquals(randRecipt.toString(), receiptDAO.getReceipt(9000).toString());

        IReceiptDAO receiptDAO2 = new ReceiptDAO(FileAPI.TEST_RECEIPT_DAO_FILE);

        assertEquals(randRecipt.toString(), receiptDAO.getReceipt(9000).toString());
    }

//    @Test
//    void setIsActive() throws DALException, JunkFormatException {
//        File file = new File(FileAPI.TEST_RECEIPT_DAO_FILE);
//        file.delete();
//        DDG = new DummyDataGenerator(4);
//        RL = DDG.generateReceiptsAndGet(receiptDAO);
//        receiptDAO.setIsActive(0, false);
//        assertEquals(false,receiptDAO.getReceipt(0).getIsActive());
//        receiptDAO.setIsActive(0, true);
//        assertEquals(true,receiptDAO.getReceipt(0).getIsActive());
//
//    }

}
