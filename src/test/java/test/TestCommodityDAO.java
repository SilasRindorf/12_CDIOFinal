package test;

import DAL.interfaces.DALException;
import DAL.interfaces.ICommodityDAO;
import DAL.interfaces.JunkFormatException;
import DAL.nonPersistent.CommodityDAONonPersistent;
import DAL.nonPersistent.DummyDataGenerator;
import DAL.persistent.CommodityDAO;
import DAL.persistent.FileAPI;
import DTO.CommodityBatchDTO;
import DTO.CommodityDTO;
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
        dao.createCommodity(new CommodityDTO(13, "Banana", true));
        CommodityDTO ban = dao.getCommodity(13);
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
        dao.createCommodity(new CommodityDTO(13, "Banana", true));
        CommodityDTO ban = dao.getCommodity(13);
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
        dao.createCommodity(new CommodityDTO(13, "Banana", true));
        CommodityDTO ban = dao.getCommodity(13);
        dao.updateCommodity(new CommodityDTO(13, "Apple", true));
        CommodityDTO get = dao.getCommodity(13);
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
        // Does exist in dao after generation:
        //CommodityBatchDTO{ | commodityBatchNr=13 | , amount=2615.7841831099936 | , provider='ITKT' | isActive=true}
        //for(int i = 0; i<dao.getCommodityBatchList().size(); ++i){
        //    System.out.println(dao.getCommodityBatchList().get(i));
        //}
        CommodityBatchDTO gotten = dao.getBatch(13);
        CommodityBatchDTO expected = new CommodityBatchDTO(13, 40, -1234, "ITKT", true);
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
        CommodityBatchDTO expect = new CommodityBatchDTO(46, 40, 1234, "Whomst", true);
        try {
            dao.createBatch(expect);
            assertTrue(false); //No commodity with commoditynr 40
        }catch(Exception e){
            assertTrue(true);
        }

        expect = new CommodityBatchDTO(46, 81, 1234, "Whomst", true);
        dao.createBatch(expect);
        CommodityBatchDTO gotten = dao.getBatch(46);
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
        //CommodityBatchDTO{ | commodityBatchNr=13 | , amount=2615.7841831099936 | , provider='ITKT' | isActive=true}
        assertTrue(dao.getBatch(13).getIsActive());
        dao.setIsActiveBatch(13, false);
        assertFalse(dao.getBatch(13).getIsActive());

        dao.setIsActiveBatch(13, true);
        try {
            dao.setIsActiveBatch(13, true);
            assertTrue(false);
        } catch (DALException e) {
            assertTrue(true);
        }

        dao.setIsActiveBatch(13, false);
        try {
            dao.setIsActiveBatch(13, false);
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
        List<CommodityDTO> list1 = dao.getCommodityList();
        ICommodityDAO dao2 = new CommodityDAO(FileAPI.TEST_COMMODITY_DAO_FILE);
        List<CommodityDTO> list2 = dao2.getCommodityList();
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
        CommodityDTO newCom = new CommodityDTO(81, "Banana", true);
        dao.updateCommodity(newCom);
        ICommodityDAO dao2 = new CommodityDAO(FileAPI.TEST_COMMODITY_DAO_FILE);
        CommodityDTO gotten = dao2.getCommodity(81);
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
       ICommodityDAO dao2 = new CommodityDAO(FileAPI.TEST_COMMODITY_DAO_FILE);



        // Snippet from existing things in the data
        //CommodityBatchDTO{ | commodityBatchNr=72 | commodityNr=81 | , amount=1255.9104322634005 | , provider='YWCW' | isActive=true}
        //CommodityBatchDTO{ | commodityBatchNr=8 | commodityNr=19 | , amount=2512.109014746832 | , provider='JZWQ' | isActive=true}
        //CommodityBatchDTO{ | commodityBatchNr=7 | commodityNr=79 | , amount=1395.8415433633104 | , provider='ZSOT' | isActive=true}
        //CommodityBatchDTO{ | commodityBatchNr=50 | commodityNr=28 | , amount=2014.5280914550144 | , provider='TETI' | isActive=true}

        //Commodity{ | commodityNr=80 | , name=QZIA | , isActive=true}
        //Commodity{ | commodityNr=43 | , name=PLMX | , isActive=true}
        //Commodity{ | commodityNr=9 | , name=WOOQ | , isActive=true}
        //Commodity{ | commodityNr=19 | , name=JETX | , isActive=true}

        List<CommodityDTO> list1 = dao.getCommodityList();
        List<CommodityDTO> list2 = dao2.getCommodityList();
        List<CommodityBatchDTO> list3 = dao.getBatchList();
        List<CommodityBatchDTO> list4 = dao.getBatchList();

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

}
