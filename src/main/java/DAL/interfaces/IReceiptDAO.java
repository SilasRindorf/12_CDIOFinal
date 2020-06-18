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

    /**
     * Responsibility: Creating an interface for getting a receipt.
     * @param receiptID: Is the ID of a requested receipt.
     * @return Receipt.
     * @throws DALException:
     * Error might happen if the ID doesn't exist.
     * Could print "Receipt with " + ID + "do not exist" .
     * Do not change anything, and returns a copy of the data.
     */
    Receipt getReceipt(int receiptID) throws DALException;
    /**
     * Responsibility: Creating an interface for getting a list of all receipts.
     * @return A list of Receipts.
     * @throws DALException:
     * Error might happen if the data do not exist or empty.
     * Do not change anything, and returns a copy of the data.
     */

    List<Receipt> getReceiptList() throws DALException;
    /**
     * Responsibility: Creating an interface for creating a receipt.
     * @param receipt: Is the Receipt that need to be created in the data layer.
     * @return void.
     * @throws DALException:
     * Error might happen if the ID already exist in the datalayer.
     * Could print "Use an id that do not exist" .
     * Adds data to the database.
     */

    void createReceipt(Receipt receipt) throws DALException, JunkFormatException;

    /**
     * Responsibility: Changing the status of a receipt.
     * @param receiptID: Is the ID of a rhe receipt that need to get changed status
     * @param isActive: Boolean u want to change the status to..
     * @return void.
     * @throws DALException:
     * Error might happen if the ID doesn't exist.
     * Could print "Receipt with " + ID + "do not exist" .
     * @throws JunkFormatException:
     * Error might happen if isActiv is the same as the receipts status.
     * Delets a user and creat a new one with the changed status and the old data.
     */
    void setIsActive(int receiptID, boolean isActive) throws DALException, JunkFormatException;
}
