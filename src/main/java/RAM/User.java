package RAM;

import com.lambdaworks.crypto.SCryptUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/***
 * Main responsible: Christoffer
 * Edited and reviewed by: Sejr, Morten, Andreas, Alexander, Silas
 */

public class User extends IdAndActivatable implements Serializable {
    // UID is Serializable magic and should not be touched!
    private static final long serialVersionUID = 4545864587995944260L;
    private String username;
    private String ini;
    private String CPR;
    private String hashedPass;
    private Role role;

    public User() {
        super();
    }

    public User(int ID, String username, String ini, String CPR, String hashedPass, Role role, boolean isActive) {
        super(ID, isActive);
        this.role = role;
        this.username = username;
        this.ini = ini;
        this.CPR = CPR;
        this.hashedPass = hashedPass;
    }



    //Getters and Setters
    public String getHashedPass() {
        return hashedPass;
    }

    public String getCPR() {
        return CPR;
    }

    public String getUsername() {
        return username;
    }

    public String getIni() {
        return ini;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' + " | " +
                "ini='" + ini + '\'' + " | " +
                "CPR='" + CPR + '\'' + " | " +
                "password='" + hashedPass + '\'' + " | " +
                "role=" + role + " | " +
                "active= " + getIsActive() + " | " +
                "id= " + getID() + '}';
    }

    /***
     * Initial version created by: Silas
     * Edited by: Christoffer
     * Created: 09-06-2020
     * This enum is responsible for:
     *  -
     */
    public enum Role {
        Administrator,
        Farmaceut,
        Produktionsleder,
        Laborant
    }

    /**
     * Generates a new random password based on the password strength criteria of CDIO2.
     * Note: This is not a hashed password
     * @return a String containing the new password
     */
    public static String newPassword() {
        int min = 6;
        int max = 50;
        int len = (int) (Math.random() * (max - min) + min);
        ArrayList<Character> pass = new ArrayList<Character>();

        char[] special = {'.', '-', '_', '+', '!', '?', '='};
        int x = 0;
        for (int i = 0; i < len; i++) {
            if (x > 2)
                x = 0;
            if (x == 0)
                pass.add((char) (Math.random() * (90 - 65) + 65));
            else if (x == 1)
                pass.add((char) (Math.random() * (122 - 97) + 97));
            else if (x == 2)
                pass.add(special[(int) (Math.random() * (special.length))]);
            x++;
        }
        Collections.shuffle(pass);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < len; i++) {
            res.append(pass.get(i));
        }
        return res.toString();
    }

    /**
     * Hashes a given password as to make it more secure to store
     * @param nonHashedPass A nonhashed pass which is not suitable for storing
     * @return A hashed pass which is suitable to store in User class "hashedPass" field
     */
    public static String hash(String nonHashedPass){
        // Timed as to give about Lenovo x220 i5 0.5 seconds computing time, for the password "secret".
        // Parameters: CPU usage, space usage, parallel execution
        // Some sources on the parameters:
        // https://github.com/wg/scrypt
        // https://blog.filippo.io/the-scrypt-parameters/
        // As of 2017, this is more than optimal: https://blog.filippo.io/the-scrypt-parameters/ (checked 12. June 2020)
        return SCryptUtil.scrypt(nonHashedPass, (int) Math.pow(2, 16), 8, 1);
    }

    /**
     * Checks whether the entered nonhashed pass is correct in regards to a hashed one.
     * @param nonHashedPass A non hashed password
     * @param hashedPass The stored hashed password of the user
     * @return whether the hash is made from the nonHashedPass, if not then false.
     */
    public static boolean check(String nonHashedPass, String hashedPass){
        return SCryptUtil.check(nonHashedPass, hashedPass);
    }

}