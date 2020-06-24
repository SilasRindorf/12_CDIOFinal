package DAL.interfaces;

import java.util.List;

// @formatter:off
/**
 * Whenever the business logic restrictions on the format is violated,
 * throw this exception, with perhaps an accumulated list of all that went wrong
 */
// @formatter:on
public class JunkFormatException extends Exception {
    public List<ErrorList> errorlist;

    public JunkFormatException(String message, List<ErrorList> errorlist) {
        super(message);
        this.errorlist = errorlist;
    }

    public enum ErrorList {
        ID,
        TOO_BIG_ID
    }
}

