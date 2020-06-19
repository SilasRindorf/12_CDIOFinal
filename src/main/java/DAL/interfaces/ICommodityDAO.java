package DAL.interfaces;

import RAM.Commodity;
import RAM.CommodityBatch;

import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 09-06-2020
 * This interface is responsible for:
 *  -
 */
public interface ICommodityDAO {

    // @formatter:off
    /**
     * Responsibility: Creating an interface for getting a commodity.
     *
     * @param commodityID: Is the ID of a requested commodity.
     * @return Commodity.
     * @throws DALException: Error if the ID doesn't exist.
     *                       Could print "Commodity ID not found. DALException thrown.".
     */
    // @formatter:on
    Commodity getCommodity(int commodityID) throws DALException;


    /**
     * Responsibility: Creating an interface for getting a commodity list.
     *
     * @return List<Commodity>.
     * @throws DALException: Error if the list doesn't exist (unable to access database).
     *                       Could print "Commodity List not found. DALException thrown.".
     */
    List<Commodity> getCommodityList() throws DALException;

    // @formatter:off
    /**
     * Responsibility: Creating an interface for creating a commodity.
     *
     * @param commodity: Is the requested commodity.
     * @throws DALException:        Error if the commodity already exist.
     *                              Could print "Commodity already exist. DALException thrown.".
     * @throws JunkFormatException: Error if the commodity information isn't within the given boundaries for a given field.
     *                              Could print "Please fill out the fields correctly. JunkFormatException thrown.".
     */
    // @formatter:on
    void createCommodity(Commodity commodity) throws DALException, JunkFormatException;

    // @formatter:off
    /**
     * Responsibility: Creating an interface for updating a commodity.
     *
     * @param commodity: The requested commodity to be updated."
     * @throws DALException:        Error if the requested commodity doesn't exist.
     *                              Could print "Commodity doesn't exist. DALException thrown.".
     *                              Or similar errors as to createCommodity
     * @throws JunkFormatException: Error if the commodity information isn't within the the given boundaries for a given field.
     *                              Could print "Please fill out the fields correctly. JunkFormatException thrown.".
     */
    // @formatter:on
    void updateCommodity(Commodity commodity) throws DALException, JunkFormatException;

    // @formatter:off
    /**
     * Responsibility: Creating an interface for setting a commodity to be active or inactive.
     *
     * @param commodityID: Is the ID of a requested commodity.
     * @param isActive:    Is a boolean that describes whether the requested commodity should be set to active or inactive.
     * @throws DALException: Error if requested commodity doesn't exist.
     *                       Could print "Commodity doesn't exist. DALException thrown.".
     *                       Error if commodity is already in "isActive" state, so if it is already true, then it will throw
     *                       an error when trying to set it to true
     */
    // @formatter:on
    void setIsActiveCommodity(int commodityID, boolean isActive) throws DALException;

    // @formatter:off
    /**
     * Responsibility: Creating an interface for getting a commodity batch.
     *
     * @param commodityBatchID: Is the ID of a requested commodity batch.
     * @return CommodityBatch.
     * @throws DALException: Error if requested commodity batch doesn't exist.
     *                       Could print "Commodity batch doesn't exist. DALException thrown.".
     */
    // @formatter:on
    CommodityBatch getBatch(int commodityBatchID) throws DALException;

    // @formatter:off
    /**
     * Responsibility: Creating an interface for getting a commodity batch list.
     *
     * @return List<CommodityBatch>.
     * @throws DALException: Error if requested commodity batch list doesn't exist (database access error).
     *                       Could print "Commodity batch list doesn't exist. DALException thrown.".
     */
    // @formatter:on
    List<CommodityBatch> getBatchList() throws DALException;

    List<CommodityBatch> getBatchList(int commodityID) throws DALException;



    // @formatter:off
    /**
     * Responsibility: Creating an interface for creating a commodity batch.
     *
     * @param commodityBatch: Is the commodity batch name. Which is identified with its supplied id
     * @throws DALException:       Error if requested commodity batch name already exist.
     *                             Or if the commodityBatch is referring to a commidity currently not in database
     *                             Could print "Commodity batch already exist. DALException thrown.".
     * @throws JunkFormatException Error if the commodity batch information isn't within the the given boundaries for a given field.
     *                             Could print "Please fill out the fields correctly. JunkFormatException thrown.".
     */
    // @formatter:on
    void createBatch(CommodityBatch commodityBatch) throws DALException, JunkFormatException;

    // @formatter:off
    /**
     * Responsibility: Creating an interface for setting a commodity batch to be active or inactive.
     *
     * @param commodityBatchID: Is the ID of a requested commodity batch.
     * @param isActive:         Is a boolean that describes whether a commodity batch should be set active or inactive.
     * @throws DALException: Error if requested commodity batch ID doesn't exist.
     *                       Could print "Commodity batch ID doesn't exist. DALException thrown.".
     */
    // @formatter:on
    void setIsActiveBatch(int commodityBatchID, boolean isActive) throws DALException;
}
