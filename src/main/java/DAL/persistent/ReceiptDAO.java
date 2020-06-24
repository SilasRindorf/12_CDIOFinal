package DAL.persistent;

import DAL.interfaces.DALException;
import DAL.interfaces.ICommodityDAO;
import DAL.interfaces.IReceiptDAO;
import DAL.interfaces.JunkFormatException;
import DAL.nonPersistent.ReceiptDAONonPersistent;
import RAM.Receipt;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/***
 * Main responsible: Christoffer
 * Edited by: Silas, Alexander, Sejr, Andreas
 */
public class ReceiptDAO extends ReceiptDAONonPersistent implements IReceiptDAO {
    private final String FILE;

    public ReceiptDAO(String filepath, ICommodityDAO commodityDAO) throws IOException, ClassNotFoundException {
        super(commodityDAO);
        FILE = filepath;
        File file = new File(FILE);
        boolean isNew = file.createNewFile();
        if (!isNew) {
            try {
                receipts = (ArrayList) FileAPI.loadDataFromFile(FILE);
            } catch (EOFException ignored) { //Means that no objects are in the file
            }
        }
    }


    @Override
    public Receipt getReceipt(int receiptID) throws DALException {
        return super.getReceipt(receiptID);
    }

    @Override
    public void createReceipt(Receipt receipt) throws DALException, JunkFormatException {
        super.createReceipt(receipt);
        try {
            FileAPI.saveDataToFile(receipts, FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setIsActive(int receiptID, boolean isActive) throws DALException, JunkFormatException {
        super.setIsActive(receiptID, isActive);
        try {
            FileAPI.saveDataToFile(receipts, FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
