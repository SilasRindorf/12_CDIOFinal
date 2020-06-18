package DAL.nonPersistent;

import DAL.interfaces.DALException;
import DAL.interfaces.IProductDAO;
import DAL.interfaces.JunkFormatException;
import DTO.ProductBatchCompDTO;
import DTO.ProductBatchDTO;
import DTO.UserDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
/***
 * Initial version created by: Andreas
 * Edited by:
 * Created: 15-06-2020
 * This class is responsible for:
 *  Storing information about productbatches in a non persistent manner
 *  Assuring wrong or illegal information is not stored
 */
public class ProductDAONonPersistent implements IProductDAO {

    private List<ProductBatchDTO> productBatches;

    public ProductDAONonPersistent() {
        productBatches = new ArrayList<>();
    }

    @Override
    public ProductBatchDTO getBatch(int pbId) throws DALException {
        for (ProductBatchDTO prod : productBatches)
        {
            if (prod.getID() == pbId)
            {
                return prod;
            }
        }
        throw new DALException("There is no productbatch where ID = " + pbId);
    }

    @Override
    public List<ProductBatchDTO> getBatchList() throws DALException {
        return new ArrayList<>(productBatches);
    }

    @Override
    public void createBatch(ProductBatchDTO productBatch) throws DALException, JunkFormatException {
        if(productBatch.getID() < 0){
            throw new JunkFormatException("Ids should not be negative, the id was: "+ productBatch.getID(), Arrays.asList(JunkFormatException.ErrorList.NEGATIVE_ID));
        }
        for (ProductBatchDTO prod : productBatches)
        {
            if (prod.getID() == productBatch.getID())
            {
                throw new DALException("There is already a productbatch where ID = " + prod.getID());
            }
        }
        productBatches.add(productBatch);
    }

    @Override
    public void updateBatch(ProductBatchDTO productBatch) throws DALException, JunkFormatException
    {
        for (ProductBatchDTO prod : productBatches)
        {
            if (prod.getID() == productBatch.getID())
            {
                productBatches.remove(prod);
                productBatches.add(productBatch);
                return;
            }
        }
        throw new DALException("The productbatch you tried to update didn't exist.");
    }

    @Override
    public void setIsActiveBatch(int pbId, boolean isActive) throws DALException {
        ProductBatchDTO prod = getBatch(pbId);
        if (prod.getIsActive() == isActive){
            throw new DALException("The productbatch activity is already "+isActive);
        }
        ProductBatchDTO newBatch = new ProductBatchDTO(pbId, prod.getReceipt(), prod.getCreated(), prod.getStatus(), prod.getProductComps(), isActive);
        try {
            updateBatch(newBatch);
        } catch (JunkFormatException e) {
            throw new AssertionError("Changing isActive should not result in " +
                    "JunkFormatException, since it should only modify one and only one variable (isActive). " +
                    "This means that the database state was corrupt.");
        }

    }

}
