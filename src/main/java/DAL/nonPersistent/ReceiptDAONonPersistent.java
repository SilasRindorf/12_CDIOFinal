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
import DTO.ReceiptCompDTO;
import DTO.ReceiptDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class ReceiptDAONonPersistent implements IReceiptDAO {
    List<String> reciptList = new ArrayList<String>();
    @Override
    public ReceiptDTO getReceipt(int receiptID) throws DALException {

        for(String line : reciptList){
            List<ReceiptCompDTO> compList  = new ArrayList<ReceiptCompDTO>();
            int id = Integer.parseInt(line.split(" ")[0]);
            if(id == receiptID){
                String[] lineA = line.split(" ");
                for(int i = 2; i < lineA.length- 2; i++){
                    ReceiptCompDTO comp = new ReceiptCompDTO(Integer.parseInt(lineA[i]), Double.parseDouble(lineA[i+1]), Double.parseDouble(lineA[i +2]));
                    compList.add(comp);
                    i += 2;
                }
                boolean status = false;
                if(lineA[lineA.length -1].equals("1"))
                    status = true;

                return new ReceiptDTO(Integer.parseInt(lineA[0]), lineA[1], compList, status);
            }
        }

        throw new DALException("ID ikke i systemet");
    }

    @Override
    public List<ReceiptDTO> getReceiptList() throws DALException {


        List<ReceiptDTO> reciptListReturn = new ArrayList<>(); //Kunne bare retunere reciptList, men da den skal skiftes ud med en txt fil, gøres det på denne måde.
        for(String line : reciptList){
            List<ReceiptCompDTO> compList  = new ArrayList<ReceiptCompDTO>();
            String[] lineA = line.split(" ");
            for(int i = 2; i < lineA.length - 2; i++){
                ReceiptCompDTO comp = new ReceiptCompDTO(Integer.parseInt(lineA[i]), Double.parseDouble(lineA[i+1]), Double.parseDouble(lineA[i +2]));
                compList.add(comp);
                    i += 2;
                }
                boolean status = false;
                if(lineA[lineA.length -1].equals("1"))
                    status = true;

                reciptListReturn.add(new ReceiptDTO(Integer.parseInt(lineA[0]), lineA[1], compList, status));
        }

        return reciptListReturn;
    }

    @Override
    public void createReceipt(ReceiptDTO receipt) throws DALException {

        for(String line : reciptList){
            int id = Integer.parseInt(line.split(" ")[0]);
            if(receipt.getID() == id) {
                throw new DALException("Vælg et ikke brugt id");
            }
        }
        String line = receipt.getID() + " "+ receipt.getName() + " ";
        for(ReceiptCompDTO x : receipt.getReceiptComps()){
            line = line + x.getCommodity() + " ";
            line = line + x.getAmount() + " ";
            line = line + x.getTolerance() + " ";
        }
        int status = 0;
        if(receipt.getIsActive()){
            status = 1;
        }

        line = line + status;
        reciptList.add(line);
    }

    @Override
    public void setIsActive(int receiptID, boolean isActive) {

        String status = "0";
        if(isActive)
            status = "1";
        for (int i = 0; i < reciptList.size(); i++){
            String[] lineA = reciptList.get(i).split(" ");
            if(Integer.parseInt(lineA[0]) == receiptID){
                lineA[lineA.length -1 ] = status;
                String line = "";
                for(String s : lineA)
                    line = line + s + " ";

                reciptList.add(i, line);
                return;
            }
        }
    }
}
