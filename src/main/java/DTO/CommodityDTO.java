package DTO;

/***
 * Initial version created by: Silas
 * Edited by: Christoffer
 * Created: 09-06-2020
 * This class is responsible for:
 *  -
 */
public class CommodityDTO extends DTO {
    private int commodityNr;
    private String name;

    public CommodityDTO(int commodityNr, String name, boolean isActive) {
        super(isActive);
        this.commodityNr = commodityNr;
        this.name = name;
    }

    public int getCommodityNr() {
        return commodityNr;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return "commodityNr=" + commodityNr +
                ", name=" + name +
                ", isActive=" + isActive();
    }
}
