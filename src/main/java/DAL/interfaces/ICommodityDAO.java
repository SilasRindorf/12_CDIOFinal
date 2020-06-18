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
    /**
     * Create an interface for getting a commodity that gets implemented by another class.
     * @param commodityID
     * @return
     * @throws DALException
     */
    CommodityDTO getCommodity(int commodityID) throws DALException;

    /**
     * Create an interface for getting a commodity list that gets implemented by another class.
     * @return
     * @throws DALException
     */
    List<CommodityDTO> getCommodityList() throws DALException;

    /**
     * Create an interface for creating a commodity that gets implemented by another class.
     * @param commodity
     * @throws DALException
     * @throws JunkFormatException
     */
    void createCommodity(CommodityDTO commodity) throws DALException, JunkFormatException;

    /**
     * Create an interface for updating a commodity that gets implemented by another class.
     * @param commodity
     * @throws DALException
     * @throws JunkFormatException
     */
    void updateCommodity(CommodityDTO commodity) throws DALException, JunkFormatException;

    /**
     * Create an interface for setting a commodity to be active or inactive that gets implemented by another class.
     * @param cId
     * @param isActive
     * @throws DALException
     */
    void setIsActiveCommodity(int cId, boolean isActive) throws DALException;

    /**
     * Create an interface for getting a commodity batch. This is done with help from the CommodityBatchDTO class and will be implemented further by another class.
     * @param commodityBatchID
     * @return
     * @throws DALException
     */
    CommodityBatchDTO getBatch(int commodityBatchID) throws DALException;

    /**
     * Create an interface for getting a commodity batch list. This is done with help from the CommodityBatchDTO class and will be implemented further by another class.
     * @return
     * @throws DALException
     */
    List<CommodityBatchDTO> getBatchList() throws DALException;
    List<CommodityBatchDTO> getBatchList(int commodityID) throws DALException;

    /**
     * Create an interface for creating a commodity batch that gets implemented later by another class.
     * @param commodityBatch
     * @throws DALException
     * @throws JunkFormatException
     */
    void createBatch(CommodityBatchDTO commodityBatch) throws DALException, JunkFormatException;

    /**
     * Create an interface for setting a batch to be active or inactive that gets implemented later on by another class.
     * @param cbId
     * @param isActive
     * @throws DALException
     */
    void setIsActiveBatch(int cbId, boolean isActive) throws DALException;
}
