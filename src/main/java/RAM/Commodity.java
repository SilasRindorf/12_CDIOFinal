package RAM;

import java.io.Serializable;

/***
 * Main responsible: Christoffer
 * Edited and reviewed by: Sejr, Morten, Andreas, Alexander, Silas
 */
public class Commodity extends IdAndActivatable implements Serializable {
    public static final long serialVersionUID = 5432657432645732L;
    private String name;

    public Commodity(int commodityNr, String name, boolean isActive) {
        super(commodityNr, isActive);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCommodityNr(){
        return getID();
    }

    @Override
    public String toString() {
        return "Commodity{" + " | " +
                "commodityNr=" + getID() + " | " +
                ", name=" + name + " | " +
                ", isActive=" + getIsActive() + "}";
    }
}
