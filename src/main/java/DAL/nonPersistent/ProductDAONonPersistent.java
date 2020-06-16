package DAL.nonPersistent;

import DAL.interfaces.DALException;
import DAL.interfaces.IProductDAO;
import DAL.interfaces.JunkFormatException;
import RAM.ProductBatch;

import java.util.ArrayList;
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

    private List<ProductBatch> productBatches;

    public ProductDAONonPersistent() {
        productBatches = new ArrayList<>();
    }

    @Override
    public ProductBatch getBatch(int pbId) throws DALException {
        for (ProductBatch prod : productBatches)
        {
            if (prod.getID() == pbId)
            {
                return prod;
            }
        }
        throw new DALException("There is no productbatch where ID = " + pbId);
    }

    @Override
    public List<ProductBatch> getBatchList() throws DALException {
        return productBatches;
    }

    @Override
    public void createBatch(ProductBatch productBatch) throws DALException, JunkFormatException {
        for (ProductBatch prod : productBatches)
        {
            if (prod.getID() == productBatch.getID())
            {
                throw new DALException("There is already a productbatch where ID = " + prod.getID());
            }
        }
        productBatches.add(productBatch);
    }

    @Override
    public void updateBatch(ProductBatch productBatch) throws DALException, JunkFormatException
    {
        for (ProductBatch prod : productBatches)
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
        ProductBatch prod = getBatch(pbId);
        if (prod.getIsActive() == isActive){
            throw new DALException("The productbatch activity is already "+isActive);
        }
        ProductBatch newBatch = new ProductBatch(pbId, prod.getReceipt(), prod.getCreated(), prod.getStatus(), prod.getProductComps(), isActive);
        try {
            updateBatch(newBatch);
        } catch (JunkFormatException e) {
            throw new AssertionError("Changing isActive should not result in " +
                    "JunkFormatException, since it should only modify one and only one variable (isActive). " +
                    "This means that the database state was corrupt.");
        }

    }

}
