package DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 11-06-2020
 * This class is responsible for:
 *  -
 */
public class IdAndActivatable implements Serializable {

    private boolean active;
    private int ID;

    public static <T> List<T> filterAddIds(List<T> list, List<Integer> ids){
        List<T> arr = new ArrayList<T>();
        for(T elem : list) {
            if(ids.contains(((IdAndActivatable) elem).getID())) {
                arr.add(elem);
            }
        }
        return arr;
    }

    public IdAndActivatable(){

    }

    public IdAndActivatable(int id, boolean isActive) {
        this.active = isActive;
        this.ID = id;
    }

    public int getID() {
        return ID;
    }

    public boolean getIsActive() {
        return active;
    }

    @Override
    public String toString() {
        return "DTO{" + " | " +
                "isActive=" + active + " | " +
                "ID=" + ID + "}";
    }
}

