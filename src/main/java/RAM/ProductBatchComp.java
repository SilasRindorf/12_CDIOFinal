package RAM;

import DTO.ProductBatchCompDTO;

import java.io.Serializable;

/***
 * Initial version created by: Silas
 * Edited by: Christoffer
 * Created: 09-06-2020
 * This class is responsible for:
 *  -
 */
public class ProductBatchComp implements Serializable {
    public static final long serialVersionUID = 12343365L;
    private double tara;
    private double weighted;
    private int commodityBatchNr;
    private int commodityNr;
    private String ini;

    public ProductBatchComp(double tara, double weighted, int commodityBatchNr, int commodityNr, String ini, boolean isActive) {
        this.tara = tara;
        this.weighted = weighted;
        this.commodityBatchNr = commodityBatchNr;
        this.commodityNr = commodityNr;
        this.ini = ini;
    }

    public ProductBatchComp(ProductBatchCompDTO productBatchCompDTO){
        this.tara = productBatchCompDTO.getTara();
        this.weighted = productBatchCompDTO.getWeighted();
        this.commodityBatchNr = productBatchCompDTO.getCommodityBatchNr();
        this.commodityNr = productBatchCompDTO.getCommodityNr();
        this.ini = productBatchCompDTO.getIni();
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

    public String getIni() {
        return ini;
    }

    public void setIni(String ini) {
        this.ini = ini;
    }

    @Override
    public String toString() {
        return "ProductBatchComp{" +
                "tara=" + tara +
                ", weighted=" + weighted +
                ", commodityBatchNr=" + commodityBatchNr +
                ", commodityNr=" + commodityNr +
                ", ini='" + ini + '\'' +
                '}';
    }
}
