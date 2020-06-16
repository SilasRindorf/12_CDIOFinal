package test;

import DAL.interfaces.DALException;
import DAL.interfaces.ICommodityDAO;
import DAL.interfaces.JunkFormatException;
import DAL.nonPersistent.CommodityDAONonPersistent;
import DAL.nonPersistent.DummyDataGenerator;
import RAM.CommodityBatch;
import RAM.Commodity;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCommodityDAO {


    @Test
    public void getCommodityTest() throws JunkFormatException, DALException {
        ICommodityDAO dao = new CommodityDAONonPersistent();
        DummyDataGenerator data = new DummyDataGenerator(13);
        data.generateCommodities(dao);
        assertTrue(dao.getCommodityList().size()>20);
        dao.createCommodity(new Commodity(13, "Banana", true));
        Commodity ban = dao.getCommodity(13);
        assertEquals(ban.getName(), "Banana");
        assertEquals(ban.getID(), 13);
        assertEquals(ban.getIsActive(), true);
    }

    @Test
    public void getCommodityListTest() throws DALException {
        ICommodityDAO dao = new CommodityDAONonPersistent();
        DummyDataGenerator data = new DummyDataGenerator(13);
        data.generateCommodities(dao);
        assertTrue(dao.getCommodityList().size()>20);
    }

    @Test
    public void createCommodityTest() throws JunkFormatException, DALException {
        ICommodityDAO dao = new CommodityDAONonPersistent();
        DummyDataGenerator data = new DummyDataGenerator(13);
        data.generateCommodities(dao);
        assertTrue(dao.getCommodityList().size()>20);
        dao.createCommodity(new Commodity(13, "Banana", true));
        Commodity ban = dao.getCommodity(13);
        assertEquals(ban.getName(), "Banana");
        assertEquals(ban.getID(), 13);
        assertEquals(ban.getIsActive(), true);
    }

    @Test
    public void updateCommodityTest() throws DALException, JunkFormatException{
        ICommodityDAO dao = new CommodityDAONonPersistent();
        DummyDataGenerator data = new DummyDataGenerator(13);
        data.generateCommodities(dao);
        assertTrue(dao.getCommodityList().size()>20);
        dao.createCommodity(new Commodity(13, "Banana", true));
        Commodity ban = dao.getCommodity(13);
        dao.updateCommodity(new Commodity(13, "Apple", true));
        Commodity get = dao.getCommodity(13);
        assertFalse(dao.getCommodityList().contains(ban));
        assertEquals(get.getName(), "Apple");
        assertEquals(get.getID(), 13);
        assertEquals(get.getIsActive(), true);
    }

    @Test
    public void setToInActive() throws DALException{
        ICommodityDAO dao = new CommodityDAONonPersistent();
        DummyDataGenerator data = new DummyDataGenerator(13);
        data.generateCommodities(dao);
        //System.out.println(dao.getCommodityList().get(13));
        assertTrue(dao.getCommodityList().size()>20);
        assertTrue(dao.getCommodity(28).getIsActive());
        dao.setIsActiveCommodity(28,false);
        assertFalse(dao.getCommodity(28).getIsActive());
    }

    @Test
    public void setToActive() throws DALException{
        ICommodityDAO dao = new CommodityDAONonPersistent();
        DummyDataGenerator data = new DummyDataGenerator(13);
        data.generateCommodities(dao);
        assertTrue(dao.getCommodityList().size()>20);
        assertTrue(dao.getCommodity(28).getIsActive());
        dao.setIsActiveCommodity(28,false);
        assertFalse(dao.getCommodity(28).getIsActive());
        dao.setIsActiveCommodity(28,true);
        assertTrue(dao.getCommodity(28).getIsActive());
    }

    @Test
    public void getCommodityBatchTest() throws DALException{
        DummyDataGenerator dummy = new DummyDataGenerator(13);
        ICommodityDAO dao = new CommodityDAONonPersistent();
        dummy.generateCommodityBatches(dao);
        // Does exist in dao after generation:
        //CommodityBatchDTO{ | commodityBatchNr=13 | , amount=2615.7841831099936 | , provider='ITKT' | isActive=true}
        //for(int i = 0; i<dao.getCommodityBatchList().size(); ++i){
        //    System.out.println(dao.getCommodityBatchList().get(i));
        //}
        CommodityBatch gotten = dao.getCommodityBatch(13);
        CommodityBatch expected = new CommodityBatch(13, 40, -1234, "ITKT", true);
        assertEquals(gotten.getID(), expected.getID());
        assertEquals(gotten.getIsActive(), expected.getIsActive());
        // Not testing double as it is quite imprecise
        assertEquals(gotten.getCommodityNr(), expected.getCommodityNr());
        assertEquals(gotten.getProvider(), expected.getProvider());
    }



    @Test
    public void createCommodityBatchTest() throws DALException, JunkFormatException{
        DummyDataGenerator dummy = new DummyDataGenerator(13);
        ICommodityDAO dao = new CommodityDAONonPersistent();
        dummy.generateCommodityBatches(dao);
        CommodityBatch expect = new CommodityBatch(46, 40, 1234, "Whomst", true);
        dao.createCommodityBatch(expect);
        CommodityBatch gotten = dao.getCommodityBatch(46);
        assertEquals(gotten.getID(), expect.getID());
        assertEquals(gotten.getIsActive(), expect.getIsActive());
        // Not testing double as it is quite imprecise
        assertEquals(gotten.getCommodityNr(), expect.getCommodityNr());
        assertEquals(gotten.getProvider(), expect.getProvider());


    }
    @Test
    public void setIsActiveBatchTest() throws DALException{
        DummyDataGenerator dummy = new DummyDataGenerator(13);
        ICommodityDAO dao = new CommodityDAONonPersistent();
        dummy.generateCommodityBatches(dao);
        // Does exist in dao after generation:
        //CommodityBatchDTO{ | commodityBatchNr=13 | , amount=2615.7841831099936 | , provider='ITKT' | isActive=true}
        assertTrue(dao.getCommodityBatch(13).getIsActive());
        dao.setIsActiveBatch(13,false);
        assertFalse(dao.getCommodityBatch(13).getIsActive());

            dao.setIsActiveBatch(13,true);
        try{
            dao.setIsActiveBatch(13,true);
            assertTrue(false);
        }catch(DALException e){
            assertTrue(true);
        }

        dao.setIsActiveBatch(13,false);
        try{
            dao.setIsActiveBatch(13,false);
            assertTrue(false);
        }catch(DALException e){
            assertTrue(true);
        }

    }
}
