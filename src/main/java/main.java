import DTO.ProductBatchCompDTO;
import DTO.ProductBatchDTO;
import DTO.UserDTO;

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
        for (int i = 0; i < 2; i++) {
            listie.add(new ProductBatchCompDTO((double) i, (double) i, i, i, i, true));
        }
        ProductBatchDTO pbd = new ProductBatchDTO(0,0,new Date(), ProductBatchDTO.Status.IN_PRODUCTION,listie,true);
        System.out.println(pbd.toString());
    }
}
