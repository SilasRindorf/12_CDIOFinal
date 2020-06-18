package DAL.persistent;

import RAM.*;

import java.io.Serializable;
import java.util.List;

// Commodity and Batch Pair
public class CaBPair implements Serializable {
    private static final long serialVersionUID = 503723103259767L;
    public List<CommodityBatch> batches;
    public List<Commodity> coms;

    public CaBPair(List<CommodityBatch> batches, List<Commodity> coms) {
        this.batches = batches;
        this.coms = coms;
    }
}
