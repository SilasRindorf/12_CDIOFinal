package DTO;

import RAM.ProductBatch;
import RAM.ProductBatchComp;

import java.util.Date;
import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 16-06-2020
 * This class is responsible for:
 *
 */
public class ProductBatchDTO {
    private int productBatchNr;
    private int receiptNr;
    private Date created;
    private ProductBatch.Status status;
    private ProductBatchComp[] productComps;
    private boolean isActive;
    public ProductBatchDTO() {

    }

    public int getProductBatchNr() {
        return productBatchNr;
    }

    public void setProductBatchNr(int productBatchNr) {
        this.productBatchNr = productBatchNr;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getReceiptNr() {
        return receiptNr;
    }

    public void setReceiptNr(int receiptNr) {
        this.receiptNr = receiptNr;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public ProductBatch.Status getStatus() {
        return status;
    }

    public void setStatus(ProductBatch.Status status) {
        this.status = status;
    }

    public ProductBatchComp[] getProductComps() {
        return productComps;
    }

    public void setProductComps(ProductBatchComp[] productComps) {
        this.productComps = productComps;
    }

}
