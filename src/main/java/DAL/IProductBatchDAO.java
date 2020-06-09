package DAL;

import DTO.ProductBatchDTO;

import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 09-06-2020
 * This interface is responsible for:
 *  -
 */
public interface IProductBatchDAO {
    ProductBatchDTO getProductBatch(int pbId) throws DALException;
    List<ProductBatchDTO> getProductBatchList() throws DALException;
    void createProductBatch(ProductBatchDTO productBatch) throws DALException;
    void updateProductBatch(ProductBatchDTO productBatch) throws DALException;

}
