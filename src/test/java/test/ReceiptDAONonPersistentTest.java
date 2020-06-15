package test;

import DAL.interfaces.DALException;
import DAL.interfaces.IReceiptDAO;
import DAL.interfaces.JunkFormatException;
import DAL.nonPersistent.DummyDataGenerator;
import DAL.nonPersistent.ReceiptDAONonPersistent;
import DTO.ReceiptCompDTO;
import DTO.ReceiptDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptDAONonPersistentTest {

    //Seed 4
    IReceiptDAO receiptdao;
    DummyDataGenerator DDG;
    int seed = 4;
    @BeforeEach
    void setUp() {
        receiptdao = new ReceiptDAONonPersistent();
        DDG = new DummyDataGenerator(seed);

    }

    @Test
    void getReceipt() throws DALException, JunkFormatException {
        DDG.generateReceipts(receiptdao);
        String compare = "ReceiptDTO{ | name='NDQV' | receiptComps=[commodityNr=-1157023572 | amount=0.9075698691414893 | tolerance=0.810723556855427, commodityNr=935400211 | amount=0.6795571637816596 | tolerance=0.07838777450255496, commodityNr=106573384 | amount=0.8526162758358073 | tolerance=0.7992049575587654, commodityNr=1150324104 | amount=0.15123210213417404 | tolerance=0.9245810422363743] | isActive=true | id= 0}";

        if(seed ==0){
            assertEquals(compare, receiptdao.getReceipt(0).toString());
            assertNotEquals(compare,receiptdao.getReceipt(1).toString() );
        }

    }

    @Test
    void getReceiptList() throws JunkFormatException, DALException {
       List<ReceiptDTO> compare = DDG.generateReceiptsAndGet(receiptdao);
        List<ReceiptDTO> actual = receiptdao.getReceiptList();


       for(int i = 0; i < compare.size(); i++){

           assertEquals(compare.get(i).toString(), actual.get(i).toString());
       }

    }

    @Test
    void createReceipt() throws JunkFormatException, DALException {
        List<ReceiptCompDTO> compList = new ArrayList<>();
        for(int x = 0; x < 4; x++){
            compList.add(new ReceiptCompDTO(0, 0.1, 0.2));
        }

        ReceiptDTO compare = new ReceiptDTO(11, "skk", compList, true);
        receiptdao.createReceipt(compare);
        assertEquals(compare.toString(),receiptdao.getReceipt(11).toString());

    }

    @Test
    void setIsActive() throws DALException {
        DDG.generateReceipts(receiptdao);

        for(int i = 0; i < 10; i++) {
            assertEquals(true, receiptdao.getReceipt(i).getIsActive());
            receiptdao.setIsActive(i, false);
            assertEquals(false, receiptdao.getReceipt(i).getIsActive());
        }
    }
}