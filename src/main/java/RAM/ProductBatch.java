package RAM;

import DTO.PrintDTO;
import DTO.ProductBatchDTO;

import java.io.Serializable;
import java.util.ArrayList;
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
public class ProductBatch extends IdAndActivatable implements Serializable {
    public static final long serialVersionUID = 567501352532323232L;
    private int receiptNr;
    private Date created;
    private Status status;
    private PrintDTO printDTO;
    private List<ProductBatchComp> productComps;

    public ProductBatch(int id, boolean isActive, int receiptNr, Date created, Status status, PrintDTO printDTO, List<ProductBatchComp> productComps) {
        super(id, isActive);
        this.receiptNr = receiptNr;
        this.created = created;
        this.status = status;
        this.printDTO = printDTO;
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

    public int getProductBatchID(){
        return getID();
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PrintDTO getPrintDTO() {
        return printDTO;
    }

    public void setPrintDTO(PrintDTO printDTO) {
        this.printDTO = printDTO;
    }

    public List<ProductBatchComp> getProductComps() {
        return productComps;
    }

    public void setProductComps(List<ProductBatchComp> productComps) {
        this.productComps = productComps;
    }

    @Override
    public String toString() {
        return "ProductBatch{" +
                "receiptNr=" + receiptNr +
                ", created=" + created +
                ", status=" + status +
                ", printDTO=" + printDTO +
                ", productComps=" + productComps +
                '}';
    }

    public enum Status {
        CREATED,
        IN_PRODUCTION,
        DONE
    }
}
