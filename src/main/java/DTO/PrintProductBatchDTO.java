package DTO;


import DAL.interfaces.DALException;
import DAL.interfaces.ICommodityDAO;
import DAL.interfaces.IReceiptDAO;
import RAM.ProductBatch;
import RAM.ProductBatchComp;
import RAM.ReceiptComp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: Morten, Christoffer
 * Created: 23-06-2020
 * This class is responsible for:
 *
 */
public class PrintProductBatchDTO {
    private int receiptNr;
    private int productBatchNr;
    private List<innerClass> list;
    private Date date;
    private double tara;
    private double netto;

    public PrintProductBatchDTO() {
    }
    //REC and COM are only used on construction.
    public PrintProductBatchDTO(ProductBatch pB, IReceiptDAO REC, ICommodityDAO COM) throws DALException {
        receiptNr = pB.getReceiptNr();
        productBatchNr = pB.getID();
        list = new ArrayList<>();

        for (int i = 0; i < REC.getReceipt(pB.getReceiptNr()).getReceiptComps().size(); i++) { // getReceiptComps().size() = 3
            if(!pB.getProductComps().isEmpty()){
                if (REC.getReceipt(pB.getReceiptNr()).getReceiptComps().get(i) != null){
                    ReceiptComp rC = REC.getReceipt(pB.getReceiptNr()).getReceiptComps().get(i);
                    ProductBatchComp pBC = pB.getProductComps().get(i);
                    double amount = rC.getAmount();
                    double tolerance = rC.getTolerance();
                    double tara = pBC.getTara();
                    double netto = pBC.getWeighted();
                    int commodityBatchNr = pBC.getCommodityBatchNr();
                    int commodityNr = pBC.getCommodityNr();
                    String name = COM.getCommodity(rC.getCommodity()).getName();
                    String ini = pBC.getIni();
                    list.add(new PrintProductBatchDTO.innerClass(amount,tolerance,tara,netto,commodityBatchNr,commodityNr,name,ini));
                }
                else {
                    createInnerClassComps(pB, REC, COM, i);
                }
            }
            else {
                createInnerClassComps(pB, REC, COM, i);
            }

        }
        netto = calcNetto();
        tara = calcTara();
    }

    // REC and COM must only call non-modifiable methods!
    private void createInnerClassComps(ProductBatch pB, IReceiptDAO REC, ICommodityDAO COM, int i) throws DALException {
        ReceiptComp rC = REC.getReceipt(pB.getReceiptNr()).getReceiptComps().get(i);
        double amount = rC.getAmount();
        double tolerance = rC.getTolerance();
        double tara = -1;
        double netto = -1;
        int commodityBatchNr = -1;
        int commodityNr = REC.getReceipt(pB.getReceiptNr()).getReceiptComps().get(i).getCommodity();
        String name = COM.getCommodity(rC.getCommodity()).getName();
        String ini = "";
        list.add(new PrintProductBatchDTO.innerClass(amount,tolerance,tara,netto,commodityBatchNr,commodityNr,name,ini));
    }


    public PrintProductBatchDTO(int receiptNr, int productBatchNr, ArrayList<innerClass> list, Date date, double tara, double netto) {
        this.receiptNr = receiptNr;
        this.productBatchNr = productBatchNr;
        this.list = list;
        this.date = date;
        this.tara = tara;
        this.netto = netto;
    }

    public double getTara() {
        return tara;
    }

    public double getNetto() {
        return netto;
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

    public double calcNetto(){
        double counter = 0;
        for (innerClass inner:
                list) {
            counter += inner.getNetto();
        }
        return counter;
    }

    public double calcTara(){
        double counter = 0;
        for (innerClass inner:
                list) {
            counter += inner.getTara();
        }
        return counter;
    }



    public class innerClass{
        private double amount;
        private double tolerance;
        private double tara;
        private double netto;
        private int commodityBatchNr;
        private int commodityNr;
        private String commodityName;
        private String ini;

        public innerClass() {
        }


        public innerClass(double amount, double tolerance, double tara, double netto, int commodityBatchNr, int commodityNr, String commodityName, String ini) {
            this.amount = amount;
            this.tolerance = tolerance;
            this.tara = tara;
            this.netto = netto;
            this.commodityBatchNr = commodityBatchNr;
            this.commodityNr = commodityNr;
            this.commodityName = commodityName;
            this.ini = ini;
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

        public double getTara() {
            return tara;
        }

        public void setTara(double tara) {
            this.tara = tara;
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

        public String getIni() {
            return ini;
        }

        public void setIni(String ini) {
            this.ini = ini;
        }
    }
}
