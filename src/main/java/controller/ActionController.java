package controller;


import DAL.interfaces.*;
import DAL.nonPersistent.*;
import DAL.persistent.*;
import DTO.*;
import RAM.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * Initial version created by: Morten
 * Edited by: Silas, Christoffer
 * Created: 19-06-2020
 * This class is responsible for:
 *  -
 */
public class ActionController {
    private static ActionController ActionControllerInstance = null;
    //private final IUserDAO USERS = new UserDAONonPersistent();
    //private final ICommodityDAO COM = new CommodityDAONonPersistent();
    //private final IReceiptDAO REC = new ReceiptDAONonPersistent(COM);
    //private final IProductDAO PRO = new ProductDAONonPersistent(REC);
    private final IUserDAO USERS = new UserDAO(FileAPI.USER_DAO_FILE);
    private final ICommodityDAO COM = new CommodityDAO(FileAPI.COMMODITY_DAO_FILE);
    private final IReceiptDAO REC = new ReceiptDAO(FileAPI.RECEIPT_DAO_FILE, COM);
    private final IProductDAO PRO = new ProductDAO(FileAPI.PRODUCT_DAO_FILE, REC);

    // TODO: Remove test fields
    private final ReceiptComp RECC = new ReceiptComp(1, 400, 2);
    private final List<ReceiptComp> receiptCompList = new ArrayList<ReceiptComp>();
    private ArrayList<ReceiptDTO> rList = new ArrayList<>();


    private ActionController() throws IOException, ClassNotFoundException {
        try {
            receiptCompList.add(RECC);
            USERS.createUser(new User(11, "Admin", "adm", "123", User.hash("password"), User.Role.Administrator, true));
            COM.createCommodity(new Commodity(1, "Citron", true));
            COM.createBatch(new CommodityBatch(1, 1, 5000, "Mærsk", true));
            REC.createReceipt(new Receipt(1, "Bajer", receiptCompList, true));
            ArrayList<ProductBatchComp> listie = new ArrayList<>();
            listie.add(new ProductBatchComp(2.2, 2.1, 1, 2, "SIL", true));
            PRO.createBatch(new ProductBatch(1, true, 1, new Date(), ProductBatch.Status.IN_PRODUCTION, new ArrayList<>()));
        } catch (Exception ignored) {

        }
    }

