package form;

import entity.Product;

import static util.ScannerUtil.*;

public class ProductForm {
    public static int getId(){
        return readPositiveInt("Enter product ID: ");
    }

    public static String getName(){
        return readNonEmptyString("Enter product name: ");
    }

    public static Product getProduct(){
        Product product = new Product();

        product.setName(readStringWithLimit("Enter product name: ", 100));
        product.setCategory_id(readInt("Enter category ID: "));
        product.setBrand_id(readInt("Enter brand ID: "));
        product.setPrice(readPositiveDouble("Enter price: "));
        product.setDesc(readStringWithLimit("Enter description: ", 255));

        return product;
    }

    public static int inputProductId(String action) {
        return readPositiveInt("Enter product ID to " + action + ": ");
    }
}
