package dao;

import entity.Product;
import entity.Staff;
import exception.DAOException;
import java.sql.*;
import java.util.ArrayList;
import static util.ScannerUtil.*;

public class ProductDAOImpl implements ProductDAO{
    private final Connection conn;

    public ProductDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public ArrayList<Product> DisplayAll() throws DAOException {
        if(conn == null){
            return null;
        }

        String select = "select * from products";
        ArrayList<Product> products = new ArrayList<>();

        try(Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(select)){

            while (rs.next()){
                Product product = new Product();

                product.setProduct_id(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setCategory_id(rs.getInt("category_id"));
                product.setBrand_id(rs.getInt("brand_id"));
                product.setPrice(rs.getDouble("price"));
                product.setDesc(rs.getString("description"));

                products.add(product);
            }
        }catch (SQLException e){
            throw new DAOException("Failed to display product list.", e);
        }
        return products;
    }

    @Override
    public boolean insert(Product product) throws DAOException {
        if(conn == null){
            return false;
        }

        boolean isCategoryValid = checkRecordExists(conn, "categories", "category_id", product.getCategory_id());
        if (!isCategoryValid) {
            System.out.println("Category ID " + product.getCategory_id() + " does not exist.");

            return false;
        }

        boolean isBrandValid = checkRecordExists(conn, "brands", "brand_id", product.getBrand_id());
        if (!isBrandValid) {
            System.out.println("Brand ID " + product.getBrand_id() + " does not exist.");

            return false;
        }

        String sql = "insert into products (name, category_id, brand_id, price, description) values (?,?,?,?,?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            int index = 1;

            ps.setString(index++, product.getName());
            ps.setInt(index++, product.getCategory_id());
            ps.setInt(index++, product.getBrand_id());
            ps.setDouble(index++, product.getPrice());
            ps.setString(index, product.getDesc());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new DAOException("Failed to insert product ", e);
        }
    }

    @Override
    public boolean update(int id, Product product) throws DAOException {
        if(conn == null){
            return false;
        }

        boolean isProductValid = checkRecordExists(conn, "products", "product_id", id);
        if (!isProductValid) {
            System.out.println("Product ID " + id + " does not exist.");

            return false;
        }

        boolean isCategoryValid = checkRecordExists(conn, "categories", "category_id", product.getCategory_id());
        if (!isCategoryValid) {
            System.out.println("Category ID " + product.getCategory_id() + " does not exist.");

            return false;
        }

        boolean isBrandValid = checkRecordExists(conn, "brands", "brand_id", product.getBrand_id());
        if (!isBrandValid) {
            System.out.println("Brand ID " + product.getBrand_id() + " does not exist.");

            return false;
        }

        String sql = "update products set name =?, category_id = ?, brand_id = ?, price = ?, description = ? where product_id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            int index = 1;

            ps.setString(index++, product.getName());
            ps.setInt(index++, product.getCategory_id());
            ps.setInt(index++, product.getBrand_id());
            ps.setDouble(index++, product.getPrice());
            ps.setString(index++, product.getDesc());
            ps.setInt(index, id);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        }catch (SQLException e){
            throw new DAOException("Failed to update product ", e);
        }
    }

    @Override
    public boolean delete(Product product) throws DAOException {
        if(conn == null){
            return false;
        }

        int productId = product.getProduct_id();

        boolean isProductValid = checkRecordExists(conn, "products", "product_id", productId);
        if (!isProductValid) {
            System.out.println("Product ID " + productId + " does not exist.");

            return false;
        }

        String sql = "delete from products where product_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, productId);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        }catch (SQLException e){
            throw new DAOException("Failed to delete product ", e);
        }
    }

    @Override
    public Product findById(int id) throws DAOException {
        String sql = "select * from products where product_id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Product product = new Product();
                product.setProduct_id(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setCategory_id(rs.getInt("category_id"));
                product.setBrand_id(rs.getInt("brand_id"));
                product.setPrice(rs.getDouble("price"));
                product.setDesc(rs.getString("description"));

                return product;
            }

        }catch (SQLException e){
            throw new DAOException("Failed to find product by ID.", e);
        }
        return null;
    }
}
