package DAL.interfaces;

import RAM.CommodityBatch;
import RAM.Commodity;

import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 09-06-2020
 * This interface is responsible for:
 *  -
 */
public interface ICommodityDAO {
    Commodity getCommodity(int commodityID) throws DALException;
    List<Commodity> getCommodityList() throws DALException;
    void createCommodity(Commodity commodity) throws DALException, JunkFormatException;
    void updateCommodity(Commodity commodity) throws DALException, JunkFormatException;
    void setIsActiveCommodity(int cId, boolean isActive) throws DALException;

    CommodityBatch getBatch(int commodityBatchID) throws DALException;
    List<CommodityBatch> getBatchList() throws DALException;
    List<CommodityBatch> getBatchList(int commodityID) throws DALException;
    void createBatch(CommodityBatch commodityBatch) throws DALException, JunkFormatException;
    void setIsActiveBatch(int cbId, boolean isActive) throws DALException;
}
