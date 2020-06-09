package DAL;

import DTO.ReceiptCompDTO;

import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 09-06-2020
 * This interface is responsible for:
 *  -
 */
public interface IReceiptCompDAO {
    ReceiptCompDTO getReceiptComp(int receiptID, int commodityID) throws DALException;
    List<ReceiptCompDTO> getReceiptCompList(int receiptID) throws DALException;
    List<ReceiptCompDTO> getReceiptCompList() throws DALException;
    void createReceiptComp(ReceiptCompDTO receiptComponent) throws DALException;
    void updateReceiptComp(ReceiptCompDTO receiptComponent) throws DALException;
}
