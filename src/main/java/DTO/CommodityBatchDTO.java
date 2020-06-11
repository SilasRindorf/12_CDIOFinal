package DTO;

/***
 * Initial version created by: Silas
 * Edited by: Christoffer
 * Created: 09-06-2020
 * This class is responsible for:
 *  -
 */
public class CommodityBatchDTO {
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


    public int getCommodityBatchNr() {
        return commodityBatchNr;
    }

    public double getAmount() {
        return amount;
    }

    public String getProvider() {
        return provider;
    }

    public int getCommodityNr() {
        return commodityNr;
    }

    public boolean isActive() {
        return isActive;
    }
    @Override
    public String toString() {
        return "CommodityBatchDTO{" +
                "commodityBatchNr=" + commodityBatchNr +
                ", amount=" + amount +
                ", provider='" + provider + '\'' +
                '}';
    }

}
