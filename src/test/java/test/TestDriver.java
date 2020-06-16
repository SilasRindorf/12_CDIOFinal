package test;

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
        Result result = JUnitCore.runClasses(test.TestProductDAO.class);
        for (Failure failure : result.getFailures()){
            System.out.println("FAILURE");
            System.out.println(failure.toString());
        }

        result = JUnitCore.runClasses(test.TestCommodityDAO.class);
        for (Failure failure : result.getFailures()){
            System.out.println("FAILURE");
            System.out.println(failure.toString());
        }
    }
}
