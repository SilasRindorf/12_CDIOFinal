package DAL.interfaces;

/***
 * Main responsible: Christoffer
 * Edited by: Silas, Alexander, Sejr, Andreas
 * Created: 08-06-2020
 * This class is responsible for:
 *  Exception class
 *  Casts whenever there is an error on the database in regards to violating the database consistency (adding the same id, adding something which refers to some non-existing id etc.):w
 *  Which does not include the format of the input,
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