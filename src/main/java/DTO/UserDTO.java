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
    private String hashedPass;
    private String Role;
    private boolean isActive;

    public UserDTO(){

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

    public String getHashedPass() {
        return hashedPass;
    }

    public void setHashedPass(String hashedPass) {
        this.hashedPass = hashedPass;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
