package DAL;

import DTO.ProductBatchCompDTO;

import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 09-06-2020
 * This interface is responsible for:
 *  -
 */
public interface IProductBatchCompDAO {
    ProductBatchCompDTO getProductBatchComp(int pbID, int cbID) throws DALException;
    List<ProductBatchCompDTO> getProductBatchCompList(int pbID) throws DALException;
    List<ProductBatchCompDTO> getProductBatchCompList() throws DALException;
    void createProductBatchComp(ProductBatchCompDTO productBatchComponent) throws DALException;
    void updateProductBatchComp(ProductBatchCompDTO productBatchComponent) throws DALException;

}
