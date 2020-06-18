package DAL.nonPersistent;

import DAL.interfaces.DALException;
import DAL.interfaces.IProductDAO;
import DAL.interfaces.IReceiptDAO;
import DAL.interfaces.JunkFormatException;
import DTO.*;

import java.util.ArrayList;
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

    protected List<ProductBatchDTO> productBatches;
    protected IReceiptDAO receiptDAO;

    public ProductDAONonPersistent(IReceiptDAO receiptDAO) {
        productBatches = new ArrayList<>();
        this.receiptDAO = receiptDAO;
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
        return productBatches;
    }

    @Override
    public void createBatch(ProductBatchDTO productBatch) throws DALException, JunkFormatException {
        for (ProductBatchDTO prod : productBatches)
        {
            if (prod.getID() == productBatch.getID())
            {
                throw new DALException("There is already a productbatch where ID = " + prod.getID());
            }
        }
        if(!isReceiptInDatabase(productBatch.getReceipt())){
            throw new DALException("Receipt id: " + productBatch.getReceipt() + " is not in database");
        }
        productBatches.add(productBatch);
    }

    private boolean containsCom(List<ReceiptCompDTO> lr, List<ProductBatchCompDTO> lp, int i){
            int comR = lr.get(i).getCommodity();
            for(int k = 0; k<lr.size(); ++k){
                int comP = lr.get(k).getCommodity();
                if(comP == comR){
                    return true;
                }
            }
            return false;
    }
    private boolean isReceiptInDatabase(int id){
        try {
            for(ReceiptDTO r : receiptDAO.getReceiptList()){
                if(r.getID() == id){
                    return true;
                }
            }
        } catch (DALException e) {
            e.printStackTrace();
            throw new AssertionError("This method should only be used in a fully initialized ProductDAO. Problems with accessing receiptDAO");
        }
        return false;

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
    public void setIsActive(int pbId, boolean isActive) throws DALException {
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