    // static method to create instance of Singleton class
    public static ActionController getInstance() {
        if (ActionControllerInstance == null) {
            try {
                ActionControllerInstance = new ActionController();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

        return ActionControllerInstance;
    }

    public boolean logIn(String name, String pass) {
        try {
            for (int i = 0; i < USERS.getUserList().size(); i++) {
                if (USERS.getUserList().get(i).getIsActive()
                        && USERS.getUserList().get(i).getUsername().equalsIgnoreCase(name)
                        && User.check(pass, USERS.getUserList().get(i).getHashedPass())) {
                    return true;
                }
            }
        } catch (DALException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    // ------------------------------- User methods ------------------------------------------
    public String createUser(UserDTO userDTO) throws JunkFormatException, DALException {

        USERS.createUser(new User(userDTO.getID(), userDTO.getUsername(),
                userDTO.getIni(), userDTO.getCPR(), User.hash(userDTO.getNonHashedPassword()),
                User.Role.valueOf(userDTO.getRole()), userDTO.isActive()));

        return "Bruger lavet";
    }

    public String getUsers() throws DALException {
        ObjectMapper objMapper = new ObjectMapper();
        try {
            return objMapper.writeValueAsString(USERS.getUserList());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Kunne ikke skaffe brugere";
        }
    }

    public void setIsActiveUser(int id, boolean status) throws DALException {

        USERS.setIsActive(id, status);


    }

    // ------------------------------- Commodity methods ------------------------------------------
    public String getCommodities() throws DALException {
        ObjectMapper objMapper = new ObjectMapper();
        try {
            return objMapper.writeValueAsString(COM.getCommodityList());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Kunne ikke skaffe brugere";
        }
    }

    public void setIsActiveCommodity(int commodityNr, boolean status) throws DALException {
        COM.setIsActiveCommodity(commodityNr, status);

    }

    public String createCommodity(CommodityDTO commodityDTO) throws JunkFormatException, DALException {

        COM.createCommodity(new Commodity(commodityDTO.getCommodityNr(), commodityDTO.getName(), commodityDTO.isActive()));

        return "Råvare lavet";
    }

    // ------------------------------- CommodityBatch methods ------------------------------------------
    public String getCommodityBatch() throws DALException {
        ObjectMapper objMapper = new ObjectMapper();
        try {
            return objMapper.writeValueAsString(COM.getBatchList());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Kunne ikke skaffe råvarebatches";
        }
    }

    public void setIsActiveCommodityBatch(int commodityBatchNr, boolean status) throws DALException {

        COM.setIsActiveBatch(commodityBatchNr, status);

    }

    public String createCommodityBatch(CommodityBatchDTO commodityBatchDTO) throws JunkFormatException, DALException {

        COM.createBatch(new CommodityBatch(commodityBatchDTO.getCommodityBatchNr(), commodityBatchDTO.getCommodityNr(), commodityBatchDTO.getAmount(), commodityBatchDTO.getProvider(), commodityBatchDTO.isActive()));

        return "Råvarebatch lavet";
    }

// ------------------------------- Receipt methods ------------------------------------------

    public String getReceipts() throws DALException {
        ObjectMapper objMapper = new ObjectMapper();
        try {
            return objMapper.writeValueAsString(REC.getReceiptList());
        } catch (JsonProcessingException | DALException e) {
            e.printStackTrace();
            return "Kunne ikke skaffe recepter";
        }
    }

    public String getReceiptComps(int receiptNr) {
        ObjectMapper objMapper = new ObjectMapper();
        for (ReceiptDTO receiptDTO : rList) {
            if (receiptNr == receiptDTO.getReceiptNr())
                try {
                    return objMapper.writeValueAsString(receiptDTO.getReceiptComps());
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    return "Kunne ikke skaffe komponenter";
                }
        }
        return "Ingen recepter med nummer: " + receiptNr;
    }

    public void setIsActiveReceipt(int receiptNumber, boolean status) throws JunkFormatException, DALException {

        REC.setIsActive(receiptNumber, status);

    }

    public void createReceiptDTO(int receiptNr, String name) throws DALException {
        for (ReceiptDTO receiptDTO : rList) {
            if (receiptDTO.getReceiptNr() == receiptNr) {
                throw new DALException("Id findes i forvejen");
            }
        }
        for (Receipt receipt : REC.getReceiptList()) {
            if (receipt.getID() == receiptNr) {
                throw new DALException("Id findes i forvejen");
            }
        }

        rList.add(new ReceiptDTO(receiptNr, name, new ArrayList<>(), true));
    }

    public String createReceipt(int receiptNr) throws JunkFormatException, DALException {
        for (ReceiptDTO receiptDTO : rList) {
            if (receiptDTO.getReceiptNr() == receiptNr) {

                REC.createReceipt(new Receipt(receiptDTO));

                return "Recept lavet";
            }
        }

        return "Recept ikke lavet";
    }

    public String addReceiptComp(int receiptID, ReceiptCompDTO receiptCompDTO) {
        for (ReceiptDTO receiptDTO : rList) {
            if (receiptDTO.getReceiptNr() == receiptID) {
                receiptDTO.addReceiptComp(receiptCompDTO);
                return "Komponent tilføjet";
            }
        }
        return "Kunne ikke tilføje komponent";

    }
//_______________________________ ProductBatch ________________________________

    public String createProductBatch(int id, boolean isActive, int receiptNr, Date created, ProductBatch.Status status, List<ProductBatchComp> productComps) {
        try {
            PRO.createBatch(new ProductBatch(id, isActive, receiptNr, created, status, productComps));
        } catch (DALException | JunkFormatException e) {
            e.printStackTrace();
            return "Kunne ikke laves";
        }
        return "produkt batch lavet";
    }

    public String getProductBathes() throws DALException {
        ObjectMapper objMapper = new ObjectMapper();
        try {
            return objMapper.writeValueAsString(PRO.getBatchList());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Kunne ikke skaffe produkt batches";
        }
    }

    //_______________________________ Printer _______________________________


    public String createAndGetPrint(int productBatchNr) throws DALException {
        ProductBatch pB = PRO.getBatch(productBatchNr);
        // REC and COM must only call non-modifiable methods!
        PrintProductBatchDTO printPB = new PrintProductBatchDTO(pB, REC, COM);

        ObjectMapper objMapper = new ObjectMapper();
        try {
            return objMapper.writeValueAsString(printPB);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Kunne ikke printe";
        }

    }


    //_______________________________ Afvejning _________________________________

    public String getProductComps(int productBatchNr) throws DALException {
        ObjectMapper objMapper = new ObjectMapper();
        if (PRO.getBatch(productBatchNr).getProductComps() != null) {
            ProductBatch pB = PRO.getBatch(productBatchNr);
            try {
                return objMapper.writeValueAsString(pB.getProductComps());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return "Kunne ikke skaffe product komponenter";
            }

        }
        else{
            return "";
        }
    }
    public String createProductBatchComp(int productBatchNr, ProductBatchCompDTO productBatchCompDTO) {
        try {
            PRO.getBatch(productBatchNr).getProductComps().add(new ProductBatchComp(productBatchCompDTO));
            ProductBatch pB = PRO.getBatch(productBatchNr);
            PRO.updateBatch(new ProductBatch(pB.getID(),pB.getIsActive(),pB.getReceiptNr(),pB.getCreated(), ProductBatch.Status.IN_PRODUCTION,pB.getProductComps()));
        } catch (DALException | JunkFormatException e) {
            e.printStackTrace();
            return "Kunne ikke lave product batch comp";
        }

        return "Product batch comp lavet";
    }

    public String finishProductBatch(int productBatchNr){
        try {
            ProductBatch pB = PRO.getBatch(productBatchNr);
            PRO.updateBatch(new ProductBatch(pB.getID(),pB.getIsActive(),pB.getReceiptNr(),pB.getCreated(), ProductBatch.Status.DONE,pB.getProductComps()));
        } catch (DALException | JunkFormatException e) {
            e.printStackTrace();
            return "Det var ikke muligt at færdigøre den";
        }
        return "Den er blevet færdiggjort";
    }



}

