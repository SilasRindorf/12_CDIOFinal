package DTO;

import java.io.Serializable;

/***
 * Initial version created by: Silas
 * Edited by: Christoffer
 * Created: 09-06-2020
 * This class is responsible for:
 *  -
 */
public class CommodityDTO {
    private int commodityNr;
    private String name;
    private boolean isActive;
    public CommodityDTO() {
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return "Commodity{" + " | " +
                "commodityNr=" + commodityNr + " | " +
                ", name=" + name + " | " +
                ", isActive=" + isActive + "}";
    }
}
