package test;

import DAL.interfaces.DALException;
import DAL.interfaces.IReceiptDAO;
import DAL.interfaces.JunkFormatException;
import DAL.nonPersistent.DummyDataGenerator;
import DAL.nonPersistent.ReceiptDAONonP;
import DAL.persistent.ReceiptDAO;
import DTO.ReceiptCompDTO;
import DTO.ReceiptDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptDAONonPTest {
    IReceiptDAO receiptdao;
    DummyDataGenerator DDG;
    int seed = 4;
    @BeforeEach
    void setUp() {
        receiptdao = new ReceiptDAONonP();
        DDG = new DummyDataGenerator(seed);

    }

    @Test
    void getReceipt() throws DALException, JunkFormatException {
        DDG.generateReceipts(receiptdao);
        String compare = "ReceiptDTO{ | name='NDQV' | receiptComps=[commodityNr=-1157023572 | amount=0.9075698691414893 | tolerance=0.810723556855427, commodityNr=935400211 | amount=0.6795571637816596 | tolerance=0.07838777450255496, commodityNr=106573384 | amount=0.8526162758358073 | tolerance=0.7992049575587654, commodityNr=1150324104 | amount=0.15123210213417404 | tolerance=0.9245810422363743] | isActive=true | id= 0}";

        if(seed ==0){
            assertEquals(compare, receiptdao.getReceipt(0).toString());
        }

    }

    @Test
    void getReceiptList() {
    }

    @Test
    void createReceipt() {
        List<ReceiptCompDTO> compList = new ArrayList<>();


        receiptdao.createReceipt();
    }

    @Test
    void setIsActive() {
    }
}