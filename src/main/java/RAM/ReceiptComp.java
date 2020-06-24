package RAM;

import DTO.ReceiptCompDTO;

import java.io.Serializable;

/***
 * Main responsible: Christoffer
 * Edited and reviewed by: Sejr, Morten, Andreas, Alexander, Silas
 */

public class ReceiptComp implements Serializable {
    public static final long serialVersionUID = 57536453707645L;
    private int commodityNr;
    private double amount;
    private double tolerance;

    public ReceiptComp(int commodity, double amount, double tolerance) {
        this.commodityNr = commodity;
        this.amount = amount;
        this.tolerance = tolerance;
    }

    public ReceiptComp(ReceiptCompDTO receiptCompDTO){
        this.commodityNr = receiptCompDTO.getCommodityNr();
        this.amount = receiptCompDTO.getAmount();
        this.tolerance = receiptCompDTO.getTolerance();
    }

    public int getCommodity() {
        return commodityNr;
    }

    public double getAmount() {
        return amount;
    }

    public double getTolerance() {
        return tolerance;
    }

    @Override
    public String toString() {
        return "commodityNr=" + commodityNr + " | " +
                "amount=" + amount + " | " +
                "tolerance=" + tolerance;
    }
}
