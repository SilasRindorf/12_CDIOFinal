package DTO;

/***
 * Main responsible: Silas
 * Edited by: Christoffer, Morten
 */

public class ReceiptCompDTO {
    private int receiptNr;
    private int commodityNr;
    private double amount;
    private double tolerance;
    private boolean isActive;

    public ReceiptCompDTO(int receiptNr, int commodityNr, double amount, double tolerance, boolean isActive) {
        this.receiptNr = receiptNr;
        this.commodityNr = commodityNr;
        this.amount = amount;
        this.tolerance = tolerance;
        this.isActive = isActive;
    }

    public ReceiptCompDTO (){

    }

    public int getReceiptNr() {
        return receiptNr;
    }

    public void setReceiptNr(int receiptNr) {
        this.receiptNr = receiptNr;
    }

    public int getCommodityNr() {
        return commodityNr;
    }

    public void setCommodityNr(int commodityNr) {
        this.commodityNr = commodityNr;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTolerance() {
        return tolerance;
    }

    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


}
