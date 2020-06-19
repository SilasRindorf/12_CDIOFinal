package DAL.nonPersistent;

import DAL.interfaces.DALException;
import DAL.interfaces.IProductDAO;
import DAL.interfaces.IReceiptDAO;
import DAL.interfaces.JunkFormatException;
import RAM.ProductBatch;
import RAM.ProductBatchComp;
import RAM.Receipt;
import RAM.ReceiptComp;

import java.util.ArrayList;
import java.util.Arrays;
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

    protected List<ProductBatch> productBatches;
    protected IReceiptDAO receiptDAO;

    public ProductDAONonPersistent(IReceiptDAO receiptDAO) {
        productBatches = new ArrayList<>();
        this.receiptDAO = receiptDAO;
    }

    @Override
    public ProductBatch getBatch(int pbId) throws DALException {
        for (ProductBatch prod : productBatches) {
            if (prod.getID() == pbId) {
                return prod;
            }
        }
        throw new DALException("There is no productbatch where ID = " + pbId);
    }

    @Override
    public List<ProductBatch> getBatchList() throws DALException {
        return new ArrayList<>(productBatches);
    }

    @Override
    public void createBatch(ProductBatch productBatch) throws DALException, JunkFormatException {
        if (productBatch.getID() < 0) {
            throw new JunkFormatException("Ids should not be negative, the id was: " + productBatch.getID(), Arrays.asList(JunkFormatException.ErrorList.ID));
        }
        for (ProductBatch prod : productBatches) {
            if (prod.getID() == productBatch.getID()) {
                throw new DALException("There is already a productbatch where ID = " + prod.getID());
            }
        }
        if (!isReceiptInDatabase(productBatch.getReceipt())) {
            throw new DALException("Receipt id: " + productBatch.getReceipt() + " is not in database");
        }
        productBatches.add(productBatch);
    }

    private boolean containsCom(List<ReceiptComp> lr, List<ProductBatchComp> lp, int i) {
        int comR = lr.get(i).getCommodity();
        for (int k = 0; k < lr.size(); ++k) {
            int comP = lr.get(k).getCommodity();
            if (comP == comR) {
                return true;
            }
        }
        return false;
    }

    private boolean isReceiptInDatabase(int id) {
        try {
            for (Receipt r : receiptDAO.getReceiptList()) {
                if (r.getID() == id) {
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
    public void updateBatch(ProductBatch productBatch) throws DALException, JunkFormatException {
        for (ProductBatch prod : productBatches) {
            if (prod.getID() == productBatch.getID()) {
                productBatches.remove(prod);
                productBatches.add(productBatch);
                return;
            }
        }
        throw new DALException("The productbatch you tried to update didn't exist.");
    }

    @Override
    public void setIsActive(int pbId, boolean isActive) throws DALException {
        ProductBatch prod = getBatch(pbId);
        if (prod.getIsActive() == isActive) {
            throw new DALException("The productbatch activity is already " + isActive);
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
