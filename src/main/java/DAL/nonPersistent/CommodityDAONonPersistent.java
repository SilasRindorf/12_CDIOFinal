package DAL.nonPersistent;

import DAL.interfaces.DALException;
import DAL.interfaces.ICommodityDAO;
import DAL.interfaces.JunkFormatException;
import DTO.CommodityBatchDTO;
import DTO.CommodityDTO;
import DTO.IdAndActivatable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommodityDAONonPersistent implements ICommodityDAO {
    private List<CommodityDTO> commodities;
    private List<CommodityBatchDTO> batches;
    public CommodityDAONonPersistent() {
        this.commodities = new ArrayList<>();
    }

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
            if (commodityDTO.getID() == commodity.getID()) {
                throw new DALException("There is already a commodity with: ID=" + commodity.getID());
            } else if (commodityDTO.getName().equals(commodity.getName())) {
                throw new JunkFormatException("There is already a commodity with: name=" + commodity.getName(), Arrays.asList(JunkFormatException.ErrorList.NOT_UNIQUE_NAME));
            }
        }
        commodities.add(commodity);
    }

    @Override
    public void updateCommodity(CommodityDTO commodity) throws DALException, JunkFormatException {
        boolean found = false;
        for(int i = 0; i<commodities.size(); ++i){
            if(commodity.getID() == commodities.get(i).getID()){
                found = true;
                CommodityDTO backup = commodities.get(i);
                commodities.remove(i);
                try {
                    createCommodity(commodity);
                }catch(Exception e){
                    commodities.add(backup);
                    throw e;
                }
            }
        }
        if(!found){
           throw new DALException("Commodity with that ID does not exist");
        }
    }

    @Override
    public void setIsActiveCommodity(int cId, boolean isActive) throws DALException {
        CommodityDTO c = getCommodity(cId);
        if (c.getIsActive() == isActive){
            throw new DALException("The commodity activity is already "+isActive);
        }
        CommodityDTO newC = new CommodityDTO(cId, c.getName(), isActive);
        try {
            updateCommodity(newC);
        } catch (JunkFormatException e) {
            throw new AssertionError("Changing isActive should not result in " +
                    "JunkFormatException, since it should only modify one and only one variable (isActive). " +
                    "This means that the database state was corrupt.");
        }
    }

    @Override
    public CommodityBatchDTO getCommodityBatch(int commodityBatchID) throws DALException {
        List<CommodityBatchDTO> list = IdAndActivatable.<CommodityBatchDTO>filterAddIds(batches,Arrays.asList(commodityBatchID));
        if(list.isEmpty()){
            throw new DALException("There is no commoditybatch with: ID=" + commodityBatchID);
        }
        return list.get(0);
    }

    @Override
    public List<CommodityBatchDTO> getCommodityBatchList() throws DALException {
        return batches;
    }

    @Override
    public List<CommodityBatchDTO> getCommodityBatchList(int commodityID) throws DALException {
        List<CommodityBatchDTO> list = new ArrayList<>();
        for(int i = 0; i<batches.size();++i){
            if(batches.get(i).getCommodityNr() == commodityID){
                list.add(batches.get(i));
            }
        }
        if(list.isEmpty()){
            throw new DALException("There is no commoditybatch which contains a commodity of ID=" + commodityID);
        }
        return list;
    }

    @Override
    public void createCommodityBatch(CommodityBatchDTO newBatch) throws DALException, JunkFormatException {
        for (CommodityBatchDTO batch :
                batches) {
            if (batch.getID() == newBatch.getID()) {
                throw new DALException("There is already a commodity with: ID=" + batch.getID());
            }
        }
        batches.add(newBatch);
    }

    public void updateCommodityBatch(CommodityBatchDTO batch) throws DALException, JunkFormatException {
        boolean found = false;
        for(int i = 0; i<batches.size(); ++i){
            if(batch.getID() == batches.get(i).getID()){
                found = true;
                CommodityBatchDTO backup = batches.get(i);
                batches.remove(i);
                try {
                    createCommodityBatch(batch);
                }catch(Exception e){
                    batches.add(backup);
                    throw e;
                }
            }
        }
        if(!found){
            throw new DALException("Commodity with that ID does not exist");
        }
    }

    @Override
    public void setIsActiveBatch(int cbId, boolean isActive) throws DALException {
        CommodityBatchDTO c = getCommodityBatch(cbId);
        if (c.getIsActive() == isActive){
            throw new DALException("The commoditybatch activity is already "+isActive);
        }
        CommodityBatchDTO newC = new CommodityBatchDTO(c.getID(),c.getCommodityNr(),c.getAmount(),c.getProvider(),isActive);
        try {
            createCommodityBatch(newC);
        } catch (JunkFormatException e) {
            throw new AssertionError("Changing isActive should not result in " +
                    "JunkFormatException, since it should only modify one and only one variable (isActive). " +
                    "This means that the database state was corrupt.");
        }
    }



}
