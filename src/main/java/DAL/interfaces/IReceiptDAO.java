package DAL.interfaces;

import RAM.Receipt;

import java.util.List;

// @formatter:off
/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 09-06-2020
 * This interface is responsible for:
 *  -
 */
// @formatter:on
public interface IReceiptDAO {

    // @formatter:off
    /**
     * Responsibility: Creating an interface for getting a receipt.
     *
     * @param receiptID: Is the ID of a requested receipt.
     * @return Receipt.
     * @throws DALException: Error if the ID doesn't exist.
     *                       Could print "Receipt with " + ID + "do not exist" .
     * Do not change anything, and returns a copy of the data
     */
// @formatter:on
    Receipt getReceipt(int receiptID) throws DALException;
    // @formatter:off
    /**
     * Responsibility: Creating an interface for getting a list of all receipts.
     * @return A list of Receipts.
     * @throws DALException: Error if the data do not exist (database access error).
     * Do not change anything, and returns a copy of the data
     */
// @formatter:on

    List<Receipt> getReceiptList() throws DALException;
    // @formatter:off
    /**
     * Responsibility: Creating an interface for creating a receipt.
     *
     * @param receipt: Is the Receipt that need to be created in the data layer.
     * @return void.
     * @throws DALException: Error if the ID already exist in the datalayer.
     *                       Could print "Use an id that do not exist" .
     * Adds data to the database.
     */
// @formatter:on
    void createReceipt(Receipt receipt) throws DALException, JunkFormatException;

    // @formatter:off
    /**
     * Responsibility: Changing the status of a receipt.
     *
     * @param receiptID: Is the ID of a rhe receipt that need to get changed status
     * @param isActive: Boolean you want to change the status to.
     * @return void.
     * @throws DALException: Error if the ID doesn't exist.
     *                       Could print "Receipt with " + ID + "do not exist" .
     * @throws JunkFormatException: Error if isActive is the same as the receipts status.
     */
// @formatter:on
    void setIsActive(int receiptID, boolean isActive) throws DALException, JunkFormatException;
}
