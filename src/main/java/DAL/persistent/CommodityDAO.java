package DAL.persistent;

import DAL.interfaces.DALException;
import DAL.interfaces.JunkFormatException;
import DAL.nonPersistent.CommodityDAONonPersistent;
import DTO.CommodityBatchDTO;
import DTO.CommodityDTO;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;

public class CommodityDAO extends CommodityDAONonPersistent {
    private final String FILE;

    public CommodityDAO(String filepath) throws IOException, ClassNotFoundException {
        super();
        FILE = filepath;
        File file = new File(FILE);
        boolean isNew = file.createNewFile();
        if (!isNew) {
            try {
                CaBPair pair = (CaBPair) FileAPI.loadDataFromFile(FILE);
                batches = pair.batches;
                commodities = pair.coms;
            } catch (EOFException ignored) { //Means that no objects are in the file
            }
        }
    }

    public void createCommodity(CommodityDTO commodity) throws DALException, JunkFormatException {
        super.createCommodity(commodity);
        try {
            FileAPI.saveDataToFile(new CaBPair(getBatchList(), getCommodityList()), FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateCommodity(CommodityDTO commodity) throws DALException, JunkFormatException{
        super.updateCommodity(commodity);
        try {
            FileAPI.saveDataToFile(new CaBPair(getBatchList(), getCommodityList()), FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setIsActiveCommodity(int cId, boolean isActive) throws DALException{
        super.setIsActiveCommodity(cId,isActive);
        try {
            FileAPI.saveDataToFile(new CaBPair(getBatchList(), getCommodityList()), FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createBatch(CommodityBatchDTO commodityBatch) throws DALException, JunkFormatException{
        super.createBatch(commodityBatch);
        try {
            FileAPI.saveDataToFile(new CaBPair(getBatchList(), getCommodityList()), FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setIsActiveBatch(int cbId, boolean isActive) throws DALException{
        super.setIsActiveBatch(cbId,isActive);
        try {
            FileAPI.saveDataToFile(new CaBPair(getBatchList(), getCommodityList()), FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
