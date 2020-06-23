package DTO;

/***
 * Initial version created by: Morten
 * Edited by: 
 * Created: 19-06-2020
 * This class is responsible for:
 *  -
 */
public class TestDTO {
    private int id;
    private String username;

    public TestDTO(){

    }

    public TestDTO(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
