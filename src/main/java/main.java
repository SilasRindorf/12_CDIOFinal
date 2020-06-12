import DTO.ProductBatchCompDTO;
import DTO.ProductBatchDTO;
import DTO.UserDTO;
import com.lambdaworks.crypto.SCryptUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * Initial version created by: Silas
 * Edited by: 
 * Created: 08-06-2020
 * This class is responsible for:
 *
 */
public class main {
    public static void main(String[] args) {
        List<ProductBatchCompDTO> listie = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listie.add(new ProductBatchCompDTO((double) i, (double) i, i, i, i, true));
        }
        ProductBatchDTO pbd = new ProductBatchDTO(0,0,new Date(), ProductBatchDTO.Status.IN_PRODUCTION,listie,true);
        System.out.println(pbd.toString());
    }
    public static String makeHashed(String nonHashedPass){
        // TODO: Needs to be in logic, not in main
        // Timed as to give about x220 i5 0.5 seconds computing time, for the password "secret".
        // Parameters: CPU usage, space usage, parallel execution
        // Some sources on the parameters:
        // https://github.com/wg/scrypt
        // https://blog.filippo.io/the-scrypt-parameters/
        // As of 2017, this is more than optimal: https://blog.filippo.io/the-scrypt-parameters/ (checked 12. June 2020)
        return SCryptUtil.scrypt(nonHashedPass, (int) Math.pow(2,16), 8, 1  );
    }
}
