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
        return receipts.get(receiptID);
    }

    @Override
    public List<ReceiptDTO> getReceiptList() throws DALException {
        return receipts;
    }

    @Override
    public void createReceipt(ReceiptDTO receipt) throws DALException {
        receipts.add(receipt);
    }

    @Override
    public void updateReceipt(ReceiptDTO receipt) throws DALException {
        receipts.get(receipt.)
    }
}
