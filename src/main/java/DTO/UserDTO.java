package DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/***
 * Initial version created by: Taken from CDIO 1
 * Edited by: Silas Rindorf
 * Created: 08-06-2020
 * This class is responsible for:
 *  Storing information about users in Java
 */
public class UserDTO implements Serializable{
  private static final long serialVersionUID = 4545864587995944260L;
  private int userID;
  private String username = "null";
  private String ini = "null";
  private ArrayList<String> roles;
  private String CPR = "null";
  private String password;

  public UserDTO() {
    this.roles = new ArrayList<String>();
    this.password = newPassword();

  }
  public UserDTO(int userID, String username, String ini, String CPR, String password, String[] roles){
    this.roles = new ArrayList<String>();
    this.userID = userID;
    this.username = username;
    this.ini = ini;
    this.CPR = CPR;
    this.password = password;
    for (String role : roles) {
      this.roles.add(role);
    }
  }

  @Override
  public String toString() {
    StringBuilder userRoles = new StringBuilder();
    for (String role :
            roles) {
      userRoles.append(", ").append(role);
    }
    return "UserDTO [userId=" + userID + ", userName=" + username + ", ini=" + ini + ", roles=" + userRoles.toString() + "]";
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

  public void addRole(String role){
    this.roles.add(role);
  }

  public boolean removeRole(String role){
    return this.roles.remove(role);
  }

  //Getters and Setters

  public String getPassword() {
    return password;
  }
  public void setPassword(String pass){this.password = pass;}

  public String getCPR() {
    return CPR;
  }
  public void setCPR(String CPR){ this.CPR = CPR;}

  public int getUserID() {
    return userID;
  }
  public void setUserID(int userID) {
    this.userID = userID;
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

  public List<String> getRoles() {
    return roles;
  }
  public void setRoles(ArrayList<String> roles) {
    this.roles = roles;
  }
}