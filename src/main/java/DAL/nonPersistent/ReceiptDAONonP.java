package DAL.nonPersistent;

import DAL.interfaces.DALException;
import DAL.interfaces.IReceiptDAO;
import DAL.interfaces.JunkFormatException;
import DTO.ReceiptCompDTO;
import DTO.ReceiptDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class ReceiptDAONonP implements IReceiptDAO {
    List<String> reciptList = new ArrayList<String>();
    @Override
    public ReceiptDTO getReceipt(int receiptID) throws DALException {
        List<ReceiptCompDTO> compList  = new ArrayList<ReceiptCompDTO>();

        for(String line : reciptList){
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

        return null;
    }

    @Override
    public List<ReceiptDTO> getReceiptList() throws DALException {

        List<ReceiptCompDTO> compList  = new ArrayList<ReceiptCompDTO>();
        List<ReceiptDTO> reciptListReturn = new ArrayList<>(); //Kunne bare retunere reciptList, men da den skal skiftes ud med en txt fil, gøres det på denne måde.
        for(String line : reciptList){
            String[] lineA = line.split(" ");
            for(int i = 2; i < lineA.length; i++){
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
    public void createReceipt(ReceiptDTO receipt) throws DALException, JunkFormatException {
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
                reciptList.add(i, Arrays.toString(lineA));
                return;
            }
        }
    }
}
