package DTO;

import java.util.Collections;
import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: Christoffer
 * Created: 09-06-2020
 * This class is responsible for:
 *  -
 */
public class ReceiptDTO extends DTO {
    private int receiptNr;
    private String name;
    private List<ReceiptCompDTO> receiptComps;

    public ReceiptDTO(int receiptNr, String name, List<ReceiptCompDTO> receiptComps, boolean isActive) {
        super(isActive);
        this.receiptNr = receiptNr;
        this.name = name;
        this.receiptComps = Collections.unmodifiableList(receiptComps);
    }

    public int getReceiptNr() {
        return receiptNr;
    }

    public String getName() {
        return name;
    }

    public List<ReceiptCompDTO> getReceiptComps() {
        return receiptComps;
    }


    @Override
    public String toString() {
        return "ReceiptDTO{" +
                "receiptNr=" + receiptNr +
                ", name='" + name + '\'' +
                ", receiptComps=" + receiptComps +
                ", isActive=" + isActive() +
                '}';
    }
}
