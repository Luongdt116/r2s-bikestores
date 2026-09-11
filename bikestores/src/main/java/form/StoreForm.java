package form;


import entity.Store;
import static util.ScannerUtil.*;

public class StoreForm {
    public static int getID(){
        return readPositiveInt("Enter Store ID: ");
    }

    public static String getName(){
        return readNonEmptyString("Enter store name: ");
    }

    public static Store getStore(){
        Store store = new Store();

        store.setName(readStringWithLimit("Enter staff name: ", 100));
        store.setAddress(readStringWithLimit("Enter staff address: ",255));
        store.setPhone(readValidPhone("Enter staff phone: "));
        return store;
    }

    public static int inputStoreId(String action){
        return readPositiveInt("Enter store ID to " + action + ": ");
    }

}
