package RAM;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/***
 * Main responsible: Christoffer
 * Edited and reviewed by: Sejr, Morten, Andreas, Alexander, Silas
 */

public class IdAndActivatable implements Serializable {
    public static final long serialVersionUID = 4447252752752745257L;
    private boolean active;
    private int ID;

    public static <T> List<T> filterAddIds(List<T> list, List<Integer> ids) {
        List<T> arr = new ArrayList<T>();
        for (T elem : list) {
            if (ids.contains(((IdAndActivatable) elem).getID())) {
                arr.add(elem);
            }
        }
        return arr;
    }

    public IdAndActivatable() {

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

