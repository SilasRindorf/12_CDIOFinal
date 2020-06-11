package DAL.interfaces;

import DTO.ProductBatchDTO;

import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 09-06-2020
 * This interface is responsible for:
 *  -
 */
public interface IProductDAO {
    ProductBatchDTO getBatch(int pbId) throws DALException;
    List<ProductBatchDTO> getBatchList() throws DALException;
    void createBatch(ProductBatchDTO productBatch) throws DALException;
    void updateBatch(ProductBatchDTO productBatch) throws DALException;
    void setIsActiveBatch(int pbId, boolean isActive);
}
