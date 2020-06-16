package DAL.persistent;

import DAL.nonPersistent.DummyDataGenerator;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptDAOPersistentTest {
    ReceiptDAO receiptDAO;

    {
        try {
            receiptDAO = new ReceiptDAO("TEST_RECEIPT_DAO_FILE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    DummyDataGenerator DDG = new DummyDataGenerator(4);


    @Test
    void getReceipt() {

    }

    @Test
    void getReceiptList() {
        receiptDAO.getReceiptList();
    }

    @Test
    void createReceipt() {
    }

    @Test
    void setIsActive() {
    }
}