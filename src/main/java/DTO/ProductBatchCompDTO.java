package DTO;

/***
 * Main responsible: Silas
 * Edited by: Christoffer, Morten
 */

public class ProductBatchCompDTO {
    private double tara;
    private double weighted;
    private int commodityBatchNr;
    private int commodityNr;
    private String ini;

    public ProductBatchCompDTO() {

    }

    public ProductBatchCompDTO(double tara, double weighted, int commodityBatchNr, int commodityNr, String ini) {
        this.tara = tara;
        this.weighted = weighted;
        this.commodityBatchNr = commodityBatchNr;
        this.commodityNr = commodityNr;
        this.ini = ini;
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
}
