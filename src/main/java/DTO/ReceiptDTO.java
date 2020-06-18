package DTO;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 16-06-2020
 * This class is responsible for:
 *
 */
public class ReceiptDTO {
    private int receiptNr;
    private String name;
    private ReceiptCompDTO[] receiptComps;
    private boolean isActive;
    public ReceiptDTO(){

    }
    public int getReceiptNr() {
        return receiptNr;
    }

    public void setReceiptNr(int receiptNr) {
        this.receiptNr = receiptNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReceiptCompDTO[] getReceiptComps() {
        return receiptComps;
    }

    public void setReceiptComps(ReceiptCompDTO[] receiptComps) {
        this.receiptComps = receiptComps;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


}
