package DTO;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: Christoffer
 * Created: 09-06-2020
 * This class is responsible for:
 *  -
 */
public class ReceiptDTO extends IdAndActivatable implements Serializable {
    static final long serialVersionUID = 8148793402845572332L;
    private String name;
    private List<ReceiptCompDTO> receiptComps;

    public ReceiptDTO(int receiptNr, String name, List<ReceiptCompDTO> receiptComps, boolean isActive) {
        super(receiptNr,isActive);
        this.name = name;
        this.receiptComps = Collections.unmodifiableList(receiptComps);
    }

    public String getName() {
        return name;
    }

    public List<ReceiptCompDTO> getReceiptComps() {
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
