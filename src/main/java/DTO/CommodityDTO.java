package DTO;

/***
 * Initial version created by: Silas
 * Edited by: Christoffer
 * Created: 09-06-2020
 * This class is responsible for:
 *  -
 */
public class CommodityDTO extends IdAndActivatable {
    private String name;

    public CommodityDTO(int commodityNr, String name, boolean isActive) {
        super(commodityNr,isActive);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return "Commodity{" + "\n" +
                "commodityNr=" + getID() +"\n" +
                ", name=" + name +"\n" +
                ", isActive=" + getIsActive() + "\n" +
                "}";
    }
}
