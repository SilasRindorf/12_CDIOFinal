package DAL;

import DTO.ReceiptDTO;

import java.util.ArrayList;
import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 09-06-2020
 * This class is responsible for:
 *  -
 */
public class ReceiptDAO implements IReceiptDAO {
    private List<ReceiptDTO> receipts = new ArrayList<>();
    @Override
    public ReceiptDTO getReceipt(int receiptID) throws DALException {
        if (receipts.get(receiptID) == null)
            throw new DALException("No receipt found");
        else
            return receipts.get(receiptID);
    }

    @Override
    public List<ReceiptDTO> getReceiptList() throws DALException {
        if (receipts.isEmpty())
            throw new DALException("No receipts available");
        else
            return receipts;
    }

    @Override
    public void createReceipt(ReceiptDTO receipt) throws DALException {
        if (receipts.get(receipt.getID())!= null)
            throw new DALException("Receipt ID Taken");
        else
            receipts.add(receipt.getID(),receipt);
    }

    @Override
    public void updateReceipt(ReceiptDTO receipt) throws DALException {
        if (receipts.get(receipt.getID()) == null)
            throw new DALException("No receipt found");
        else
            receipts.set(receipt.getID(),receipt);
    }
}
