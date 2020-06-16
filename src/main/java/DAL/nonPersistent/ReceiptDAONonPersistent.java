package DAL.nonPersistent;

import DAL.interfaces.DALException;
import DAL.interfaces.IReceiptDAO;
import DAL.interfaces.JunkFormatException;
import DTO.ReceiptDTO;

import java.util.ArrayList;
import java.util.List;

/***
 * Initial version created by: Andreas
 * Edited by:
 * Created: 16-06-2020
 * This class is responsible for:
 * Non persistent storage of ReceiptDTO objects
 */
public class ReceiptDAONonPersistent implements IReceiptDAO {

    private List<ReceiptDTO> receipts;

    public ReceiptDAONonPersistent() {
        receipts = new ArrayList<>();
    }

    @Override
    public ReceiptDTO getReceipt(int receiptID) throws DALException
    {
        for (ReceiptDTO rec : receipts)
        {
            if (rec.getID() == receiptID)
            {
                return rec;
            }
        }
        throw new DALException("There is no receipt with ID = "+receiptID);
    }

    @Override
    public List<ReceiptDTO> getReceiptList() throws DALException {
        return receipts;
    }

    @Override
    public void createReceipt(ReceiptDTO newReceipt) throws DALException {
        for (ReceiptDTO rec : receipts)
        {
            if (rec.getID() == newReceipt.getID())
            {
                throw new DALException("There already exists a receipt with ID = " + newReceipt.getID());
            }
        }
        receipts.add(newReceipt);
    }

    @Override
    public void setIsActive(int receiptID, boolean isActive) throws DALException, JunkFormatException {
        ReceiptDTO rec = getReceipt(receiptID);
        if (rec.getIsActive() == isActive){
            throw new DALException("The receipt activity is already "+isActive);
        }
        ReceiptDTO newReceipt = new ReceiptDTO(receiptID, rec.getName(), rec.getReceiptComps(), isActive);
        try {
            updateReceipt(newReceipt);
        } catch (JunkFormatException e) {
            throw new AssertionError("Changing isActive should not result in " +
                    "JunkFormatException, since it should only modify one and only one variable (isActive). " +
                    "This means that the database state was corrupt.");
        }
    }

    private void updateReceipt(ReceiptDTO newReceipt) throws DALException, JunkFormatException{
        for (ReceiptDTO rec : receipts)
        {
            if (rec.getID() == newReceipt.getID())
            {
                ReceiptDTO backup = rec;
                receipts.remove(rec);
                try {
                    createReceipt(newReceipt);
                }catch(Exception e){
                    receipts.add(backup);
                    throw e;
                }
                return;
            }
        }
        throw new DALException("The receipt you tried to update didn't exist.");
    }

}
