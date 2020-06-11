package DAL.interfaces;

import java.util.List;

/**
 *
 */
public class JunkFormatException extends Exception{

    public List<errorList> errorlist;
    JunkFormatException(String message, List<errorList> errorlist){
        super(message);
        this.errorlist = errorlist;
    }
    public enum errorList {
        // Userformat
    }
}

}
