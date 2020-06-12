package DAL.nonPersistent;

import DAL.interfaces.DALException;
import DAL.interfaces.ICommodityDAO;
import DAL.interfaces.JunkFormatException;
import DTO.CommodityBatchDTO;
import DTO.CommodityDTO;

import java.util.ArrayList;
import java.util.List;

public class CommodityDAONonPersistent implements ICommodityDAO {
    private List<CommodityDTO> commodities = new ArrayList<>();
    @Override
    public CommodityDTO getCommodity(int commodityID) throws DALException {
        for (CommodityDTO commodity :
                commodities) {
            if (commodity.getID() == commodityID)
                return commodity;
        }
        throw new DALException("There is no commodity with: ID=" + commodityID);
    }

    @Override
    public List<CommodityDTO> getCommodityList() throws DALException {
        if (commodities.isEmpty())
            throw new DALException("No commodities");
        return commodities;
    }

    @Override
    public void createCommodity(CommodityDTO commodity) throws DALException, JunkFormatException {
        for (CommodityDTO commodityDTO :
                commodities) {
            if (commodityDTO.getID() == commodity.getID())
                throw new DALException("There is already a commodity with: ID=" + commodity.getID());
            else if (commodityDTO.getName().equals(commodity.getName()))
                //throw new JunkFormatException("There is already a commodity with: name=" + commodity.getName());
                return;
        }
    }

    @Override
    public void updateCommodity(CommodityDTO commodity) throws DALException, JunkFormatException {

    }

    @Override
    public void setIsActiveCommodity(int cId, boolean isActive) {
        for (int i = 0; i < commodities.size(); i++) {
            if (commodities.get(i).getID() == cId);
                //commodities.get(i) = new CommodityDTO(cId,commodities.get(i).getName(),isActive);
        }

    }

    @Override
    public CommodityBatchDTO getCommodityBatch(int commodityBatchID) throws DALException {
        return null;
    }

    @Override
    public List<CommodityBatchDTO> getCommodityBatchList() throws DALException {
        return null;
    }

    @Override
    public List<CommodityBatchDTO> getCommodityBatchList(int commodityID) throws DALException {
        return null;
    }

    @Override
    public void createCommodityBatch(CommodityBatchDTO commodityBatch) throws DALException, JunkFormatException {

    }

    @Override
    public void setIsActiveBatch(int cbId, boolean isActive) {

    }



}
