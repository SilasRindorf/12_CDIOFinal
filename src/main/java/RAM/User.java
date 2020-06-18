package RAM;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/***
 * Initial version created by: Taken from CDIO 1
 * Edited by: Silas Rindorf
 * Created: 08-06-2020
 * This class is responsible for:
 *  Storing information about users in Java
 */
public class User extends IdAndActivatable implements Serializable {
  // UID is Serializable magic and should not be touched!
  private static final long serialVersionUID = 4545864587995944260L;
  private String username;
  private String ini;
  private String CPR;
  private String hashedPass;
  private Role role;


  public User(int ID, String username, String ini, String CPR, String hashedPass, Role role, boolean isActive){
    super(ID,isActive);
    this.role = role;
    this.username = username;
    this.ini = ini;
    this.CPR = CPR;
    this.hashedPass = hashedPass;
  }

  //TODO Needs JavaDoc
  /***
   *
   * @return
   */
  public static String newPassword(){
    int min = 6;
    int max = 50;
    int len =(int) (Math.random()*(max - min)+ min);
    ArrayList<Character> pass = new ArrayList<Character>();

    char[] special = {'.', '-', '_', '+', '!', '?', '='};
    int x=0;
    for(int i = 0; i < len; i++){
      if(x>2)
        x=0;
      if(x == 0)
        pass.add((char) (Math.random()*(90 - 65)+65));
      else if(x == 1)
        pass.add((char) (Math.random()*(122 - 97) + 97));
      else if(x == 2)
        pass.add(special[(int)(Math.random()*(special.length))]);
      x++;
    }
    Collections.shuffle(pass);
    StringBuilder res = new StringBuilder();
    for(int i =0; i<len; i++ ){
      res.append(pass.get(i));
    }
    return res.toString();
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
}