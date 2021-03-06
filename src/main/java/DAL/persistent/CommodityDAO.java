package DAL.persistent;

import DAL.interfaces.DALException;
import DAL.interfaces.JunkFormatException;
import DAL.nonPersistent.CommodityDAONonPersistent;
import RAM.Commodity;
import RAM.CommodityBatch;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;

/***
 * Main responsible: Christoffer
 * Edited by: Silas, Alexander, Sejr, Andreas
 */

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

    public void createCommodity(Commodity commodity) throws DALException, JunkFormatException {
        super.createCommodity(commodity);
        try {
            FileAPI.saveDataToFile(new CaBPair(getBatchList(), getCommodityList()), FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateCommodity(Commodity commodity) throws DALException, JunkFormatException {
        super.updateCommodity(commodity);
        try {
            FileAPI.saveDataToFile(new CaBPair(getBatchList(), getCommodityList()), FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setIsActiveCommodity(int cId, boolean isActive) throws DALException {
        super.setIsActiveCommodity(cId, isActive);
        try {
            FileAPI.saveDataToFile(new CaBPair(getBatchList(), getCommodityList()), FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createBatch(CommodityBatch commodityBatch) throws DALException, JunkFormatException {
        super.createBatch(commodityBatch);
        try {
            FileAPI.saveDataToFile(new CaBPair(getBatchList(), getCommodityList()), FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setIsActiveBatch(int commodityBatchID, boolean isActive) throws DALException {
        super.setIsActiveBatch(commodityBatchID, isActive);
        try {
            FileAPI.saveDataToFile(new CaBPair(getBatchList(), getCommodityList()), FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
