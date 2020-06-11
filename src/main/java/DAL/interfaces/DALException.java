package DAL.interfaces;

/***
 * Initial version created by: Taken from CDIO 1
 * Edited by: Silas
 * Created: 08-06-2020
 * This class is responsible for:
 *  Exception class
 *  Gets cast when a edit to IUserDAO methods fail
 */
public class DALException extends Exception {
    private static final long serialVersionUID = 7355418246336739229L;

    public DALException(String msg, Throwable e) {
        super(msg, e);
    }

    public DALException(String msg) {
        super(msg);
    }

}