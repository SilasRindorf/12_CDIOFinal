package DAL.nonPersistent;

import DAL.interfaces.DALException;
import DAL.interfaces.ICommodityDAO;
import DAL.interfaces.IReceiptDAO;
import DAL.interfaces.IUserDAO;
import DAL.interfaces.JunkFormatException;
import DTO.CommodityBatchDTO;
import DTO.CommodityDTO;
import DTO.ReceiptCompDTO;
import DTO.ReceiptDTO;
import DTO.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DummyDataGenerator {
    Random rand;
    public DummyDataGenerator(int seed){
     rand = new Random(seed);
    }

    public char randChar(){
        return (char) ('A'+rand.nextInt(26));
    }

    public void generateUsers(IUserDAO userdao){
        for(int i = 0; i<100; ++i){
            UserDTO randUser = new UserDTO(rand.nextInt(100),
                    String.valueOf(randChar())+randChar()+randChar()+randChar(),
                    String.valueOf(randChar())+randChar(),
                    "0707070707",
                    UserDTO.newPassword(),
                    UserDTO.Role.values()[rand.nextInt(4)],
                    true );
            try {
                userdao.createUser(randUser);
            } catch (DALException e) {
                continue;
            } catch (JunkFormatException e) {
                continue;
            }
        }
    }

    public void generateReceipts(IReceiptDAO receiptdao){
        for(int i = 0; i < 100; i++){
            List<ReceiptCompDTO> compList = new ArrayList<>();
            for(int x = 0; x < 4; x++){
                compList.add(new ReceiptCompDTO(rand.nextInt(), rand.nextDouble(), rand.nextDouble() ));
            }
            ReceiptDTO randRecipt = new ReceiptDTO(i,""+randChar()+randChar()+randChar()+ randChar(),compList, true);
            try {
                receiptdao.createReceipt(randRecipt);
            } catch (DALException | JunkFormatException e) {
                e.printStackTrace();
            }
        }
    }
    public List<ReceiptDTO> generateReceiptsAndGet(IReceiptDAO receiptdao){
        List<ReceiptDTO> listR = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            List<ReceiptCompDTO> compList = new ArrayList<>();
            for(int x = 0; x < 4; x++){
                compList.add(new ReceiptCompDTO(rand.nextInt(), rand.nextDouble(), rand.nextDouble() ));
            }
            ReceiptDTO randRecipt = new ReceiptDTO(i,""+randChar()+randChar()+randChar()+ randChar(),compList, true);
            listR.add(randRecipt);
            try {
                receiptdao.createReceipt(randRecipt);
            } catch (DALException | JunkFormatException e) {
                e.printStackTrace();
            }
        }
        return listR;
    }

    public void generateCommodities(ICommodityDAO dao){
        for(int i = 0;i<100; ++i){
            try {
                dao.createCommodity(new CommodityDTO(rand.nextInt(100),""+randChar()+randChar()+randChar()+randChar(), true));
            } catch (DALException e) {
                continue;
            } catch (JunkFormatException e) {
                continue;
            }
        }
    }

    public CommodityBatchDTO randomCB(){
            return new CommodityBatchDTO(
                    rand.nextInt(100),
                    rand.nextInt(100),
                    rand.nextDouble()*3600,
                    ""+randChar()+randChar()+randChar()+randChar(),
                    true);
    }

    public void generateCommodityBatches(ICommodityDAO dao){
        generateCommodities(dao);
        for(int i = 0; i<100; ++i){
            try {
                dao.createCommodityBatch(randomCB());
            } catch (DALException e) {
                continue;
            } catch (JunkFormatException e) {
                continue;
            }
        }
    }
}

