package DTO;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 16-06-2020
 * This class is responsible for:
 *
 */
public class UserDTO {
    private int ID;
    private String username;
    private String ini;
    private String CPR;
    private String nonHashedPassword;
    private String role;
    private boolean isActive;

    public UserDTO(){

    }

    public UserDTO(int ID, String username, String ini, String CPR, String hashedPass, String role, boolean isActive) {
        this.ID = ID;
        this.username = username;
        this.ini = ini;
        this.CPR = CPR;
        this.nonHashedPassword = hashedPass;
        this.role = role;
        this.isActive = isActive;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIni() {
        return ini;
    }

    public void setIni(String ini) {
        this.ini = ini;
    }

    public String getCPR() {
        return CPR;
    }

    public void setCPR(String CPR) {
        this.CPR = CPR;
    }

    public String getNonHashedPassword() {
        return nonHashedPassword;
    }

    public void setNonHashedPassword(String nonHashedPassword) {
        this.nonHashedPassword = nonHashedPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
