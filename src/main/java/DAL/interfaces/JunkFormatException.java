package DAL.interfaces;

import java.util.List;

/**
 *
 */
public class JunkFormatException extends Exception{

    public List<ErrorList> errorlist;
    JunkFormatException(String message, List<ErrorList> errorlist){
        super(message);
        this.errorlist = errorlist;
    }
    public enum ErrorList {
        // Userformat
    }
}

