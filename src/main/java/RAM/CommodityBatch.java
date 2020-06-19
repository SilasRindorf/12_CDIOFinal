package RAM;

import java.io.Serializable;

/***
 * Initial version created by: Silas
 * Edited by: Christoffer
 * Created: 09-06-2020
 * This class is responsible for:
 *  -
 */
public class CommodityBatch extends IdAndActivatable implements Serializable {
    public static final long serialVersionUID = 254574532753L;
    private int commodityNr;
    private double amount;
    private String provider;

    public CommodityBatch(int commodityBatchNr, int commodityNr, double amount, String provider, boolean isActive) {
        super(commodityBatchNr, isActive);
        this.commodityNr = commodityNr;
        this.amount = amount;
        this.provider = provider;
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
