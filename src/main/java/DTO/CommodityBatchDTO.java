package DTO;

import java.io.Serializable;

/***
 * Initial version created by: Silas
 * Edited by:
 * Created: 16-06-2020
 * This class is responsible for:
 *  -
 */
public class CommodityBatchDTO  {
    private int commodityBatchNr;
    private int commodityNr;
    private double amount;
    private String provider;
    private boolean isActive;

    public CommodityBatchDTO(int commodityBatchNr, int commodityNr, double amount, String provider, boolean isActive) {
        this.commodityBatchNr = commodityBatchNr;
        this.commodityNr = commodityNr;
        this.amount = amount;
        this.provider = provider;
        this.isActive = isActive;
    }

    public CommodityBatchDTO() {

    }

    public int getCommodityBatchNr() {
        return commodityBatchNr;
    }

    public void setCommodityBatchNr(int commodityBatchNr) {
        this.commodityBatchNr = commodityBatchNr;
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

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "CommodityBatchDTO{" + " | " +
                "commodityBatchNr=" + commodityBatchNr + " | " +
                "commodityNr=" + commodityNr + " | " +
                ", amount=" + amount + " | " +
                ", provider='" + provider + '\'' + " | " +
                "isActive=" + isActive + '}';
    }
}
