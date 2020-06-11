package DTO;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 11-06-2020
 * This class is responsible for:
 *  -
 */
public class DTO {
    private boolean active;
    private int ID;

    public DTO(){
        active = true;
    }

    public DTO(int ID) {
        this.active = true;
        this.ID = ID;
    }

    public DTO(boolean isActive) {
        this.active = isActive;
        this.ID = 0;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "DTO{" +
                "isActive=" + active +
                "ID=" + ID +
                "}";
    }
}

