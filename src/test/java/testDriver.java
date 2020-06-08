import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
//***

/***
 * Initial version created by: Silas Rindorf
 * Edited by:
 * ... more method documentation
 */
public class testDriver {
    public static void main(String[] args){
        Result result = JUnitCore.runClasses(TestDAL.class);
        for (Failure failure : result.getFailures()){
            System.out.println(failure.toString());
        }
        result = JUnitCore.runClasses(TestDAO.class);
        for (Failure failure : result.getFailures()){
            System.out.println(failure.toString());
        }
    }
}
