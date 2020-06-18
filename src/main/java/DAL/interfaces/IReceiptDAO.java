package DAL.interfaces;

import RAM.Receipt;
import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 09-06-2020
 * This interface is responsible for:
 *  -
 */
public interface IReceiptDAO {
    Receipt getReceipt(int receiptID) throws DALException;
    List<Receipt> getReceiptList() throws DALException;
    void createReceipt(Receipt receipt) throws DALException, JunkFormatException;
    void setIsActive(int receiptID, boolean isActive) throws DALException, JunkFormatException;
}
