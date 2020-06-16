package DAL.persistent;

import DAL.interfaces.DALException;
import DAL.interfaces.JunkFormatException;
import DAL.nonPersistent.DummyDataGenerator;
import DTO.ReceiptCompDTO;
import DTO.ReceiptDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptDAOPersistentTest {

    ReceiptDAO receiptDAO;
    DummyDataGenerator DDG;
    List<ReceiptDTO> RL;
    {
        try {
            receiptDAO = new ReceiptDAO(FileAPI.TEST_RECEIPT_DAO_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Test
    void getReceipt() throws DALException {
        File file = new File(FileAPI.TEST_RECEIPT_DAO_FILE);
        file.delete();

        DDG = new DummyDataGenerator(4);
        RL = DDG.generateReceiptsAndGet(receiptDAO);
        for(int i = 0; i<RL.size(); i++) {
            assertEquals(RL.get(i).toString(), receiptDAO.getReceipt(i).toString());

        }

    }

    @Test
    void getReceiptList() throws DALException {
        File file = new File(FileAPI.TEST_RECEIPT_DAO_FILE);
        file.delete();

        DDG = new DummyDataGenerator(4);
        RL = DDG.generateReceiptsAndGet(receiptDAO);
        List<ReceiptDTO> TL = receiptDAO.getReceiptList();
        for(int i = 0; i< RL.size(); i++){
            assertEquals(RL.get(i).toString(), TL.get(i).toString());

        }

    }

    @Test
    void createReceipt() throws JunkFormatException, DALException {
        File file = new File(FileAPI.TEST_RECEIPT_DAO_FILE);
        file.delete();

        List<ReceiptCompDTO> compList = new ArrayList<>();
        for(int x = 0; x < 4; x++){
            compList.add(new ReceiptCompDTO(0, 0.1, 0.2));
        }
        ReceiptDTO randRecipt = new ReceiptDTO(9000,"SKKKK",compList, true);

        receiptDAO.createReceipt(randRecipt);
        assertEquals(randRecipt.toString(), receiptDAO.getReceipt(9000).toString());
    }

    @Test
    void setIsActive() throws DALException, JunkFormatException {
        File file = new File(FileAPI.TEST_RECEIPT_DAO_FILE);
        file.delete();
        DDG = new DummyDataGenerator(4);
        RL = DDG.generateReceiptsAndGet(receiptDAO);
        receiptDAO.setIsActive(0, false);
        assertEquals(false,receiptDAO.getReceipt(0).getIsActive());
        receiptDAO.setIsActive(0, true);
        assertEquals(true,receiptDAO.getReceipt(0).getIsActive());

    }
}