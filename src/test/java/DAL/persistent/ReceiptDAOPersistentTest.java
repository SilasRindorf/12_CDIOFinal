package DAL.persistent;

import DAL.interfaces.DALException;
import DAL.nonPersistent.DummyDataGenerator;
import DTO.ReceiptDTO;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptDAOPersistentTest {

    ReceiptDAO receiptDAO;
    DummyDataGenerator DDG = new DummyDataGenerator(4);
    {
        try {
            receiptDAO = new ReceiptDAO("TEST_RECEIPT_DAO_FILE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Test
    void getReceipt() {

    }

    @Test
    void getReceiptList() throws DALException {
        List<ReceiptDTO> RL = DDG.generateReceiptsAndGet(receiptDAO);
        List<ReceiptDTO> TL = receiptDAO.getReceiptList();
        for(int i = 0; i< RL.size(); i++){
            assertEquals(RL.get(i).toString(), TL.get(i).toString());
        }

    }

    @Test
    void createReceipt() {
    }

    @Test
    void setIsActive() {
    }
}