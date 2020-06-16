/***
 * Initial version created by: Alexander
 * Edited by:
 * Created: 15-06-2020
 * This class is responsible for:
 *  Storing information about receipts in a non persistent manner
 *  Assuring wrong or illegal information is not stored
 */

package DAL.nonPersistent;

import DAL.interfaces.DALException;
import DAL.interfaces.IReceiptDAO;
import DAL.interfaces.JunkFormatException;
import DAL.persistent.ReceiptDAO;
import DTO.ReceiptCompDTO;
import DTO.ReceiptDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class ReceiptDAONonPersistent implements IReceiptDAO {
    List<ReceiptDTO> reciptList = new ArrayList<ReceiptDTO>();
    @Override
    public ReceiptDTO getReceipt(int receiptID) throws DALException {

        for(ReceiptDTO receipt : reciptList){
            List<ReceiptCompDTO> compList  = new ArrayList<ReceiptCompDTO>();
            if(receipt.getID() == receiptID){

                return receipt;
            }
        }

        throw new DALException("ID ikke i systemet");
    }

    @Override
    public List<ReceiptDTO> getReceiptList() throws DALException {
        if(reciptList.size() != 0){
            return reciptList;
        }
        throw new DALException("Receipt listen er tom");
    }

    @Override
    public void createReceipt(ReceiptDTO receipt) throws DALException {

        for(ReceiptDTO x : reciptList ){
            if(x.getID() == receipt.getID())
                throw new DALException("ID allerede i brug");
        }
        reciptList.add(receipt);
    }

    @Override
    public void setIsActive(int receiptID, boolean isActive) {
        for(int i = 0; i < reciptList.size(); i++){
            if(reciptList.get(i).getID() == receiptID){
                ReceiptDTO x = reciptList.get(i);

                ReceiptDTO insert = new ReceiptDTO(x.getID(), x.getName(),x.getReceiptComps(), isActive);
                reciptList.add(i, insert);
            }

        }
    }
}
