package DAL.persistent;

import DAL.interfaces.DALException;

import java.io.*;


public class FileAPI {
    public static final String USER_DAO_FILE = "src/main/resources/USER_DAO_DATA";
    public static final String TEST_USER_DAO_FILE = "src/test/resources/USER_DAO_DATA";

    public static final String COMMODITY_DAO_FILE = "src/main/resources/COMMODITY_DAO_DATA";
    public static final String TEST_COMMODITY_DAO_FILE = "src/test/resources/COMMODITY_DAO_DATA";

    public static final String PRODUCT_DAO_FILE = "src/main/resources/PRODUCT_DAO_DATA";
    public static final String TEST_PRODUCT_DAO_FILE = "src/test/resources/PRODUCT_DAO_DATA";

    public static final String RECEIPT_DAO_FILE = "src/main/resources/RECEIPT_DAO_DATA";
    public static final String TEST_RECEIPT_DAO_FILE = "src/test/resources/RECEIPT_DAO_DATA";

    // Load and save files:https://howtodoinjava.com/java/collections/arraylist/serialize-deserialize-arraylist/
    public static void saveDataToFile(Object toSave, String filepath) throws IOException, DALException {
        FileOutputStream fos = new FileOutputStream(filepath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(toSave);
        oos.close();
        fos.close();
    }

    public static Object loadDataFromFile(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        Object res = null;
        ObjectInputStream ois = new ObjectInputStream(fis);
        res = ois.readObject();
        ois.close();
        fis.close();
        return res;
    }
}
