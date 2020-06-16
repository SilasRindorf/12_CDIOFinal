package DAL.persistent;

import DTO.CommodityBatchDTO;
import DTO.CommodityDTO;

import java.io.Serializable;
import java.util.List;

// Commodity and Batch Pair
public class CaBPair implements Serializable {
    private static final long serialVersionUID = 503723103259767L;
    public List<CommodityBatchDTO> batches;
    public List<CommodityDTO> coms;

    public CaBPair(List<CommodityBatchDTO> batches, List<CommodityDTO> coms) {
        this.batches = batches;
        this.coms = coms;
    }
}
