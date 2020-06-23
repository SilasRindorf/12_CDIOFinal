package DTO;


import java.util.Date;
import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 23-06-2020
 * This class is responsible for:
 *
 */
public class PrintDTO {
    private int receiptNr;
    private int productBatchNr;
    private List<innerClass> list;
    private Date date;
    private double tara;
    private double netto;

    public PrintDTO() {
    }

    public PrintDTO(int receiptNr, int productBatchNr, List<innerClass> list, Date date, double tara, double netto) {
        this.receiptNr = receiptNr;
        this.productBatchNr = productBatchNr;
        this.list = list;
        this.date = date;
        this.tara = tara;
        this.netto = netto;
    }

    private double getNetto(){
        double counter = 0;
        for (innerClass inner:
             list) {
            counter += inner.getNetto();
        }
        return counter;
    }

    private double getTara(){
        double counter = 0;
        for (innerClass inner:
                list) {
            counter += inner.getTara();
        }
        return counter;
    }

    public int getReceiptNr() {
        return receiptNr;
    }

    public void setReceiptNr(int receiptNr) {
        this.receiptNr = receiptNr;
    }

    public int getProductBatchNr() {
        return productBatchNr;
    }

    public void setProductBatchNr(int productBatchNr) {
        this.productBatchNr = productBatchNr;
    }

    public List<innerClass> getList() {
        return list;
    }

    public void setList(List<innerClass> list) {
        this.list = list;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTara(double tara) {
        this.tara = tara;
    }

    public void setNetto(double netto) {
        this.netto = netto;
    }

    @Override
    public String toString() {
        return "PrintDTO{" +
                "receiptNr=" + receiptNr +
                ", productBatchNr=" + productBatchNr +
                ", list=" + list +
                ", date=" + date +
                ", tara=" + tara +
                ", netto=" + netto +
                '}';
    }

    private static class innerClass{
        private int part;
        private int amount;
        private double tolerance;
        private double tara;
        private double netto;
        private int commodityBatchNr;
        private int commodityNr;
        private String commodityName;
        private String ini;

        public innerClass() {
        }


        public innerClass(int part, int amount, double tolerance, double netto, double tara, int commodityBatchNr, int commodityNr, String commodityName, String ini) {
            this.part = part;
            this.amount = amount;
            this.tolerance = tolerance;
            this.netto = netto;
            this.tara = tara;
            this.commodityBatchNr = commodityBatchNr;
            this.commodityNr = commodityNr;
            this.commodityName = commodityName;
            this.ini = ini;
        }

        public double getTara() {
            return tara;
        }

        public void setTara(double tara) {
            this.tara = tara;
        }

        public int getCommodityNr() {
            return commodityNr;
        }

        public void setCommodityNr(int commodityNr) {
            this.commodityNr = commodityNr;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public int getPart() {
            return part;
        }

        public void setPart(int part) {
            this.part = part;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public double getTolerance() {
            return tolerance;
        }

        public void setTolerance(double tolerance) {
            this.tolerance = tolerance;
        }

        public double getNetto() {
            return netto;
        }

        public void setNetto(double netto) {
            this.netto = netto;
        }

        public int getCommodityBatchNr() {
            return commodityBatchNr;
        }

        public void setCommodityBatchNr(int commodityBatchNr) {
            this.commodityBatchNr = commodityBatchNr;
        }

        public String getIni() {
            return ini;
        }

        public void setIni(String ini) {
            this.ini = ini;
        }

        @Override
        public String toString() {
            return "innerClass{" +
                    "part=" + part +
                    ", amount=" + amount +
                    ", tolerance=" + tolerance +
                    ", tara=" + tara +
                    ", netto=" + netto +
                    ", commodityBatchNr=" + commodityBatchNr +
                    ", commodityNr=" + commodityNr +
                    ", commodityName='" + commodityName + '\'' +
                    ", ini='" + ini + '\'' +
                    '}';
        }
    }
}
