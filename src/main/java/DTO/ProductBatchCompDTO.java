package DTO;

/***
 * Initial version created by: Silas
 * Edited by: Christoffer
 * Created: 09-06-2020
 * This class is responsible for:
 *  -
 */
public class ProductBatchCompDTO {
    private double tara;
    private double weighted;
    private int commodityBatchNr;
    private int commodityNr;
    private int weighterID;

    public ProductBatchCompDTO(double tara, double weighted, int commodityBatchNr, int commodityNr, int userID, boolean isActive) {
        this.tara = tara;
        this.weighted = weighted;
        this.commodityBatchNr = commodityBatchNr;
        this.commodityNr = commodityNr;
        this.weighterID = userID;
    }

    public double getTara() {
        return tara;
    }

    public double getWeighted() {
        return weighted;
    }

    public int getCommodityBatchNr() {
        return commodityBatchNr;
    }

    public int getCommodityNr() {
        return commodityNr;
    }

    public int getWeighterID() {
        return weighterID;
    }

    @Override
    public String toString() {
        return "ProductBatchCompDTO{" + " | " +
                "tara=" + tara + " | " +
                "weighted=" + weighted +" | " +
                "commodityBatchNr=" + commodityBatchNr + " | " +
                "commodityNr=" + commodityNr + " | " +
                "weighterID=" + weighterID + '}';
    }
}
