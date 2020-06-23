package controller;


import DAL.interfaces.*;
import DAL.nonPersistent.*;
import DTO.*;
import RAM.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActionController {
    private static ActionController ActionControllerInstance = null;
    private final IUserDAO USERS = new UserDAONonPersistent();
    private final ICommodityDAO COM = new CommodityDAONonPersistent();
    private final IReceiptDAO REC = new ReceiptDAONonPersistent(COM);
    private final IProductDAO PRO = new ProductDAONonPersistent(REC);
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
            listie.add(new ProductBatchComp(2.2,2.1,1,2,11,true));
            PRO.createBatch(new ProductBatch(1,1,new Date(), ProductBatch.Status.IN_PRODUCTION,listie,true));
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
    public String createUser(UserDTO userDTO) {
        try {
            USERS.createUser(new User(userDTO.getID(), userDTO.getUsername(),
                    userDTO.getIni(), userDTO.getCPR(), User.hash(userDTO.getNonHashedPassword()),
                    User.Role.valueOf(userDTO.getRole()), userDTO.isActive()));
        } catch (DALException | JunkFormatException e) {
            e.printStackTrace();
            return "Bruger kunne ikke laves";
        }
        return "Bruger lavet";
    }

    public String getUsers() {
        ObjectMapper objMapper = new ObjectMapper();
        try {
            return objMapper.writeValueAsString(USERS.getUserList());
        } catch (JsonProcessingException | DALException e) {
            e.printStackTrace();
            return "Kunne ikke skaffe brugere";
        }
    }

    public void setIsActiveUser(int id, boolean status) {
        try {
            USERS.setIsActive(id, status);
        } catch (DALException e) {
            e.printStackTrace();
        }

    }

    // ------------------------------- Commodity methods ------------------------------------------
    public String getCommodities() {
        ObjectMapper objMapper = new ObjectMapper();
        try {
            return objMapper.writeValueAsString(COM.getCommodityList());
        } catch (JsonProcessingException | DALException e) {
            e.printStackTrace();
            return "Kunne ikke skaffe brugere";
        }
    }

    public void setIsActiveCommodity(int commodityNr, boolean status) {
        try {
            COM.setIsActiveCommodity(commodityNr, status);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public String createCommodity(CommodityDTO commodityDTO) {
        try {
            COM.createCommodity(new Commodity(commodityDTO.getCommodityNr(), commodityDTO.getName(), commodityDTO.isActive()));
        } catch (DALException | JunkFormatException e) {
            e.printStackTrace();
            return "Råvare kunne ikke laves";
        }
        return "Råvare lavet";
    }

    // ------------------------------- CommodityBatch methods ------------------------------------------
    public String getCommodityBatch() {
        ObjectMapper objMapper = new ObjectMapper();
        try {
            return objMapper.writeValueAsString(COM.getBatchList());
        } catch (JsonProcessingException | DALException e) {
            e.printStackTrace();
            return "Kunne ikke skaffe råvarebatches";
        }
    }

    public void setIsActiveCommodityBatch(int commodityBatchNr, boolean status) {
        try {
            COM.setIsActiveBatch(commodityBatchNr, status);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public String createCommodityBatch(CommodityBatchDTO commodityBatchDTO) {
        try {
            COM.createBatch(new CommodityBatch(commodityBatchDTO.getCommodityBatchNr(), commodityBatchDTO.getCommodityNr(), commodityBatchDTO.getAmount(), commodityBatchDTO.getProvider(), commodityBatchDTO.isActive()));
        } catch (DALException | JunkFormatException e) {
            e.printStackTrace();
            return " Kunne ikke laves";
        }
        return "Råvarebatch lavet";
    }

// ------------------------------- Receipt methods ------------------------------------------

    public String getReceipts() {
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

    public void setIsActiveReceipt(int receiptNumber, boolean status) {
        try {
            REC.setIsActive(receiptNumber, status);
        } catch (DALException | JunkFormatException e) {
            e.printStackTrace();
        }
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

    public String createReceipt(int receiptNr) {
        for (ReceiptDTO receiptDTO : rList) {
            if (receiptDTO.getReceiptNr() == receiptNr) {
                try {
                    REC.createReceipt(new Receipt(receiptDTO));
                } catch (DALException | JunkFormatException e) {
                    e.printStackTrace();
                    return " Kunne ikke laves";
                }
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

    public String createProductBatch(ProductBatchDTO productBatchDTO) {
        try {
            PRO.createBatch(new ProductBatch(productBatchDTO));
        } catch (DALException | JunkFormatException e) {
            e.printStackTrace();
            return "Kunne ikke laves";
        }
        return "produkt batch lavet";
    }

    public String getProductBathes() {
        ObjectMapper objMapper = new ObjectMapper();
        try {
            return objMapper.writeValueAsString(PRO.getBatchList());
        } catch (JsonProcessingException | DALException e) {
            e.printStackTrace();
            return "Kunne ikke skaffe produkt batches";
        }
    }
}

