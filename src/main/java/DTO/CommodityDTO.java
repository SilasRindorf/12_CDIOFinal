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

    public CommodityDTO(int commodityNr, String name, boolean isActive) {
        this.commodityNr = commodityNr;
        this.name = name;
        this.isActive = isActive;
    }

    public CommodityDTO() {
    }

    public int getCommodityNr() {
        return commodityNr;
    }

    public void setCommodityNr(int commodityNr) {
        this.commodityNr = commodityNr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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
