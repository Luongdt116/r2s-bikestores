package app;

import dao.StoreDAO;
import dao.StoreDAOIplm;
import entity.Store;
import exception.DAOException;
import exception.GlobalExceptionHandler;
import form.StoreForm;
import util.JDBCUtil;
import util.ScannerUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class StoreApp {
    private static final int GET_ALL_STORE = 1;
    private static final int ADD_NEW_STORE = 2;
    private static final int UPDATE_STORE = 3;
    private static final int REMOVE_STORE = 4;
    private static final int FIND_STORE_BY_ID = 5;
    private static final int BACK = 0;

    public static void run(){
        try(Connection conn = JDBCUtil.getConnection()){
            StoreDAO storeDAO = new StoreDAOIplm(conn);

            int choice;

            do{
                showMenu();
                choice = ScannerUtil.readInt("Enter your choice: ");
                try{
                    switch (choice){
                        case GET_ALL_STORE:
                            displayAllStore(storeDAO);
                            break;
                        case ADD_NEW_STORE:
                            addStore(storeDAO);
                            break;
                        case UPDATE_STORE:
                            updateStore(storeDAO);
                            break;
                        case REMOVE_STORE:
                            deleteStore(storeDAO);
                            break;
                        case FIND_STORE_BY_ID:
                            findStoreById(storeDAO);
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } catch (DAOException e){
                    GlobalExceptionHandler.handle(e);
                }
            }while (choice != BACK);
        }catch (SQLException e){
            System.out.println("Cannot connect to the database. ");
            System.out.println("Cause: " + e.getMessage());
        }
    }

    private static void showMenu(){
        System.out.println("\n====== STORE MANAGEMENT =======");
        System.out.println("1. Display all store");
        System.out.println("2. Add new store");
        System.out.println("3. Update store");
        System.out.println("4. Delete store");
        System.out.println("5 Find store by ID");
        System.out.println("0. Back to main menu");
    }

    private static void displayAllStore(StoreDAO storeDAO) throws DAOException {
        ArrayList<Store> storeList = storeDAO.DisplayAll();

        if(storeList == null || storeList.isEmpty()){
            System.out.println("No store found");
            return;
        }
        for (Store store : storeList){
            System.out.println(store);
        }
    }

    private static void addStore(StoreDAO storeDAO) throws DAOException{
        Store newStore = StoreForm.getStore();

        if(storeDAO.insert(newStore)){
            System.out.println("Store added successfully");
        }else {
            System.out.println("Failed to add store.");
        }
    }

    private static void updateStore(StoreDAO storeDAO) throws DAOException{
        int id = StoreForm.getID();
        Store store = StoreForm.getStore();

        if (storeDAO.update(id, store)) {
            System.out.println("Store updated successfully.");
        } else {
            System.out.println("Store was not found or update failed.");
        }
    }

    private static void deleteStore(StoreDAO storeDAO) throws DAOException{
        int id = StoreForm.getID();
        Store store = new Store();
        store.setStore_id(id);

        if (storeDAO.delete(store)) {
            System.out.println("Store deleted successfully.");
        } else {
            System.out.println("Store was not found or delete failed.");
        }
    }

    private static void findStoreById(StoreDAO storeDAO) throws DAOException{
        int id = StoreForm.getID();
        Store store = storeDAO.findById(id);

        if(store != null){
            System.out.println(store);
        }else {
            System.out.println("Store not found");
        }
    }
}
