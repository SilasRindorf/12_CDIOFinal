package DAL.interfaces;

import DTO.ReceiptDTO;
import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 09-06-2020
 * This interface is responsible for:
 *  -
 */
public interface IReceiptDAO {
    ReceiptDTO getReceipt(int receiptID) throws DALException;
    List<ReceiptDTO> getReceiptList() throws DALException;
    void createReceipt(ReceiptDTO receipt) throws DALException, JunkFormatException;
    void setIsActive(int receiptID, boolean isActive) throws DALException, JunkFormatException;
}
