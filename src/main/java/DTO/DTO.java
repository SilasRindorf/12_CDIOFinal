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

    public DTO(){
        active = true;
    }

    public DTO(boolean isActive) {
        this.active = isActive;
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
                "}";
    }
}

