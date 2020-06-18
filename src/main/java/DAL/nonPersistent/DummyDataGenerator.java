package DAL.nonPersistent;

import DAL.interfaces.*;
import DAL.interfaces.DALException;
import DAL.interfaces.ICommodityDAO;
import DAL.interfaces.IUserDAO;
import DAL.interfaces.JunkFormatException;
import RAM.*;

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
            User randUser = new User(rand.nextInt(100),
                    String.valueOf(randChar())+randChar()+randChar()+randChar(),
                    String.valueOf(randChar())+randChar(),
                    "0707070707",
                    User.newPassword(),
                    User.Role.values()[rand.nextInt(4)],
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

    public void generateCommodities(ICommodityDAO dao){
        for(int i = 0;i<100; ++i){
            try {
                dao.createCommodity(new Commodity(rand.nextInt(100),""+randChar()+randChar()+randChar()+randChar(), true));
            } catch (DALException e) {
                continue;
            } catch (JunkFormatException e) {
                continue;
            }
        }
    }

    public CommodityBatch randomCB(){
            return new CommodityBatch(
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
                dao.createBatch(randomCB());
            } catch (DALException e) {
                continue;
            } catch (JunkFormatException e) {
                continue;
            }
        }
    }
    //TODO: Seems broken, ids get too big?
    //TODO: ID is changed from random to i.
    public void generateReceipts(IReceiptDAO receiptdao){
        for(int i = 0; i < 100; i++){
            List<ReceiptComp> compList = new ArrayList<>();
            for(int x = 0; x < 4; x++){
                compList.add(new ReceiptComp(rand.nextInt(100), rand.nextDouble()*100, rand.nextDouble()*100 ));
                //System.out.println(compList.get(x));
            }
            Receipt randRecipt = new Receipt(i,""+randChar()+randChar()+randChar()+ randChar(),compList, true);
            try {
                receiptdao.createReceipt(randRecipt);
            } catch (DALException | JunkFormatException e) {
                continue;
            }
        }
    }
}

