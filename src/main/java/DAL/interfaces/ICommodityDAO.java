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

    CommodityBatch getCommodityBatch(int commodityBatchID) throws DALException;
    List<CommodityBatch> getCommodityBatchList() throws DALException;
    List<CommodityBatch> getCommodityBatchList(int commodityID) throws DALException;
    void createCommodityBatch(CommodityBatch commodityBatch) throws DALException, JunkFormatException;
    void setIsActiveBatch(int cbId, boolean isActive) throws DALException;
}
