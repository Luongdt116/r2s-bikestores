package dao;

import entity.Staff;
import exception.DAOException;

import java.util.ArrayList;

public interface StaffDAO {
    ArrayList<Staff> DisplayALl() throws DAOException;
    boolean insert(Staff staff) throws DAOException;
    boolean update(int id,Staff staff) throws DAOException;
    boolean delete(Staff staff) throws DAOException;



}
