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
    ProductBatch getBatch(int pbId) throws DALException;
    List<ProductBatch> getBatchList() throws DALException;
    void createBatch(ProductBatch productBatch) throws DALException, JunkFormatException;
    void updateBatch(ProductBatch productBatch) throws DALException, JunkFormatException;
    void setIsActiveBatch(int pbId, boolean isActive) throws DALException;
}
