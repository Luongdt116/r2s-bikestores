package dao;

import entity.Product;
import exception.DAOException;

import java.util.ArrayList;

public interface ProductDAO {
    ArrayList<Product> DisplayAll() throws DAOException;
    boolean insert(Product product) throws DAOException;
    boolean update(int id, Product product) throws DAOException;
    boolean delete(Product product) throws DAOException;
    Product findById(int id) throws DAOException;
}
