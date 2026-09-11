package dao;

import entity.Staff;
import entity.Store;
import exception.DAOException;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class StoreDAOIplm implements StoreDAO {
    private final Connection conn;
    public StoreDAOIplm (Connection conn) {this.conn = conn;}

    @Override
    public ArrayList<Store> DisplayAll() throws DAOException {
        if(conn == null){ return null;}

        String select = "select * from stores";
        ArrayList<Store> stores = new ArrayList<>();

        try(Statement s = conn.createStatement();){
            ResultSet rs = s.executeQuery(select);

            while (rs.next()){
                Store store = new Store();

                store.setStore_id(rs.getInt("store_id"));
                store.setName(rs.getString("name"));
                store.setAddress(rs.getString("address"));
                store.setPhone(rs.getString("phone"));

                stores.add(store);
            }
        }catch (SQLException e){
            throw new DAOException("Failed to display storelist.", e);
        }
        return stores;
    }

    @Override
    public boolean insert(Store store) throws DAOException {
        if(conn == null) { return false; }

        String sql = "insert into stores (name, address, phone) values(?,?,?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            int index = 1;

            ps.setString(index++, store.getName());
            ps.setString(index++, store.getAddress());
            ps.setString(index, store.getPhone());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        }catch (SQLException e){
            throw new DAOException("Failed to insert store ", e);
        }
    }

    @Override
    public boolean update(int id, Store store) throws DAOException {
        if(conn == null) { return false; }

        if (!checkStoreExists(id)) {
            System.out.println("Staff ID " + id + " does not exist.");

            return false;
        }

        String sql = "update stores set name = ?, address = ?, phone = ? where store_id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            int index = 1;

            ps.setString(index++, store.getName());
            ps.setString(index++, store.getAddress());
            ps.setString(index++, store.getPhone());
            ps.setInt(index, id);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        }catch (SQLException e){
            throw new DAOException("Failed to update store ", e);
        }
    }

    @Override
    public boolean delete(Store store) throws DAOException {
        if(conn == null){ return false; }

        int storeId = store.getStore_id();

        if (!checkStoreExists(storeId)) {
            System.out.println("Staff ID " + storeId + " does not exist.");

            return false;
        }

        String sql = "delete from stores where store_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,storeId);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        }catch (SQLException e){
            throw new DAOException("Failed to delete store", e);
        }
    }

    private boolean checkStoreExists(int storeId) throws DAOException {
        String sql = "select count(*) from stores where store_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, storeId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new DAOException("Failed to check Store ID.", e);
        }

        return false;
    }

    @Override
    public Store findById(int id) throws DAOException {
        String sql = "select * from stores where store_id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Store store = new Store();
                store.setStore_id(rs.getInt("store_id"));
                store.setName(rs.getString("name"));
                store.setAddress(rs.getString("address"));
                store.setPhone(rs.getString("phone"));

                return store;
            }

        }catch (SQLException e){
            throw new DAOException("Failed to find Store by ID.", e);
        }
        return null;
    }
}
