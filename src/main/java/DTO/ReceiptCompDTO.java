package DTO;

import java.io.Serializable;

/***
 * Initial version created by: Silas
 * Edited by: Christoffer
 * Created: 09-06-2020
 * This class is responsible for:
 *  -
 */
public class ReceiptCompDTO implements Serializable {
    static final long serialVersionUID = 40L;
    private int commodityNr;
    private double amount;
    private double tolerance;

    public ReceiptCompDTO(int commodity, double amount, double tolerance) {
        this.commodityNr = commodity;
        this.amount = amount;
        this.tolerance = tolerance;
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
    public String toString(){
        return "commodityNr=" + commodityNr + " | " +
                "amount=" + amount + " | " +
                "tolerance=" + tolerance;
    }
}
