package DAL.interfaces;

import DTO.CommodityBatchDTO;
import DTO.CommodityDTO;

import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 09-06-2020
 * This interface is responsible for:
 *  -
 */
public interface ICommodityDAO {
    CommodityDTO getCommodity(int commodityID) throws DALException;
    List<CommodityDTO> getCommodityList() throws DALException;
    void createCommodity(CommodityDTO commodity) throws DALException, JunkFormatException;
    void updateCommodity(CommodityDTO commodity) throws DALException, JunkFormatException;
    void setIsActiveCommodity(int cId, boolean isActive) throws DALException;

    CommodityBatchDTO getBatch(int commodityBatchID) throws DALException;
    List<CommodityBatchDTO> getBatchList() throws DALException;
    List<CommodityBatchDTO> getBatchList(int commodityID) throws DALException;
    void createBatch(CommodityBatchDTO commodityBatch) throws DALException, JunkFormatException;
    void setIsActiveBatch(int cbId, boolean isActive) throws DALException;
}
