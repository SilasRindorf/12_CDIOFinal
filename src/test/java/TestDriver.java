import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
/***
 * Initial version created by: Silas
 * Edited by:
 * Created: 08-06-2020
 * This class is responsible for:
 *  Running all tests
 *  Printing all test failures
 */
public class TestDriver {
    public static void main(String[] args){
        Result result = JUnitCore.runClasses(TestDAL.class);
        for (Failure failure : result.getFailures()){
            System.out.println(failure.toString());
        }


        result = JUnitCore.runClasses(TestDAO.class);
        for (Failure failure : result.getFailures()){
            System.out.println(failure.toString());
        }

        result = JUnitCore.runClasses(TestUserDAO.class);
        for (Failure failure : result.getFailures()){
            System.out.println(failure.toString());
        }
    }
}
