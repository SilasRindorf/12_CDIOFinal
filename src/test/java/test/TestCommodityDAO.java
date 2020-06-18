package test;

import DAL.interfaces.*;
import DAL.nonPersistent.*;
import DAL.persistent.*;
import RAM.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class TestCommodityDAO {


    @Test
    public void getCommodityTest() throws JunkFormatException, DALException {
        ICommodityDAO dao = new CommodityDAONonPersistent();
        DummyDataGenerator data = new DummyDataGenerator(13);
        data.generateCommodities(dao);
        assertTrue(dao.getCommodityList().size() > 20);
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
        assertTrue(dao.getCommodityList().size() > 20);
    }

    @Test
    public void createCommodityTest() throws JunkFormatException, DALException {
        ICommodityDAO dao = new CommodityDAONonPersistent();
        DummyDataGenerator data = new DummyDataGenerator(13);
        data.generateCommodities(dao);
        assertTrue(dao.getCommodityList().size() > 20);
        dao.createCommodity(new Commodity(13, "Banana", true));
        Commodity ban = dao.getCommodity(13);
        assertEquals(ban.getName(), "Banana");
        assertEquals(ban.getID(), 13);
        assertEquals(ban.getIsActive(), true);
    }

    @Test
    public void updateCommodityTest() throws DALException, JunkFormatException {
        ICommodityDAO dao = new CommodityDAONonPersistent();
        DummyDataGenerator data = new DummyDataGenerator(13);
        data.generateCommodities(dao);
        assertTrue(dao.getCommodityList().size() > 20);
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
    public void setToInActive() throws DALException {
        ICommodityDAO dao = new CommodityDAONonPersistent();
        DummyDataGenerator data = new DummyDataGenerator(13);
        data.generateCommodities(dao);
        //System.out.println(dao.getCommodityList().get(13));
        assertTrue(dao.getCommodityList().size() > 20);
        assertTrue(dao.getCommodity(28).getIsActive());
        dao.setIsActiveCommodity(28, false);
        assertFalse(dao.getCommodity(28).getIsActive());
    }

    @Test
    public void setToActive() throws DALException {
        ICommodityDAO dao = new CommodityDAONonPersistent();
        DummyDataGenerator data = new DummyDataGenerator(13);
        data.generateCommodities(dao);
        assertTrue(dao.getCommodityList().size() > 20);
        assertTrue(dao.getCommodity(28).getIsActive());
        dao.setIsActiveCommodity(28, false);
        assertFalse(dao.getCommodity(28).getIsActive());
        dao.setIsActiveCommodity(28, true);
        assertTrue(dao.getCommodity(28).getIsActive());
    }

    @Test
    public void getCommodityBatchTest() throws DALException {
        DummyDataGenerator dummy = new DummyDataGenerator(13);
        ICommodityDAO dao = new CommodityDAONonPersistent();
        dummy.generateCommodityBatches(dao);
        //Does exist after generation
        //CommodityBatch{ | commodityBatchNr=72 | commodityNr=81 | , amount=1255.9104322634005 | , provider='YWCW' | isActive=true}
        CommodityBatch gotten = dao.getBatch(72);
        CommodityBatch expected = new CommodityBatch(72, 81, -1234, "YWCW", true);
        assertEquals(gotten.getID(), expected.getID());
        assertEquals(gotten.getIsActive(), expected.getIsActive());
        // Not testing double as it is quite imprecise
        assertEquals(gotten.getCommodityNr(), expected.getCommodityNr());
        assertEquals(gotten.getProvider(), expected.getProvider());
    }


    @Test
    public void createCommodityBatchTest() throws DALException, JunkFormatException {
        DummyDataGenerator dummy = new DummyDataGenerator(13);
        ICommodityDAO dao = new CommodityDAONonPersistent();
        dummy.generateCommodityBatches(dao);
        CommodityBatch expect = new CommodityBatch(72, 81, -1234, "YWCW", true);
        try {
            dao.createBatch(expect);
            assertTrue(false); //No commodity with commoditynr 40
        }catch(Exception e){
            assertTrue(true);
        }

        expect = new CommodityBatch(46, 81, 1234, "Whomst", true);
        dao.createBatch(expect);
        CommodityBatch gotten = dao.getBatch(46);
        assertEquals(gotten.getID(), expect.getID());
        assertEquals(gotten.getIsActive(), expect.getIsActive());
        // Not testing double as it is quite imprecise
        assertEquals(gotten.getCommodityNr(), expect.getCommodityNr());
        assertEquals(gotten.getProvider(), expect.getProvider());


    }

    @Test
    public void setIsActiveBatchTest() throws DALException {
        DummyDataGenerator dummy = new DummyDataGenerator(13);
        ICommodityDAO dao = new CommodityDAONonPersistent();
        dummy.generateCommodityBatches(dao);
        // Does exist in dao after generation:
        //CommodityBatch expect = new CommodityBatch(72, 81, -1234, "YWCW", true);
        assertTrue(dao.getBatch(72).getIsActive());
        dao.setIsActiveBatch(72, false);
        assertFalse(dao.getBatch(72).getIsActive());

        dao.setIsActiveBatch(72, true);
        try {
            dao.setIsActiveBatch(72, true);
            assertTrue(false);
        } catch (DALException e) {
            assertTrue(true);
        }

        dao.setIsActiveBatch(72, false);
        try {
            dao.setIsActiveBatch(72, false);
            assertTrue(false);
        } catch (DALException e) {
            assertTrue(true);
        }

    }

    @Test
    public void commodityPersistency() throws IOException, ClassNotFoundException, DALException, JunkFormatException {
        // Test gen commodities
        File file = new File(FileAPI.TEST_COMMODITY_DAO_FILE);
        file.delete();
        ICommodityDAO dao = new CommodityDAO(FileAPI.TEST_COMMODITY_DAO_FILE);
        assertEquals(dao.getBatchList().size(), 0);
        assertEquals(dao.getCommodityList().size(), 0);
        DummyDataGenerator gen = new DummyDataGenerator(13);
        gen.generateCommodities(dao);
        //Known to exist: Commodity{ | commodityNr=81 | , name=YTVI | , isActive=true}
        List<Commodity> list1 = dao.getCommodityList();
        ICommodityDAO dao2 = new CommodityDAO(FileAPI.TEST_COMMODITY_DAO_FILE);
        List<Commodity> list2 = dao2.getCommodityList();
        for (int i = 0; i < list1.size(); ++i) {
            assertEquals(list1.get(i).getName(), list2.get(i).getName());
            assertEquals(list1.get(i).getIsActive(), list2.get(i).getIsActive());
        }



    }

    @Test
    public void updateCommodity() throws IOException, ClassNotFoundException, DALException, JunkFormatException {
        // Test update
        File file = new File(FileAPI.TEST_COMMODITY_DAO_FILE);
        file.delete();
        ICommodityDAO dao = new CommodityDAO(FileAPI.TEST_COMMODITY_DAO_FILE);
        assertEquals(dao.getBatchList().size(), 0);
        assertEquals(dao.getCommodityList().size(), 0);
        DummyDataGenerator gen = new DummyDataGenerator(13);
        gen.generateCommodities(dao);
        //Known to exist: Commodity{ | commodityNr=81 | , name=YTVI | , isActive=true}
        Commodity newCom = new Commodity(81, "Banana", true);
        dao.updateCommodity(newCom);
        ICommodityDAO dao2 = new CommodityDAO(FileAPI.TEST_COMMODITY_DAO_FILE);
        Commodity gotten = dao2.getCommodity(81);
        assertEquals(gotten.getIsActive(), newCom.getIsActive());
        assertEquals(gotten.getName(), newCom.getName());
        assertNotEquals(gotten.getName(), "YTVI");
        assertEquals(gotten.getID(), 81);
    }

    @Test
    public void testIsActiveCommodity() throws DALException, IOException, ClassNotFoundException {

        //Test set active
        File file = new File(FileAPI.TEST_COMMODITY_DAO_FILE);
        file.delete();
        ICommodityDAO dao = new CommodityDAO(FileAPI.TEST_COMMODITY_DAO_FILE);
        assertEquals(dao.getBatchList().size(), 0);
        assertEquals(dao.getCommodityList().size(), 0);
        DummyDataGenerator gen = new DummyDataGenerator(13);
        gen.generateCommodities(dao);
        //Known to exist: Commodity{ | commodityNr=81 | , name=YTVI | , isActive=true}
        assertTrue(dao.getCommodity(81).getIsActive());
        ICommodityDAO dao2 = new CommodityDAO(FileAPI.TEST_COMMODITY_DAO_FILE);
        assertTrue(dao2.getCommodity(81).getIsActive());

        dao.setIsActiveCommodity(81, false);
        assertFalse(dao.getCommodity(81).getIsActive());
        dao2 = new CommodityDAO(FileAPI.TEST_COMMODITY_DAO_FILE);
        assertFalse(dao2.getCommodity(81).getIsActive());

        dao2.setIsActiveCommodity(81, true);
        assertTrue(dao2.getCommodity(81).getIsActive());
        dao = new CommodityDAO(FileAPI.TEST_COMMODITY_DAO_FILE);
        assertTrue(dao.getCommodity(81).getIsActive());

        try {
            dao.setIsActiveCommodity(81, true);
            assertFalse(true);
        } catch (Exception e) {
            dao2 = new CommodityDAO(FileAPI.TEST_COMMODITY_DAO_FILE);
            assertTrue(dao2.getCommodity(81).getIsActive());
        }
        dao.setIsActiveCommodity(81, false);
        try {
            dao.setIsActiveCommodity(81, false);
            assertFalse(true);
        } catch (Exception e) {
            dao2 = new CommodityDAO(FileAPI.TEST_COMMODITY_DAO_FILE);
            assertFalse(dao2.getCommodity(81).getIsActive());
        }
    }

    @Test
    public void batchPersistency() throws IOException, ClassNotFoundException, DALException {
        File file = new File(FileAPI.TEST_COMMODITY_DAO_FILE);
        file.delete();
        ICommodityDAO dao = new CommodityDAO(FileAPI.TEST_COMMODITY_DAO_FILE);
        assertEquals(dao.getBatchList().size(), 0);
        assertEquals(dao.getCommodityList().size(), 0);
        DummyDataGenerator gen = new DummyDataGenerator(13);
        gen.generateCommodityBatches(dao);
        //    for(int i = 0; i<dao.getBatchList().size(); ++i){
        //        System.out.println(dao.getBatchList().get(i));
        //    }
        //    for(int i = 0; i<dao.getCommodityList().size(); ++i){
        //        System.out.println(dao.getCommodityList().get(i));
        //    }



        // Snippet from existing things in the data
        //CommodityBatch{ | commodityBatchNr=72 | commodityNr=81 | , amount=1255.9104322634005 | , provider='YWCW' | isActive=true}
        //CommodityBatch{ | commodityBatchNr=8 | commodityNr=19 | , amount=2512.109014746832 | , provider='JZWQ' | isActive=true}
        //CommodityBatch{ | commodityBatchNr=7 | commodityNr=79 | , amount=1395.8415433633104 | , provider='ZSOT' | isActive=true}
        //CommodityBatch{ | commodityBatchNr=50 | commodityNr=28 | , amount=2014.5280914550144 | , provider='TETI' | isActive=true}

        //Commodity{ | commodityNr=80 | , name=QZIA | , isActive=true}
        //Commodity{ | commodityNr=43 | , name=PLMX | , isActive=true}
        //Commodity{ | commodityNr=9 | , name=WOOQ | , isActive=true}
        //Commodity{ | commodityNr=19 | , name=JETX | , isActive=true}

        ICommodityDAO dao2 = new CommodityDAO(FileAPI.TEST_COMMODITY_DAO_FILE);

        List<Commodity> list1 = dao.getCommodityList();
        List<Commodity> list2 = dao2.getCommodityList();
        List<CommodityBatch> list3 = dao.getBatchList();
        List<CommodityBatch> list4 = dao.getBatchList();

        for (int i = 0; i < list1.size(); ++i) {
            assertEquals(list1.get(i).getName(), list2.get(i).getName());
            assertEquals(list1.get(i).getIsActive(), list2.get(i).getIsActive());
        }

        for(int i = 0; i<list3.size(); ++i){
            assertEquals(list3.get(i).getIsActive(),list4.get(i).getIsActive());
            assertEquals(list3.get(i).getProvider(),list4.get(i).getProvider());
            assertEquals(list3.get(i).getID(),list4.get(i).getID());
            assertEquals(list3.get(i).getCommodityNr(),list4.get(i).getCommodityNr());
        }

    }

    @Test
    public void persistentSet() throws IOException, ClassNotFoundException, DALException {
        File file = new File(FileAPI.TEST_COMMODITY_DAO_FILE);
        file.delete();
        ICommodityDAO dao = new CommodityDAO(FileAPI.TEST_COMMODITY_DAO_FILE);
        assertEquals(dao.getBatchList().size(), 0);
        assertEquals(dao.getCommodityList().size(), 0);
        DummyDataGenerator gen = new DummyDataGenerator(13);
        gen.generateCommodityBatches(dao);
        //    for(int i = 0; i<dao.getBatchList().size(); ++i){
        //        System.out.println(dao.getBatchList().get(i));
        //    }
        //    for(int i = 0; i<dao.getCommodityList().size(); ++i){
        //        System.out.println(dao.getCommodityList().get(i));
        //    }



        // Snippet from existing things in the data
        //CommodityBatch{ | commodityBatchNr=72 | commodityNr=81 | , amount=1255.9104322634005 | , provider='YWCW' | isActive=true}
        //CommodityBatch{ | commodityBatchNr=8 | commodityNr=19 | , amount=2512.109014746832 | , provider='JZWQ' | isActive=true}
        //CommodityBatch{ | commodityBatchNr=7 | commodityNr=79 | , amount=1395.8415433633104 | , provider='ZSOT' | isActive=true}
        //CommodityBatch{ | commodityBatchNr=50 | commodityNr=28 | , amount=2014.5280914550144 | , provider='TETI' | isActive=true}

        //Commodity{ | commodityNr=80 | , name=QZIA | , isActive=true}
        //Commodity{ | commodityNr=43 | , name=PLMX | , isActive=true}
        //Commodity{ | commodityNr=9 | , name=WOOQ | , isActive=true}
        //Commodity{ | commodityNr=19 | , name=JETX | , isActive=true}

        dao.setIsActiveBatch(7, false);
        ICommodityDAO dao2 = new CommodityDAO(FileAPI.TEST_COMMODITY_DAO_FILE);
        assertFalse(dao2.getBatch(7).getIsActive());
        dao.setIsActiveBatch(7, true);
        dao2 = new CommodityDAO(FileAPI.TEST_COMMODITY_DAO_FILE);
        assertTrue(dao2.getBatch(7).getIsActive());

    }

}
