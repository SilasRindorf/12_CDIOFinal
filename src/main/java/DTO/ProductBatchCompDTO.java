package DTO;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 16-06-2020
 * This class is responsible for:
 *
 */
public class ProductBatchCompDTO {
    private double tara;
    private double weighted;
    private int commodityBatchNr;
    private int commodityNr;
    private int weigherID;

    public ProductBatchCompDTO() {

    }

    public double getTara() {
        return tara;
    }

    public void setTara(double tara) {
        this.tara = tara;
    }

    public double getWeighted() {
        return weighted;
    }

    public void setWeighted(double weighted) {
        this.weighted = weighted;
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

    public int getWeigherID() {
        return weigherID;
    }

    public void setWeigherID(int weigherID) {
        this.weigherID = weigherID;
    }


}
