import DTO.UserDTO;
import Enums.Role;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 08-06-2020
 * This class is responsible for:
 *
 */
public class main {
    public static void main(String[] args) {
        UserDTO user = new UserDTO();
        user.setRole(Role.Administrator);
        System.out.println(user.getRole());
    }
}
