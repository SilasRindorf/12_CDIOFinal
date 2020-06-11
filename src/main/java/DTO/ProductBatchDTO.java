package DTO;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: Christoffer
 * Created: 09-06-2020
 * This class is responsible for:
 *  -
 */
public class ProductBatchDTO {
    private int productBatchNr;
    private int receiptNr;
    private Date created;
    private Status status;
    private List<ProductBatchCompDTO> productComps;

    public ProductBatchDTO(int productBatchNr, int receiptNr, Date created, Status status, List<ProductBatchCompDTO> productComps) {
        this.productBatchNr = productBatchNr;
        this.receiptNr = receiptNr;
        this.created = created;
        this.status = status;
        this.productComps = Collections.unmodifiableList(productComps);
    }

    public int getProductBatchNr() {
        return productBatchNr;
    }

    public int getReceipt() {
        return receiptNr;
    }

    public Date getCreated() {
        return created;
    }

    public Status getStatus() {
        return status;
    }


    public enum Status{
        CREATED,
        IN_PRODUCTION,
        DONE
    }
}