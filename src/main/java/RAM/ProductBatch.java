package RAM;

import DAL.interfaces.DALException;
import DTO.PrintProductBatchDTO;
import DTO.ProductBatchDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: Christoffer
 * Created: 09-06-2020
 * This class is responsible for:
 *  -
 */
public class ProductBatch extends IdAndActivatable implements Serializable {
    public static final long serialVersionUID = 567501352532323232L;
    private int receiptNr;
    private Date created;
    private Status status;
    private List<ProductBatchComp> productComps;

    public ProductBatch(int id, boolean isActive, int receiptNr, Date created, Status status, List<ProductBatchComp> productComps) {
        super(id, isActive);
        this.receiptNr = receiptNr;
        this.created = created;
        this.status = status;
        this.productComps = productComps;
    }

    public ProductBatch(ProductBatchDTO productBatchDTO) {
        super(productBatchDTO.getProductBatchNr(), productBatchDTO.isActive());
        this.receiptNr = productBatchDTO.getReceiptNr();
        this.created = productBatchDTO.getCreated();
        this.status = productBatchDTO.getStatus();
        productComps = new ArrayList<>();
        for (int i = 0; i < productBatchDTO.getProductComps().size(); i++) {
            productComps.add(new ProductBatchComp(productBatchDTO.getProductComps().get(i)));
        }
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getReceiptNr() {
        return receiptNr;
    }

    public Date getCreated() {
        return created;
    }

    public Status getStatus() {
        return status;
    }

    public List<ProductBatchComp> getProductComps() {
        return productComps;
    }


    @Override
    public String toString() {
        return "ProductBatch{" +
                "receiptNr=" + receiptNr +
                ", created=" + created +
                ", status=" + status +
                ", productComps=" + productComps +
                '}';
    }

    public enum Status {
        CREATED,
        IN_PRODUCTION,
        DONE
    }
}
