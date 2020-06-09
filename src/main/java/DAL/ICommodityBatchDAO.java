package DAL;

import DTO.CommodityBatchDTO;

import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 09-06-2020
 * This interface is responsible for:
 *  -
 */
public interface ICommodityBatchDAO {
    CommodityBatchDTO getCommodityBatch(int commodityBatchID) throws DALException;
    List<CommodityBatchDTO> getCommodityBatchList() throws DALException;
    List<CommodityBatchDTO> getCommodityBatchList(int commodityID) throws DALException;
    void createCommodityBatch(CommodityBatchDTO commodityBatch) throws DALException;
    void updateCommodityBatch(CommodityBatchDTO commodityBatch) throws DALException;

}
