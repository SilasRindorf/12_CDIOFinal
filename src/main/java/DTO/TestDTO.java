package DTO;

/***
 * Initial version created by: Auezeras
 * Edited by: 
 * Created: 19-06-2020
 * This class is responsible for:
 *  -
 */
public class TestDTO {
    private int ID;
    private String username;

    public TestDTO(){

    }

    public TestDTO(int ID, String username) {
        this.ID = ID;
        this.username = username;
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
}
