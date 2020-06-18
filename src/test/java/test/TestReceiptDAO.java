package test;

import DAL.interfaces.DALException;
import DAL.interfaces.ICommodityDAO;
import DAL.interfaces.IReceiptDAO;
import DAL.interfaces.JunkFormatException;
import DAL.nonPersistent.CommodityDAONonPersistent;
import DAL.nonPersistent.ReceiptDAONonPersistent;
import RAM.Commodity;
import RAM.ReceiptComp;
import RAM.Receipt;
import org.junit.Test;

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

        try{
            dao.createReceipt(rec);
            assertTrue(false);
        } catch (Exception e){
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

        try{
            dao.setIsActive(1, false);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }

    }

}
