package RAM;

import DTO.ReceiptDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***
 * Main responsible: Christoffer
 * Edited and reviewed by: Sejr, Morten, Andreas, Alexander, Silas
 */
public class Receipt extends IdAndActivatable implements Serializable {
    public static final long serialVersionUID = 686420351353L;
    private String name;
    private List<ReceiptComp> receiptComps;

    public Receipt(int receiptNr, String name, List<ReceiptComp> receiptComps, boolean isActive) {
        super(receiptNr, isActive);
        this.name = name;
        this.receiptComps = Collections.unmodifiableList(receiptComps);
    }

    public Receipt(ReceiptDTO receiptDTO){
        super(receiptDTO.getReceiptNr(),receiptDTO.isActive());
        receiptComps = new ArrayList<>();
        this.name = receiptDTO.getName();
        for (int i = 0; i < receiptDTO.getReceiptComps().size(); i++) {
            receiptComps.add(new ReceiptComp(receiptDTO.getReceiptComps().get(i)));
        }
    }

    public String getName() {
        return name;
    }

    public List<ReceiptComp> getReceiptComps() {
        return receiptComps;
    }


    @Override
    public String toString() {
        return "ReceiptDTO{" + " | " +
                "name='" + name + '\'' + " | " +
                "receiptComps=" + receiptComps + " | " +
                "isActive=" + getIsActive() + " | " +
                "id= " + getID() + '}';
    }
}
