package DTO;

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
public class UserDTO extends IdAndActivatable implements Serializable {
  private static final long serialVersionUID = 4545864587995944260L;
  private int ID;
  private String username;
  private String ini;
  private String CPR;
  private String password;
  private Role role;

  public UserDTO(int ID, String username, String ini, String CPR, String password, Role role, boolean isActive){
    super(ID,isActive);
    this.role = role;
    this.ID = ID;
    this.username = username;
    this.ini = ini;
    this.CPR = CPR;
    this.password = password;

  }

  //TODO Needs JavaDoc
  /***
   *
   * @return
   */
  public String newPassword(){
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

  public String getPassword() {
    return password;
  }

  public String getCPR() {
    return CPR;
  }

  public int getID() {
    return ID;
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
            "ID=" + ID +
            "username='" + username + '\'' + " | " +
            "ini='" + ini + '\'' + " | " +
            "CPR='" + CPR + '\'' + " | " +
            "password='" + password + '\'' + " | " +
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