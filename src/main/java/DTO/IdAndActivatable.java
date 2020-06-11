package DTO;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 11-06-2020
 * This class is responsible for:
 *  -
 */
public class IdAndActivatable {
    private boolean active;
    private int ID;

    public IdAndActivatable(int id, boolean isActive) {
        this.active = isActive;
        this.ID = id;
    }

    public int getID() {
        return ID;
    }

    public boolean getIsActive() {
        return active;
    }

    @Override
    public String toString() {
        return "DTO{" + " | " +
                "isActive=" + active + " | " +
                "ID=" + ID + "}";
    }
}

