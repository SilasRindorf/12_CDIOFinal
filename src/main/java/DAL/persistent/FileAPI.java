package DAL.persistent;

import DAL.interfaces.DALException;

import javax.servlet.ServletContext;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileAPI {
    //If this pathing does not work. Make it more robust:https://stackoverflow.com/questions/8513729/create-file-in-resources-source-folder-in-java-programmatically
    //private Path RESOURCES = Paths.get(this.getClass().getResource("/").getPath());

    public static String USER_DAO_FILE;

    static {
        try {
            USER_DAO_FILE = ServletContext.class.getResource("/USER_DAO_DATA").toURI().toString().substring(5);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new AssertionError("Path finding failed");
        }
    }


    public static final String TEST_USER_DAO_FILE = "src/test/resources/USER_DAO_DATA";

    public static String COMMODITY_DAO_FILE;

    static {
        try {
            COMMODITY_DAO_FILE = ServletContext.class.getResource("/COMMODITY_DAO_DATA").toURI().toString().substring(5);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new AssertionError("Path finding failed");

        }
    }

    ;
    public static final String TEST_COMMODITY_DAO_FILE = "src/test/resources/COMMODITY_DAO_DATA";

    public static String PRODUCT_DAO_FILE;

    static {
        try {
            PRODUCT_DAO_FILE = ServletContext.class.getResource("/PRODUCT_DAO_DATA").toURI().toString().substring(5);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new AssertionError("Path finding failed");
        }
    }

    public static final String TEST_PRODUCT_DAO_FILE = "src/test/resources/PRODUCT_DAO_DATA";

    public static String RECEIPT_DAO_FILE;

    static {
        try {
            RECEIPT_DAO_FILE = ServletContext.class.getResource("/RECEIPT_DAO_DATA").toURI().toString().substring(5);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new AssertionError("Path finding failed");
        }
    }

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
