package test;

import DAL.interfaces.DALException;
import DAL.interfaces.ICommodityDAO;
import DAL.interfaces.IReceiptDAO;
import DAL.interfaces.JunkFormatException;
import DAL.nonPersistent.DummyDataGenerator;
import DAL.nonPersistent.ReceiptDAONonPersistent;
import DAL.persistent.CommodityDAO;
import DAL.persistent.FileAPI;
import DAL.persistent.ReceiptDAO;
import DAL.nonPersistent.CommodityDAONonPersistent;
import RAM.Commodity;
import RAM.ReceiptComp;
import RAM.Receipt;
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

        ICommodityDAO cdao = new CommodityDAONonPersistent();
        IReceiptDAO dao = new ReceiptDAONonPersistent(cdao);

        cdao.createCommodity(new Commodity(42, "banana",true));


        List<ReceiptComp> components = new ArrayList<>();
        components.add(new ReceiptComp(42,120, 53));

        // When i made no changes yet, the list of receipts should be 0, and i get no errors.

        assertTrue(dao.getReceiptList().size() == 0);

        // When creating a receipt object, it gets inserted into the receipts list.

        Receipt rec = new Receipt(1, "name", components, true);
        dao.createReceipt(rec);

        assertTrue(dao.getReceiptList().size() == 1);

        // When trying to add a new receipt with the same ID, i expect an exception.

        try {
            dao.createReceipt(rec);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }


        // When I add a receipt which has no valid commodity, then fail.
        components = new ArrayList<>();
        components.add(new ReceiptComp(13,120, 53));
        rec = new Receipt(2, "name", components, true);

        try{
            dao.createReceipt(rec);
            assertTrue(false);
        } catch (DALException e){
            assertTrue(true);
            assertEquals(e.getMessage(), "There is no commodityIds in the database which have the Ids: [[13]]");
        }
    }

    @Test
    public void testGetReceipt() throws DALException, JunkFormatException
    {
        ICommodityDAO cdao = new CommodityDAONonPersistent();
        IReceiptDAO dao = new ReceiptDAONonPersistent(cdao);

        //This is tested in a previous test.
        List<ReceiptComp> components = new ArrayList<>();
        Receipt rec = new Receipt(1, "name", components, true);
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
    public void testSetIsActive() throws DALException, JunkFormatException
    {

        ICommodityDAO cdao = new CommodityDAONonPersistent();
        IReceiptDAO dao = new ReceiptDAONonPersistent(cdao);


        //This is tested in a previous test.
        List<ReceiptComp> components = new ArrayList<>();
        Receipt rec = new Receipt(1, "name", components, true);
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
        ICommodityDAO cdao = new CommodityDAONonPersistent();
        DummyDataGenerator DDG = new DummyDataGenerator(4);
        DDG.generateCommodities(cdao);

        IReceiptDAO DAO = new ReceiptDAO(FileAPI.TEST_RECEIPT_DAO_FILE, cdao);
        DDG.generateReceipts(DAO);

        IReceiptDAO newDAO = new ReceiptDAO(FileAPI.TEST_RECEIPT_DAO_FILE,cdao); //creat a new DAO with the data in the file


        for (int i = 0; i < DAO.getReceiptList().size(); i++) {
            assertEquals(DAO.getReceipt(DAO.getReceiptList().get(i).getID()).toString(), newDAO.getReceipt(DAO.getReceiptList().get(i).getID()).toString());
        }

    }

    @Test
    public void persistentGetReceiptList() throws DALException, IOException, ClassNotFoundException {
        File file = new File(FileAPI.TEST_RECEIPT_DAO_FILE);
        file.delete();
        ICommodityDAO cdao = new CommodityDAONonPersistent();
        DummyDataGenerator DDG = new DummyDataGenerator(4);
        DDG.generateCommodities(cdao);

        IReceiptDAO DAO = new ReceiptDAO(FileAPI.TEST_RECEIPT_DAO_FILE, cdao);

        DDG.generateReceipts(DAO);
        IReceiptDAO newDAO = new ReceiptDAO(FileAPI.TEST_RECEIPT_DAO_FILE, cdao); //creat a new DAO with the data in the file

        List<Receipt> fromRam = DAO.getReceiptList();
        List<Receipt> fromFile = newDAO.getReceiptList();

        for (int i = 0; i < fromRam.size(); i++) {
            assertEquals(fromRam.get(i).toString(), fromFile.get(i).toString());
        }
    }

    @Test
    public void persistentCreateReceiptTest() throws JunkFormatException, DALException, IOException, ClassNotFoundException {
        File file = new File(FileAPI.TEST_RECEIPT_DAO_FILE);
        file.delete();

        ICommodityDAO cdao = new CommodityDAONonPersistent();
        DummyDataGenerator DDG = new DummyDataGenerator(4);
        DDG.generateCommodities(cdao);
        IReceiptDAO receiptDAO = new ReceiptDAO(FileAPI.TEST_RECEIPT_DAO_FILE, cdao);

        List<ReceiptComp> compList = new ArrayList<>();
        for (int x = 0; x < 4; x++) {
            compList.add(new ReceiptComp(1 * x, 0.1 * x, 0.2 * x));
        }
        Receipt randRecipt = new Receipt(9000, "SKKKK", compList, true);
        receiptDAO.createReceipt(randRecipt);
        assertEquals(randRecipt.toString(), receiptDAO.getReceipt(9000).toString());

        IReceiptDAO receiptDAO2 = new ReceiptDAO(FileAPI.TEST_RECEIPT_DAO_FILE,cdao);

        assertEquals(randRecipt.toString(), receiptDAO2.getReceipt(9000).toString());
    }

    @Test
    public void persistentSetIsActive() throws DALException, JunkFormatException, IOException, ClassNotFoundException {
        File file = new File(FileAPI.TEST_RECEIPT_DAO_FILE);
        file.delete();
        ICommodityDAO cdao = new CommodityDAONonPersistent();
        DummyDataGenerator DDG = new DummyDataGenerator(4);
        DDG.generateCommodities(cdao);
        ReceiptDAO dao = new ReceiptDAO(FileAPI.TEST_RECEIPT_DAO_FILE,cdao);
        DDG.generateReceipts(dao);

        List<Receipt> fromRam = dao.getReceiptList();

        for(Receipt rec : fromRam){
            assertTrue(rec.getIsActive());
            //System.out.println("Before:");
            //System.out.println(rec.getID() + " " + rec.getIsActive());
            dao.setIsActive(rec.getID(),false);
            //System.out.println("After:");
            //System.out.println(dao.getReceipt(rec.getID()).getIsActive()+ " " + rec.getIsActive());
            //System.out.println("-------------------");
            assertFalse(dao.getReceipt(rec.getID()).getIsActive());
        }

        // When trying to set all the receipts activity to false, which they already are, an Exception will get thrown.

        ReceiptDAO dao2 = new ReceiptDAO(FileAPI.TEST_RECEIPT_DAO_FILE,cdao);
        List<Receipt> fromFile = dao2.getReceiptList();

        fromRam = dao2.getReceiptList();

        for (Receipt rec : fromRam)
        {
            assertFalse(rec.getIsActive());

            try {
                dao.setIsActive(rec.getID(), false);
                assertTrue(false);
            } catch (Exception e){
                assertTrue(true);
            }
            
        }

    }
}
