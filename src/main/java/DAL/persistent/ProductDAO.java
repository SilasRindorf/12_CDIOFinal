package DAL.persistent;

import DAL.interfaces.DALException;
import DAL.interfaces.JunkFormatException;
import DAL.nonPersistent.ProductDAONonPersistent;
import DTO.ProductBatchDTO;
import DTO.UserDTO;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/***
 * Initial version created by: Andreas
 * Edited by:
 * Created: 16-06-2020
 * This class is responsible for:
 * Persistent storage of Product-objects.
 */


public class ProductDAO extends ProductDAONonPersistent implements Serializable {
    private final String FILE;

    public ProductDAO(String filepath) throws IOException, ClassNotFoundException {
        super();
        FILE = filepath;
        File file = new File(FILE);
        boolean isNew = file.createNewFile();
        if (!isNew) {
            try {
                productBatches = (ArrayList) FileAPI.loadDataFromFile(FILE);
            } catch (EOFException ignored) { //Means that no objects are in the file
            }
        }
    }


    public void createBatch(ProductBatchDTO productBatch) throws DALException, JunkFormatException {
        super.createBatch(productBatch);
        try {
            FileAPI.saveDataToFile(getBatchList(), FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateBatch(ProductBatchDTO productBatch) throws DALException, JunkFormatException {
        super.updateBatch(productBatch);
        try {
            FileAPI.saveDataToFile(getBatchList(), FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setIsActive(int pbId, boolean isActive) throws DALException {
        super.setIsActive(pbId, isActive);
        try {
            FileAPI.saveDataToFile(getBatchList(), FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
