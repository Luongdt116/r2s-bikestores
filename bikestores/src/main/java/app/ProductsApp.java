package app;

import dao.ProductDAO;
import dao.ProductDAOImpl;
import entity.Product;
import exception.DAOException;
import exception.GlobalExceptionHandler;
import form.ProductForm;
import util.JDBCUtil;
import util.ScannerUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProductsApp {
        private static final int GET_ALL_PRODUCT = 1;
    private static final int ADD_NEW_PRODUCT = 2;
    private static final int UPDATE_PRODUCT = 3;
    private static final int REMOVE_PRODUCT = 4;
    private static final int FIND_PRODUCT_BY_ID = 5;
    private static final int BACK = 0;

    public static void run(){
        try(Connection conn = JDBCUtil.getConnection()){
            ProductDAO productDAO = new ProductDAOImpl(conn);

            int choice;

            do{
                showMenu();
                choice = ScannerUtil.readInt("Enter your choice: ");

                try{
                    switch (choice){
                        case GET_ALL_PRODUCT:
                            displayAllProduct(productDAO);
                            break;
                        case ADD_NEW_PRODUCT:
                            addProduct(productDAO);
                            break;
                        case UPDATE_PRODUCT:
                            updateProduct(productDAO);
                            break;
                        case REMOVE_PRODUCT:
                            deleteProduct(productDAO);
                            break;
                        case FIND_PRODUCT_BY_ID:
                            findProductById(productDAO);
                            break;
                        case BACK:
                            System.out.println("Back to menu.");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } catch (DAOException e){
                    GlobalExceptionHandler.handle(e);
                }
            }while (choice != BACK);
        }catch (SQLException e){
            System.out.println("Cannot connect to the database.");
            System.out.println("Cause: " + e.getMessage());
        }
    }

    private static void showMenu(){
        System.out.println("\n====== PRODUCT MANAGEMENT =======");
        System.out.println("1. Display all product");
        System.out.println("2. Add new product");
        System.out.println("3. Update product");
        System.out.println("4. Delete product");
        System.out.println("5 Find product by ID");
        System.out.println("0. Back to main menu");
    }

    private static void displayAllProduct(ProductDAO productDAO) throws DAOException {
        ArrayList<Product> productList = productDAO.DisplayAll();

        if(productList == null || productList.isEmpty()){
            System.out.println("No staff found");
            return;
        }
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    private static void addProduct(ProductDAO productDAO) throws DAOException{
        Product newProc = ProductForm.getProduct();

        if (productDAO.insert(newProc)) {
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Failed to add product.");
        }
    }

    private static void updateProduct(ProductDAO productDAO) throws DAOException{
        int id = ProductForm.getId();
        Product product = ProductForm.getProduct();

        if (productDAO.update(id, product)) {
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product was not found or update failed.");
        }
    }

    private static void deleteProduct(ProductDAO productDAO) throws DAOException{
        int id = ProductForm.getId();
        Product product = new Product();
        product.setProduct_id(id);

        if (productDAO.delete(product)) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product was not found or delete failed.");
        }
    }

    private static void findProductById(ProductDAO productDAO) throws DAOException{
        int id = ProductForm.getId();

        Product product = productDAO.findById(id);

        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Product not found.");
        }
    }
}
