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
    void createCommodity(CommodityDTO commodity) throws DALException;
    void updateCommodity(CommodityDTO commodity) throws DALException;

    CommodityBatchDTO getCommodityBatch(int commodityBatchID) throws DALException;
    List<CommodityBatchDTO> getCommodityBatchList() throws DALException;
    List<CommodityBatchDTO> getCommodityBatchList(int commodityID) throws DALException;
    void createCommodityBatch(CommodityBatchDTO commodityBatch) throws DALException;
    void updateCommodityBatch(CommodityBatchDTO commodityBatch) throws DALException;
}
