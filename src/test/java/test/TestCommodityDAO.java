package test;

import DAL.interfaces.DALException;
import DAL.interfaces.ICommodityDAO;
import DAL.interfaces.JunkFormatException;
import DAL.nonPersistent.CommodityDAONonPersistent;
import DAL.nonPersistent.DummyDataGenerator;
import DTO.CommodityBatchDTO;
import DTO.CommodityDTO;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestCommodityDAO {


    @Test
    public void getCommodityTest() throws JunkFormatException, DALException {
        ICommodityDAO dao = new CommodityDAONonPersistent();
        DummyDataGenerator data = new DummyDataGenerator(13);
        data.generateCommodities(dao);
        assertTrue(dao.getCommodityList().size()>20);
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
        assertTrue(dao.getCommodityList().size()>20);
    }

    @Test
    public void createCommodityTest() throws JunkFormatException, DALException {
        ICommodityDAO dao = new CommodityDAONonPersistent();
        DummyDataGenerator data = new DummyDataGenerator(13);
        data.generateCommodities(dao);
        assertTrue(dao.getCommodityList().size()>20);
        dao.createCommodity(new CommodityDTO(13, "Banana", true));
        CommodityDTO ban = dao.getCommodity(13);
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

    public void getCommodityBatchTest() throws DALException{

    }

    //List<CommodityBatchDTO> getCommodityBatchList() throws DALException;
    //List<CommodityBatchDTO> getCommodityBatchList(int commodityID) throws DALException;
    //void createCommodityBatch(CommodityBatchDTO commodityBatch) throws DALException, JunkFormatException;
    //void setIsActiveBatch(int cbId, boolean isActive) throws DALException;
}
