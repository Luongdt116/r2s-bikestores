package dao;

import entity.Store;
import exception.DAOException;

import java.util.ArrayList;

public interface StoreDAO {
    ArrayList<Store> DisplayAll() throws DAOException;
    boolean insert(Store store) throws DAOException;
    boolean update(int id, Store store) throws DAOException;
    boolean delete(Store store) throws DAOException;
    Store findById(int id) throws DAOException;

}
