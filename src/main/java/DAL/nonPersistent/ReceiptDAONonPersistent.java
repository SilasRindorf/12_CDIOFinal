package DAL.nonPersistent;

import DAL.interfaces.DALException;
import DAL.interfaces.ICommodityDAO;
import DAL.interfaces.IReceiptDAO;
import DAL.interfaces.JunkFormatException;
import RAM.Commodity;
import RAM.Receipt;
import RAM.ReceiptComp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * Initial version created by: Andreas
 * Edited by:
 * Created: 16-06-2020
 * This class is responsible for:
 * Non persistent storage of ReceiptDTO objects
 */
public class ReceiptDAONonPersistent implements IReceiptDAO {

    protected List<Receipt> receipts;
    protected ICommodityDAO commodityDAO;

    public ReceiptDAONonPersistent(ICommodityDAO commodityDAO) {
        this.commodityDAO = commodityDAO;
        receipts = new ArrayList<>();
    }

    @Override
    public Receipt getReceipt(int receiptID) throws DALException {
        for (Receipt rec : receipts) {
            if (rec.getID() == receiptID) {
                return rec;
            }
        }
        throw new DALException("There is no receipt with ID = " + receiptID);
    }

    @Override
    public List<Receipt> getReceiptList() throws DALException {
        return new ArrayList<>(receipts);
    }

    @Override
    public void createReceipt(Receipt newReceipt) throws DALException, JunkFormatException {
        if (newReceipt.getID() < 0) {
            throw new JunkFormatException("Ids should not be negative, the id was: " + newReceipt.getID(), Arrays.asList(JunkFormatException.ErrorList.ID));
        }
        for (Receipt rec : receipts) {
            if (rec.getID() == newReceipt.getID()) {
                throw new DALException("There already exists a receipt with ID = " + newReceipt.getID());
            }
        }
        List<Integer> idsNotExisting = commoditiesDoesNotExistForReceipt(newReceipt);
        if (idsNotExisting.size() > 0) {
            throw new DALException("There is no commodityIds in the database which have the Ids: " + Arrays.asList(idsNotExisting));
        }
        receipts.add(newReceipt);
    }

    private List<Integer> commoditiesDoesNotExistForReceipt(Receipt r) {
        List<Integer> res = new ArrayList<>();
        for (ReceiptComp comp : r.getReceiptComps()) {
            if (!commodityExist(comp.getCommodity())) {
                res.add(comp.getCommodity());
            }
        }
        return res;
    }

    private boolean commodityExist(int cID) {
        try {
            for (Commodity c : commodityDAO.getCommodityList()) {
                if (c.getID() == cID) {
                    return true;
                }
            }
        } catch (DALException e) {
            e.printStackTrace();
            throw new AssertionError("Should only be used by an initialized ReceiptDAO.");
        }
        return false;
    }

    @Override
    public void setIsActive(int receiptID, boolean isActive) throws DALException, JunkFormatException {
        Receipt rec = getReceipt(receiptID);
        if (rec.getIsActive() == isActive) {
            throw new DALException("The receipt activity is already " + isActive);
        }
        Receipt newReceipt = new Receipt(receiptID, rec.getName(), rec.getReceiptComps(), isActive);
        try {
            updateReceipt(newReceipt);
        } catch (JunkFormatException e) {
            throw new AssertionError("Changing isActive should not result in " +
                    "JunkFormatException, since it should only modify one and only one variable (isActive). " +
                    "This means that the database state was corrupt.");
        }
    }

    private void updateReceipt(Receipt newReceipt) throws DALException, JunkFormatException {
        for (Receipt rec : receipts) {
            if (rec.getID() == newReceipt.getID()) {
                Receipt backup = rec;
                receipts.remove(rec);
                try {
                    createReceipt(newReceipt);
                } catch (Exception e) {
                    receipts.add(backup);
                    throw e;
                }
                return;
            }
        }
        throw new DALException("The receipt you tried to update didn't exist.");
    }

}
