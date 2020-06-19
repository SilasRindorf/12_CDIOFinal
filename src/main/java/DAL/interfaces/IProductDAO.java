package DAL.interfaces;

import RAM.ProductBatch;

import java.util.List;

// @formatter:off
/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 09-06-2020
 * This interface is responsible for:
 *  -
 */
// @formatter:on
public interface IProductDAO {

// @formatter:off
    /**
     * Responsibility: Creating an interface for getting a product batch.
     *
     * @param pbId: Is the ID of a requested product batch.
     * @return ProductBatch.
     * @throws DALException: Error if the ID doesn't exist.
     *                       Could print "Product batch ID not found. DALException thrown.".
     */
    // @formatter:on
    ProductBatch getBatch(int pbId) throws DALException;

// @formatter:off
    /**
     * Responsibility: Creating an interface for getting a product batch list.
     *
     * @return List<ProductBatch>.
     * @throws DALException: Error if the ID doesn't exist.
     *                       Could print "Product batch list not found. DALException thrown.".
     */

    // @formatter:on

    List<ProductBatch> getBatchList() throws DALException;

// @formatter:off
    /**
     * Responsibility: Creating an interface for creating a product batch.
     *
     * @param productBatch: Is the name of a requested product batch.
     * @throws DALException:        Error if the name of the product batch already exist.
     *                              Could print "Product batch name already exist. DALException thrown.".
     * @throws JunkFormatException: Error if the product batch information isn't within the given boundaries for a given field.
     *                              Could print "Please fill out the fields correctly. JunkFormatException thrown.".
     */
    // @formatter:on
    void createBatch(ProductBatch productBatch) throws DALException, JunkFormatException;

// @formatter:off
    /**
     * Responsibility: Creating an interface for updating a product batch.
     *
     * @param productBatch: Is the name of a requested product batch.
     * @throws DALException:        Error if the name of the product batch already exist.
     *                              Could print "Product batch name already exist. DALException thrown.".
     * @throws JunkFormatException: Error if the product batch information isn't within the given boundaries for a given field.
     *                              Could print "Please fill out the fields correctly. JunkFormatException thrown.".
     */
    // @formatter:on

    void updateBatch(ProductBatch productBatch) throws DALException, JunkFormatException;

// @formatter:off
    /**
     * Responsibility: Creating an interface for setting a product batch ID to be active or inactive.
     *
     * @param productBatchID: Is the ID of a requested product batch.
     * @param isActive:       Is a boolean describing whether the requested product batch ID is active or inactive.
     * @throws DALException: Error if the product batch ID doesn't exist.
     *                       Could print "Product batch ID not found. DALException thrown.".
     */
    // @formatter:on
    void setIsActive(int productBatchID, boolean isActive) throws DALException;
}
