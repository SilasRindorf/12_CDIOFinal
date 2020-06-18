package DAL.interfaces;

import RAM.ProductBatch;

import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 09-06-2020
 * This interface is responsible for:
 *  -
 */
public interface IProductDAO {
    /**
     * Responsibility: Creating an interface for getting a product batch.
     *
     * @param pbId: Is the ID of a requested product batch.
     * @return ProductBatch.
     * @throws DALException: Error might happen if the ID doesn't exist.
     *                       Could print "Product batch ID not found. DALException thrown.".
     */
    ProductBatch getBatch(int pbId) throws DALException;

    /**
     * Responsibility: Creating an interface for getting a product batch list.
     *
     * @return List<ProductBatch>.
     * @throws DALException: Error might happen if the ID doesn't exist.
     *                       Could print "Product batch list not found. DALException thrown.".
     */
    List<ProductBatch> getBatchList() throws DALException;

    /**
     * Responsibility: Creating an interface for creating a product batch.
     *
     * @param productBatch: Is the name of a requested product batch.
     * @throws DALException:        Error might happen if the name of the product batch already exist.
     *                              Could print "Product batch name already exist. DALException thrown.".
     * @throws JunkFormatException: Error might happen if the product batch information isn't within the given boundaries for a given field.
     *                              Could print "Please fill out the fields correctly. JunkFormatException thrown.".
     */
    void createBatch(ProductBatch productBatch) throws DALException, JunkFormatException;

    /**
     * Responsibility: Creating an interface for updating a product batch.
     *
     * @param productBatch: Is the name of a requested product batch.
     * @throws DALException:        Error might happen if the name of the product batch already exist.
     *                              Could print "Product batch name already exist. DALException thrown.".
     * @throws JunkFormatException: Error might happen if the product batch information isn't within the given boundaries for a given field.
     *                              Could print "Please fill out the fields correctly. JunkFormatException thrown.".
     */
    void updateBatch(ProductBatch productBatch) throws DALException, JunkFormatException;

    /**
     * Responsibility: Creating an interface for setting a product batch ID to be active or inactive.
     *
     * @param productBatchID: Is the ID of a requested product batch.
     * @param isActive:       Is a boolean describing whether the requested product batch ID is active or inactive.
     * @throws DALException: Error might happen if the product batch ID doesn't exist.
     *                       Could print "Product batch ID not found. DALException thrown.".
     */
    void setIsActive(int productBatchID, boolean isActive) throws DALException;
}
