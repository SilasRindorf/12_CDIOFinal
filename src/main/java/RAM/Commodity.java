package RAM;

import java.io.Serializable;

/***
 * Initial version created by: Silas
 * Edited by: Christoffer
 * Created: 09-06-2020
 * This class is responsible for:
 *  -
 */
public class Commodity extends IdAndActivatable implements Serializable {
    public static final long serialVersionUID = 5432657432645732L;
    private String name;

    public Commodity(int commodityNr, String name, boolean isActive) {
        super(commodityNr,isActive);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return "Commodity{" + " | " +
                "commodityNr=" + getID() + " | " +
                ", name=" + name + " | " +
                ", isActive=" + getIsActive() + "}";
    }
}
