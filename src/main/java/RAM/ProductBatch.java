package RAM;

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
public class ProductBatch extends IdAndActivatable {
    private int receiptNr;
    private Date created;
    private Status status;
    private List<ProductBatchComp> productComps;

    public ProductBatch(int productBatchNr, int receiptNr, Date created, Status status, List<ProductBatchComp> productComps, boolean isActive) {
        super(productBatchNr, isActive);
        this.receiptNr = receiptNr;
        this.created = created;
        this.status = status;
        this.productComps = Collections.unmodifiableList(productComps);
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

    public List<ProductBatchComp> getProductComps() { return productComps; }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (ProductBatchComp pbc :
                productComps) {
            builder.append("\n\t").append(pbc.toString());
        }
        return "ProductBatchDTO{" + "receiptNr=" + receiptNr + " | " +
                "created=" + created + " | " +
                "status=" + status + " | " +
                "isActive=" + getIsActive() + " | " +
                "id = " + getID() + " | " +
                "productComps=" + builder.toString() + '}';
    }


    public enum Status {
        CREATED,
        IN_PRODUCTION,
        DONE
    }
}
