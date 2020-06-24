package DTO;

import RAM.ProductBatch;

import java.util.Date;
import java.util.List;

/***
 * Main responsible: Silas
 * Edited by: Christoffer, Morten
 */

public class ProductBatchDTO {
    private int productBatchNr;
    private int receiptNr;
    private Date created;
    private ProductBatch.Status status;
    private List<ProductBatchCompDTO> productComps;
    private boolean isActive;

    public ProductBatchDTO() {

    }

    public ProductBatchDTO(int productBatchNr, boolean isActive , int receiptNr, Date created, ProductBatch.Status status, List<ProductBatchCompDTO> productComps) {
        this.productBatchNr = productBatchNr;
        this.receiptNr = receiptNr;
        this.created = created;
        this.status = status;
        this.productComps = productComps;
        this.isActive = isActive;
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

    public List<ProductBatchCompDTO> getProductComps() {
        return productComps;
    }

    public void setProductComps(List<ProductBatchCompDTO> productComps) {
        this.productComps = productComps;
    }


}
