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
    private PrintDTO printDTO;
    private List<ProductBatchCompDTO> productComps;
    private boolean isActive;

    public ProductBatchDTO() {

    }

    public ProductBatchDTO(int productBatchNr, int receiptNr, Date created, ProductBatch.Status status, PrintDTO printDTO, List<ProductBatchCompDTO> productComps,  boolean isActive) {
        this.productBatchNr = productBatchNr;
        this.receiptNr = receiptNr;
        this.created = created;
        this.status = status;
        this.printDTO = printDTO;
        this.productComps = productComps;
        this.isActive = isActive;
    }

    public PrintDTO getPrintDTO() {
        return printDTO;
    }

    public void setPrintDTO(PrintDTO printDTO) {
        this.printDTO = printDTO;
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
