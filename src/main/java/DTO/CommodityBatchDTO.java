package DTO;

import java.io.Serializable;

/***
 * Initial version created by: Silas
 * Edited by:
 * Created: 16-06-2020
 * This class is responsible for:
 *  -
 */
public class CommodityBatchDTO extends IdAndActivatable implements Serializable {
    private static final long serialVersionUID = 1275450134536L;
    private int commodityNr;
    private double amount;
    private String provider;

    public CommodityBatchDTO(int commodityBatchNr, int commodityNr, double amount, String provider, boolean isActive) {
        super(commodityBatchNr,isActive);
        this.commodityNr = commodityNr;
        this.amount = amount;
        this.provider = provider;
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

    @Override
    public String toString() {
        return "CommodityBatchDTO{" + " | " +
                "commodityBatchNr=" + getID() + " | " +
                "commodityNr=" + commodityNr + " | " +
                ", amount=" + amount + " | " +
                ", provider='" + provider + '\'' + " | " +
                "isActive=" + getIsActive() + '}';
    }
}
